package com.comunio.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.comunio.dao.GameDao;
import com.comunio.dao.GroupDao;
import com.comunio.model.Game;
import com.comunio.model.Groupe;
import com.comunio.model.Team;
import com.comunio.service.ComunioService;
import com.comunio.service.GameService;
import com.comunio.service.GroupService;
import com.comunio.service.ScheduleService;
import com.comunio.service.TeamService;

@Service
public class GameServiceImpl implements GameService {
	@Autowired
	private GameDao gameDao;

	@Transactional
	public void saveGame(Game game) {
		gameDao.saveMatch(game);
	}


}
