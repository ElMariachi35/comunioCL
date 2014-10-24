package com.comunio.service;

import com.comunio.model.Playoff;

public interface PlayoffService {
    void save(Playoff playoff);

    void delete(Playoff playoff);

    Playoff findBy(long comunioId);
}
