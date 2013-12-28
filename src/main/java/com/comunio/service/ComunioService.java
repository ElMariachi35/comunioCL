package com.comunio.service;

import java.util.List;
import java.util.Set;

import com.comunio.model.Comunio;
import com.comunio.model.Groupe;

public interface ComunioService {
	public void add(Comunio comunio);
	public void edit(Comunio comunio);
	public void delete(long comunioId);
	public Comunio getComunio(long comunioId);
	public List<Comunio> getAllComunio();
	long createComunio(String name, String password);
}
