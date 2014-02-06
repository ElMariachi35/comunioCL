package com.comunio.dao;

import com.comunio.model.Fixture;

public interface FixtureDao {
	void addFixture(Fixture fixture);
	void updateFixture(Fixture fixture);
	Fixture getFixtureByGroupId(long groupId);
}
