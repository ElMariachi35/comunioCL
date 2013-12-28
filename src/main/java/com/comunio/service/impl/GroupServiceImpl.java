package com.comunio.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comunio.dao.ComunioDao;
import com.comunio.dao.GroupDao;
import com.comunio.model.Comunio;
import com.comunio.model.Groupe;
import com.comunio.service.ComunioService;
import com.comunio.service.GroupService;
@Service
public class GroupServiceImpl implements GroupService {
	@Autowired
	private GroupDao groupDao;
	@Autowired
	private ComunioService comunioService;
	
	@Transactional
	public void add(Groupe group) {
		groupDao.add(group);
	}

	@Transactional
	public void edit(Groupe group) {
		groupDao.edit(group);
	}

	@Transactional
	public void delete(long groupId) {
		groupDao.delete(groupId);
	}

	@Transactional
	public Groupe getGroup(long groupId) {
		return groupDao.getGroup(groupId);
	}
	
	@Transactional
	public void initializeGroups(long comunioId, int numberOfTeams, int numberOfGroups) {
		String groupNames = "ABCDEFGHIJKLMN";
		for(int i=0;i<numberOfGroups;i++){
			Groupe group = new Groupe();
			group.setGroupName(groupNames.charAt(i)+"");
			group.setComunio(comunioService.getComunio(comunioId));
			groupDao.add(group);
		}
	}
}

