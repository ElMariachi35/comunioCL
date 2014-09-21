package com.comunio.service;

import java.util.List;

import com.comunio.model.Playoff;
import com.comunio.model.Result;

public interface PlayoffFinalService {

    void updateSecondLeg(List<Result> results, Playoff playoff);

    void updateThirdLeg(List<Result> results, Playoff playoff);

    void updateFirstLeg(List<Result> results, Playoff playoff);

}
