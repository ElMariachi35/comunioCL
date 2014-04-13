package com.comunio.dao.impl;

import java.util.List;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.comunio.dao.ComunioDao;
import com.comunio.model.Comunio;

@Repository
public class ComunioDaoImpl implements ComunioDao {
    @Autowired
    private SessionFactory session;

    @Override
    public Comunio add(Comunio comunio) {
        session.getCurrentSession().save(comunio);
        return comunio;
    }

    @Override
    public Comunio getComunio(long comunioId) {
        return (Comunio) session.getCurrentSession().get(Comunio.class, comunioId);
    }

    @Override
    public List<Comunio> getAllComunio() {
        return session.getCurrentSession().createQuery("from Comunio").list();
    }
}
