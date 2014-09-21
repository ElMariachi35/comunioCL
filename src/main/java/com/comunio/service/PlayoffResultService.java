package com.comunio.service;

import java.util.List;
import java.util.Map;

import com.comunio.model.Result;

public interface PlayoffResultService {

    void handlePlayoff(Map<Integer, List<Result>> preparedResults);

    void handlePlayoff(List<Result> results, long comunioId);
}
