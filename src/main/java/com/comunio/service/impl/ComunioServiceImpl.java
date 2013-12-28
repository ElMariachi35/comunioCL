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
	private ComunioDao comunioDao;
	@Autowired
	private GroupService groupService;
	
	@Transactional
	public void add(Comunio comunio) {
		comunioDao.add(comunio);
	}

	@Transactional
	public void edit(Comunio comunio) {
		comunioDao.edit(comunio);
	}

	@Transactional
	public void delete(long comunioId) {
		comunioDao.delete(comunioId);
	}

	@Transactional
	public Comunio getComunio(long comunioId) {
		return comunioDao.getComunio(comunioId);
	}

	@Transactional
	public List<Comunio> getAllComunio() {
		return comunioDao.getAllComunio();
	}
	
	@Transactional
	public void createComunio(String comunioName, int numberOfGroups){
		Comunio comunio = new Comunio();
		comunio.setName(comunioName);
		comunioDao.add(comunio);
		comunio = getComunio(comunio.getComunioId());
		comunio.setGroups(initializeGroups(numberOfGroups, comunio));
		comunioDao.edit(comunio);
	}

	private Set<Groupe> initializeGroups(int numberOfGroups, Comunio comunio) {
		String groupNames = "ABCDEFGHIJKLMN";
		Set<Groupe> groupList = new HashSet<Groupe>();
		for(int i=0;i<numberOfGroups;i++){
			Groupe group = new Groupe();
			group.setGroupName(groupNames.charAt(i)+"");
			group.setComunio(comunio);
			groupService.add(group);
			groupList.add(group);
		}
		return groupList;
	}

}
