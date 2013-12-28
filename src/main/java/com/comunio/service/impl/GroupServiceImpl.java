package com.comunio.service.impl;

import java.util.List;

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
}

