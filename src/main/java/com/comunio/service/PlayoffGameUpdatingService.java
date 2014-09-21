package com.comunio.service;

import java.io.Serializable;
import java.util.List;

import com.comunio.model.PlayoffGame;
import com.comunio.model.Result;

public interface PlayoffGameUpdatingService extends Serializable {

    PlayoffGame updatePlayoffGame(PlayoffGame playoffGame, List<Result> results);

}
