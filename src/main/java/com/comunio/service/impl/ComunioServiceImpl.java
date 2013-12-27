package com.comunio.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comunio.dao.ComunioDao;
import com.comunio.model.Comunio;
import com.comunio.service.ComunioService;
@Service
public class ComunioServiceImpl implements ComunioService {
	@Autowired
	private ComunioDao comunioDao;
	
	@Transactional
	public void add(Comunio comunio) {
		comunioDao.add(comunio);
	}

	@Transactional
	public void edit(Comunio comunio) {
		comunioDao.edit(comunio);
	}

	@Transactional
	public void delete(int comId) {
		comunioDao.delete(comId);
	}

	@Transactional
	public Comunio getComunio(int comId) {
		return comunioDao.getComunio(comId);
	}

	@Transactional
	public List getAllComunio() {
		return comunioDao.getAllComunio();
	}

}
