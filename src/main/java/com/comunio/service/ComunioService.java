package com.comunio.service;

import java.util.List;

import com.comunio.model.Comunio;
import com.comunio.model.Team;

public interface ComunioService {
    public void add(Comunio comunio);

    Comunio createComunio(String name, String password);

    public List<Team> getAllTeams();

    List<Team> getAllTeams(long comunioId);

    public Comunio retrieveComunio(long parseLong);

    Comunio save(Comunio comunio);

    long findByName(String name);

    long findById(long id);

    public boolean checkPassword(long comunioId, String password);

}
