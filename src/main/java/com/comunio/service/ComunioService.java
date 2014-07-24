package com.comunio.service;

import java.util.List;

import com.comunio.model.Comunio;
import com.comunio.model.Team;

public interface ComunioService {
    public void add(Comunio comunio);

    Comunio createComunio(String name, String password);

    public List<Team> getAllTeams();

    public Comunio retrieveComunio(long parseLong);

    Comunio save(Comunio comunio);

}
