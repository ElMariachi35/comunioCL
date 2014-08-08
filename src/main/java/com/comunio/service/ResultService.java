package com.comunio.service;

import java.util.List;

import com.comunio.model.JsonResult;
import com.comunio.model.Result;

public interface ResultService {

    List<Result> findResultsBy(long comunioId, int matchdayNumber);

    void updateResult(List<JsonResult> jsonResults, long comunioId);

    int findNextMatchday(long comunioId);
}
