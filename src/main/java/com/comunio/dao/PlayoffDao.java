package com.comunio.dao;

import com.comunio.model.Playoff;

public interface PlayoffDao {

    Playoff save(Playoff playoff);

    Playoff findById(Playoff playoff);

    void delete(Playoff playoff);

    Playoff findBy(long comunioId);
}
