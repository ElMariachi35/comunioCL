package com.comunio.service;

import java.util.List;
import java.util.Set;

import com.comunio.model.Fixture;
import com.comunio.model.Matchday;

public interface MatchdayService {
    public void saveMatchdays(Set<Matchday> set);

    public void saveMatchday(Matchday matchday);

    public List<Matchday> getSortedMatchdays(Fixture fixture);
}
