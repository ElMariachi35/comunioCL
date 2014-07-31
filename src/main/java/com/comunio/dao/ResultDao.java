package com.comunio.dao;

import java.util.List;

import com.comunio.model.Result;
import com.comunio.model.Team;

public interface ResultDao {
    public Result saveOrUpdate(Result result);

    public List<Result> getResultsByTeam(Team team);

    public List<Result> getResults(long comunioId);

    public List<Result> findResultsBy(long comunioId, int matchdayNumber);
}
