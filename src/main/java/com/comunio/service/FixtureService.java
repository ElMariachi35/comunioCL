package com.comunio.service;

import com.comunio.model.Groupe;
import com.comunio.model.Fixture;

public interface FixtureService {
	public void createFixture(Groupe group);
	public Fixture getFixture(Groupe group);
}
