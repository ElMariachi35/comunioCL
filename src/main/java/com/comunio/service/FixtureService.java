package com.comunio.service;

import com.comunio.model.Fixture;
import com.comunio.model.Groupe;

public interface FixtureService {
	public void createFixture(Groupe group);
	public Fixture getFixture(Groupe group);
}
