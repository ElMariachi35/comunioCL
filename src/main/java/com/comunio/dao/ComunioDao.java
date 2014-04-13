package com.comunio.dao;

import java.util.List;

import com.comunio.model.Comunio;

public interface ComunioDao {
    public Comunio add(Comunio comunio);

    public Comunio getComunio(long comunioId);

    public List<Comunio> getAllComunio();
}
