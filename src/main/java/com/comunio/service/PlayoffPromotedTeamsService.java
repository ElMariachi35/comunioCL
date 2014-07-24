package com.comunio.service;

import com.comunio.model.KnockoutPairing;
import com.comunio.model.Team;

public interface PlayoffPromotedTeamsService {

    Team determinePromotedTeam(KnockoutPairing pairing);

}
