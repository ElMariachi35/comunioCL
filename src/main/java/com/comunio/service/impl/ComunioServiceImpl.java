package com.comunio.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comunio.dao.ComunioDao;
import com.comunio.model.Comunio;
import com.comunio.service.ComunioService;
import com.comunio.service.GroupService;

@Service
public class ComunioServiceImpl implements ComunioService {
    @Autowired
    ComunioDao comunioDao;
    @Autowired
    GroupService groupService;

    @Transactional
    public void add(Comunio comunio) {
        comunioDao.add(comunio);
    }

    @Transactional
    public Comunio getComunio(long comunioId) {
        return comunioDao.getComunio(comunioId);
    }

    @Transactional
    public long createComunio(String comunioName, String password) {
        Comunio comunio = new Comunio();
        comunio.setName(comunioName);
        comunio.setPassword(password);
        comunioDao.add(comunio);
        return comunio.getComunioId();
    }
}
