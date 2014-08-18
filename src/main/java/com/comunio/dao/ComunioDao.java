package com.comunio.dao;

import com.comunio.model.Comunio;

public interface ComunioDao {
    public Comunio add(Comunio comunio);

    public Comunio getComunio(long comunioId);

    Comunio save(Comunio comunio);

    long findByName(String name);

    long findById(long id);

    public String findPassword(long comunioId);

    public int countByName(String comName);
}
