package com.comunio.service;

import java.io.Serializable;
import java.util.List;

import com.comunio.model.Playoff;
import com.comunio.model.Result;

public interface PlayoffSemiFinalService extends Serializable {

    Playoff updateFirstLeg(List<Result> results, Playoff playoff);

    Playoff updateSecondLeg(List<Result> results, Playoff playoff);

}
