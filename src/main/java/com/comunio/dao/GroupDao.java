package com.comunio.dao;

import java.util.List;

import com.comunio.model.Comunio;
import com.comunio.model.Groupe;
import com.comunio.model.Team;

public interface GroupDao {
	public void add(Groupe group);
	public void edit(Groupe group);
	public void delete(long groupId);
	public Groupe getGroup(long groupId);
	public Groupe getGroup(long comunioId, String groupName);
	public List<Groupe> findGroupsByComunioId(long comunioId);
}
