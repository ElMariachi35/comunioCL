package com.comunio.service;

import com.comunio.model.KnockoutPairing;
import com.comunio.model.PlayoffFixture;
import com.comunio.model.Team;

public interface KnockoutPairingService {

    KnockoutPairing createPairing(Team team1, Team team2, PlayoffFixture fixture);

}
