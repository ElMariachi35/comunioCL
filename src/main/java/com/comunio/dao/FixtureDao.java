package com.comunio.dao;

import com.comunio.model.Fixture;

public interface FixtureDao {
	void saveFixture(Fixture fixture);
	void updateFixture(Fixture fixture);
	Fixture getFixtureByGroupId(long groupId);
}
