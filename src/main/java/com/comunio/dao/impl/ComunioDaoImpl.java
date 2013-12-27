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
	public void add(Comunio comunio) {
		session.getCurrentSession().save(comunio);
	}

	@Override
	public void edit(Comunio comunio) {
		session.getCurrentSession().update(comunio);
	}

	@Override
	public void delete(int comId) {		
		session.getCurrentSession().delete(getComunio(comId));
	}

	@Override
	public Comunio getComunio(int comId) {
		return (Comunio)session.getCurrentSession().get(Comunio.class, comId);
	}

	@Override
	public List getAllComunio() {
		return session.getCurrentSession().createQuery("from Comunio").list();
	}

}
