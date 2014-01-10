package com.comunio.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comunio.dao.ComunioDao;
import com.comunio.model.Comunio;
import com.comunio.model.Groupe;
import com.comunio.service.ComunioService;
import com.comunio.service.GroupService;

@Service
public class ComunioServiceImpl implements ComunioService {
	@Autowired
	private
	ComunioDao comunioDao;
	@Autowired
	GroupService groupService;

	@Transactional
	public void add(Comunio comunio) {
		getComunioDao().add(comunio);
	}

	@Transactional
	public void edit(Comunio comunio) {
		getComunioDao().edit(comunio);
	}

	@Transactional
	public void delete(long comunioId) {
		getComunioDao().delete(comunioId);
	}

	@Transactional
	public Comunio getComunio(long comunioId) {
		return getComunioDao().getComunio(comunioId);
	}

	@Transactional
	public List<Comunio> getAllComunio() {
		return getComunioDao().getAllComunio();
	}

	@Transactional
	public long createComunio(String comunioName, String password) {
		Comunio comunio = new Comunio();
		comunio.setName(comunioName);
		comunio.setPassword(password);
		getComunioDao().add(comunio);
		return comunio.getComunioId();
		// comunio = getComunio(comunio.getComunioId());
		// comunio.setGroups(initializeGroups(numberOfGroups, comunio));
		// comunioDao.edit(comunio);
	}

	public ComunioDao getComunioDao() {
		return comunioDao;
	}

	public void setComunioDao(ComunioDao comunioDao) {
		this.comunioDao = comunioDao;
	}

}
