package com.comunio.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comunio.dao.ComunioDao;
import com.comunio.exception.NoComunioLoadedException;
import com.comunio.model.Comunio;
import com.comunio.model.Groupe;
import com.comunio.model.Team;
import com.comunio.service.ComunioService;
import com.comunio.service.GroupService;

@Service
public class ComunioServiceImpl implements ComunioService {
    @Autowired
    ComunioDao comunioDao;
    @Autowired
    GroupService groupService;

    private Comunio comunio;

    @Transactional
    public void add(Comunio comunio) {
        comunioDao.add(comunio);
    }

    @Transactional
    public void loadComunio(long comunioId) {
        if (comunio == null || comunio.getComunioId() != comunioId)
            comunio = comunioDao.getComunio(comunioId);
    }

    @Transactional
    public void refreshComunio() {
        if (comunio != null) {
            comunio = comunioDao.getComunio(comunio.getComunioId());
        } else {
            throw new NoComunioLoadedException();
        }
    }

    @Transactional
    public long createComunio(String comunioName, String password) {
        Comunio comunio = new Comunio();
        comunio.setName(comunioName);
        comunio.setPassword(password);
        comunioDao.add(comunio);
        return comunio.getComunioId();
    }

    public Comunio getComunio() {
        return comunio;
    }

    public void setComunio(Comunio comunio) {
        this.comunio = comunio;
    }

    @Transactional
    public List<Team> getAllTeams(long comunioId) {
        loadComunio(comunioId);
        List<Team> teams = new ArrayList<>();
        for (Groupe group : comunio.getGroups()) {
            for (Team team : group.getTeams()) {
                teams.add(team);
            }
        }
        Collections.sort(teams);
        return teams;
    }
}
