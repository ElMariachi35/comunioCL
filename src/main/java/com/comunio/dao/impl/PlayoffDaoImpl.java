package com.comunio.dao.impl;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.comunio.dao.PlayoffDao;
import com.comunio.model.Playoff;

@Repository
public class PlayoffDaoImpl implements PlayoffDao, Serializable {
    private static final long serialVersionUID = 755666301771987768L;
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public Playoff save(Playoff playoff) {
        Playoff playoffToUpdate = findById(playoff);
        if (playoffToUpdate == null) {
            sessionFactory.getCurrentSession().save(playoff);
            return playoff;
        }
        sessionFactory.getCurrentSession().merge(playoff);
        return playoff;
    }

    @Override
    public Playoff findById(Playoff playoff) {
        return (Playoff) sessionFactory.getCurrentSession().get(Playoff.class, playoff.getId());
    }

    @Override
    public void delete(Playoff playoff) {
        sessionFactory.getCurrentSession().delete(playoff);
    }

    @Override
    public Playoff findBy(long comunioId) {
        return (Playoff) sessionFactory
                .getCurrentSession()
                .createSQLQuery(
                        "SELECT * FROM Playoff where id=(SELECT playoff_id FROM Comunio WHERE comunioId=" + comunioId
                                + ");").addEntity(Playoff.class).uniqueResult();
    }
}
