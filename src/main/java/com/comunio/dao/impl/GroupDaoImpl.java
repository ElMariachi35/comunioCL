package com.comunio.dao.impl;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.comunio.dao.GroupDao;
import com.comunio.model.Groupe;

@Repository
public class GroupDaoImpl implements GroupDao{

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
		session.getCurrentSession().get(Groupe.class, groupId);
		return null;
	}

}
