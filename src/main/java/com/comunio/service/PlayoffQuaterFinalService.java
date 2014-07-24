package com.comunio.service;

import java.io.Serializable;
import java.util.List;

import com.comunio.model.Result;

public interface PlayoffQuaterFinalService extends Serializable {

    void updateFirstLeg(List<Result> results);

    void updateSecondLeg(List<Result> results);

}
