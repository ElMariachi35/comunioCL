package com.comunio.dao;

import java.util.List;

import com.comunio.model.Comunio;

public interface ComunioDao {
	public void add(Comunio comunio);
	public void edit(Comunio comunio);
	public void delete(long comuioId);
	public Comunio getComunio(long comunioId);
	public List<Comunio> getAllComunio();
}
