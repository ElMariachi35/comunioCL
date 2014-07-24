package com.comunio.service.impl;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comunio.dao.PlayoffDao;
import com.comunio.model.Playoff;
import com.comunio.service.PlayoffService;

@Service
public class PlayoffServiceImpl implements PlayoffService, Serializable {
    private static final long serialVersionUID = -4586483467938948380L;

    @Autowired
    PlayoffDao playoffDao;

    @Override
    @Transactional
    public void save(Playoff playoff) {
        playoffDao.save(playoff);
    }

    @Override
    @Transactional
    public void delete(Playoff playoff) {
        playoffDao.delete(playoff);
    }
}
