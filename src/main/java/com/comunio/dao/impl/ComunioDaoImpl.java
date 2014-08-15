package com.comunio.dao.impl;

import java.math.BigInteger;
import java.util.List;
import java.util.Random;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.SessionFactory;
import org.hibernate.classic.Session;
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
        comunio.setComunioId(generateComunioId());
        session.getCurrentSession().save(comunio);
        return comunio;
    }

    private long generateComunioId() {
        int min = 10000;
        int max = 99999;
        Random rand = new Random();
        int randomNum = rand.nextInt((max - min) + 1) + min;
        if (comunioExists(randomNum)) {
            return generateComunioId();
        }
        return Long.valueOf(randomNum);
    }

    private boolean comunioExists(long comunioId) {
        Query query = session.getCurrentSession().createSQLQuery(
                "SELECT COUNT(*) FROM comunio WHERE comunioId=" + comunioId);
        BigInteger numberOfComunios = (BigInteger) query.uniqueResult();
        if (numberOfComunios.intValue() == 0) {
            return false;
        }
        return true;
    }

    @Override
    public Comunio getComunio(long comunioId) {
        return (Comunio) session.getCurrentSession().get(Comunio.class, comunioId);
    }

    @Override
    public Comunio save(Comunio comunio) {
        Session currentSession = session.getCurrentSession();
        Comunio persistedComunio = (Comunio) currentSession.get(Comunio.class, comunio.getComunioId());
        if (persistedComunio == null) {
            currentSession.save(comunio);
            return comunio;
        }
        return (Comunio) currentSession.merge(comunio);
    }

    @Override
    @SuppressWarnings("unchecked")
    public long findByName(String name) {
        SQLQuery query = session.getCurrentSession().createSQLQuery(
                "SELECT comunioId FROM comunio WHERE name='" + name + "';");
        List<BigInteger> list = query.list();
        if (list.isEmpty()) {
            return 0;
        }
        return ((BigInteger) list.get(0)).longValue();
    }

    @Override
    @SuppressWarnings("unchecked")
    public long findById(long id) {
        SQLQuery query = session.getCurrentSession().createSQLQuery(
                "SELECT comunioId FROM comunio WHERE comunioId='" + id + "';");
        List<BigInteger> list = query.list();
        if (list.isEmpty()) {
            return 0;
        }
        return ((BigInteger) list.get(0)).longValue();
    }

    @Override
    public String findPassword(long comunioId) {
        return (String) session.getCurrentSession()
                .createSQLQuery("SELECT password FROM comunio WHERE comunioId='" + comunioId + "';").uniqueResult();
    }
}
