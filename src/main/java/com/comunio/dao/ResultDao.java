package com.comunio.dao;

import java.util.List;

import com.comunio.model.Result;
import com.comunio.model.Team;

public interface ResultDao {
    public void saveOrUpdate(Result result);

    public List<Result> getResultsByTeam(Team team);
}
