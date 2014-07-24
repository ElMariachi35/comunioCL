package com.comunio.service;

import java.util.List;

import com.comunio.model.Result;

public interface PlayoffFinalService {

    void updateSecondLeg(List<Result> results);

    void updateThirdLeg(List<Result> results);

    void updateFirstLeg(List<Result> results);

}
