package com.comunio.dao.impl;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.comunio.dao.FixtureDao;
import com.comunio.model.Fixture;

@Repository
public class FixtureDaoImpl implements FixtureDao {

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void addFixture(Fixture fixture) {
        sessionFactory.getCurrentSession().save(fixture);
    }

    @Override
    public void updateFixture(Fixture fixture) {
        sessionFactory.getCurrentSession().update(fixture);
    }

    @Override
    public Fixture getFixtureByGroupId(long groupId) {
        Query query = sessionFactory.getCurrentSession()
                .createSQLQuery("Select * from Fixture where groupId= :groupId").addEntity(Fixture.class)
                .setParameter("groupId", groupId);
        return (Fixture) query.list().get(0);
    }
}
