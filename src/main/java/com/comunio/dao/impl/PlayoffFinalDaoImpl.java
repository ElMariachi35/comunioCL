package com.comunio.dao.impl;

import java.io.Serializable;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.comunio.dao.PlayoffFinalDao;
import com.comunio.model.PlayoffFinale;

@Repository
public class PlayoffFinalDaoImpl implements PlayoffFinalDao, Serializable{
    private static final long serialVersionUID = 5828694283447794385L;
    
    @Autowired
    SessionFactory sessionFactory;

    @Override
    public void save(PlayoffFinale playoffFinale) {
	sessionFactory.getCurrentSession().save(playoffFinale);
    }

}
