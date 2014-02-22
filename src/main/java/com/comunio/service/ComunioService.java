package com.comunio.service;

import java.util.List;

import com.comunio.model.Comunio;
import com.comunio.model.Team;

public interface ComunioService {
    public void add(Comunio comunio);

    public void loadComunio(long comunioId);

    long createComunio(String name, String password);

    public Comunio getComunio();

    public void refreshComunio();

    public List<Team> getAllTeams(long comunioId);

}
