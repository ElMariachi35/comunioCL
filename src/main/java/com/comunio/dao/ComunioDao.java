package com.comunio.dao;

import java.util.List;

import com.comunio.model.Comunio;

public interface ComunioDao {
	public void add(Comunio comunio);
	public void edit(Comunio comunio);
	public void delete(int comId);
	public Comunio getComunio(int comId);
	public List getAllComunio();
}
