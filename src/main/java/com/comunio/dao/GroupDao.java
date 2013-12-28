package com.comunio.dao;

import java.util.List;

import com.comunio.model.Comunio;
import com.comunio.model.Groupe;

public interface GroupDao {
	public void add(Groupe group);
	public void edit(Groupe group);
	public void delete(long groupId);
	public Groupe getGroup(long groupId);
}
