package com.comunio.dao;

import com.comunio.model.Playoff;

public interface PlayoffDao {

    void save(Playoff playoff);

    void update(Playoff playoff);
}
