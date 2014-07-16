package com.comunio.service;

import java.util.List;

import com.comunio.model.Result;

public interface PlayoffResultService {
    void handlePlayoff(List<Result> results);
}
