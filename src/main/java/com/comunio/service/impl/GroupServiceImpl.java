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
		getGroupDao().add(group);
	}

	@Transactional
	public void edit(Groupe group) {
		getGroupDao().edit(group);
	}

	@Transactional
	public void delete(long groupId) {
		getGroupDao().delete(groupId);
	}

	@Transactional
	public Groupe getGroup(long groupId) {
		return getGroupDao().getGroup(groupId);
	}
	
	@Transactional
	public void initializeGroups(long comunioId, int numberOfTeams, int numberOfGroups) {
		String groupNames = "ABCDEFGHIJKLMNOPQRSTUVWXYZ";
		for(int i=0;i<numberOfGroups;i++){
			Groupe group = new Groupe();
			group.setGroupName(groupNames.charAt(i)+"");
			group.setComunio(getComunioService().getComunio(comunioId));
			getGroupDao().add(group);
		}
	}

	public GroupDao getGroupDao() {
		return groupDao;
	}

	public void setGroupDao(GroupDao groupDao) {
		this.groupDao = groupDao;
	}

	public ComunioService getComunioService() {
		return comunioService;
	}

	public void setComunioService(ComunioService comunioService) {
		this.comunioService = comunioService;
	}
}

