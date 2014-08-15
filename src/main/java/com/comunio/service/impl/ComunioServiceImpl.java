package com.comunio.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comunio.dao.ComunioDao;
import com.comunio.model.Comunio;
import com.comunio.model.Groupe;
import com.comunio.model.SessionData;
import com.comunio.model.Team;
import com.comunio.service.ComunioService;
import com.comunio.service.GroupService;

@Service
public class ComunioServiceImpl implements ComunioService {
    @Autowired
    ComunioDao comunioDao;
    @Autowired
    GroupService groupService;
    @Autowired
    PasswordHashingService passwordHashingService;
    @Autowired
    SessionData sessionData;

    @Transactional
    public void add(Comunio comunio) {
        comunioDao.add(comunio);
    }

    @Transactional
    public Comunio createComunio(String comunioName, String password) {
        Comunio comunio = new Comunio();
        comunio.setName(comunioName);
        comunio.setPassword(passwordHashingService.hashPassword(password));
        return comunioDao.add(comunio);
    }

    public List<Team> getAllTeams() {
        List<Team> teams = new ArrayList<Team>();
        for (Groupe group : groupService.getGroups()) {
            for (Team team : group.getTeams()) {
                teams.add(team);
            }
        }
        Collections.sort(teams);
        return teams;
    }

    @Override
    public List<Team> getAllTeams(long comunioId) {
        List<Team> teams = new ArrayList<Team>();
        for (Groupe group : groupService.findGroupsByComunioId(comunioId)) {
            for (Team team : group.getTeams()) {
                teams.add(team);
            }
        }
        Collections.sort(teams);
        return teams;
    }

    @Transactional
    public Comunio retrieveComunio(long comunioId) {
        return comunioDao.getComunio(comunioId);
    }

    @Transactional
    @Override
    public Comunio save(Comunio comunio) {
        return comunioDao.save(comunio);
    }

    @Override
    @Transactional
    public long findByName(String name) {
        return comunioDao.findByName(name);
    }

    @Override
    @Transactional
    public long findById(long id) {
        return comunioDao.findById(id);
    }

    @Override
    @Transactional
    public boolean checkPassword(long comunioId, String password) {
        String storedPassword = comunioDao.findPassword(comunioId);
        if (storedPassword.equals(passwordHashingService.hashPassword(password))) {
            return true;
        }
        return false;
    }
}
