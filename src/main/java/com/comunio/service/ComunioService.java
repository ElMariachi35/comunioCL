package com.comunio.service;

import com.comunio.model.Comunio;

public interface ComunioService {
	public void add(Comunio comunio);
	public Comunio getComunio(long comunioId);
	long createComunio(String name, String password);
}
