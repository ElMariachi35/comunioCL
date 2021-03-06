package com.comunio.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.comunio.dao.GroupDao;
import com.comunio.model.Groupe;

@Repository
public class GroupDaoImpl implements GroupDao {

    @Autowired
    private SessionFactory session;

    @Override
    public void add(Groupe group) {
        session.getCurrentSession().save(group);
    }

    @Override
    public void edit(Groupe group) {
        session.getCurrentSession().update(group);
    }

    @Override
    public void delete(long groupId) {
        session.getCurrentSession().delete(getGroup(groupId));
    }

    @Override
    public Groupe getGroup(long groupId) {
        return (Groupe) session.getCurrentSession().get(Groupe.class, groupId);
    }

    @Override
    public Groupe getGroup(long comunioId, String groupName) {
        Query query = session.getCurrentSession()
                .createSQLQuery("select * from Groupe g where g.groupName = :groupName and comunioId= :comunioId")
                .addEntity(Groupe.class).setParameter("groupName", groupName).setParameter("comunioId", comunioId);
        List list = query.list();
        return (Groupe) list.get(0);
    }

    public List<Groupe> findGroupsByComunioId(long comunioId) {
        Query query = session.getCurrentSession()
                .createSQLQuery("select * from Groupe g where g.comunioId= :comunioId").addEntity(Groupe.class)
                .setParameter("comunioId", comunioId);
        List<Groupe> groups = query.list();
        return groups;
    }

    @Override
    public int determineNumberOfGroups(long comunioId) {
        Query query = session.getCurrentSession()
                .createSQLQuery("select count(*) from Groupe g where g.comunioId= :comunioId")
                .setParameter("comunioId", comunioId);
        return Integer.parseInt(query.uniqueResult().toString());
    }

}
