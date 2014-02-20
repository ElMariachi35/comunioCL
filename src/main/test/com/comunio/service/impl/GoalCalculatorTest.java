package com.comunio.service.impl;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class GoalCalculatorTest {

    private GoalCalculator goalCalculator = new GoalCalculator();

    @Test
    public void fourPointsEqualZeroGoals() {
        assertEquals(0, goalCalculator.calculateGoalsFromPoints(4));
    }

    @Test
    public void fivePointsEqualOneGoal() {
        assertEquals(1, goalCalculator.calculateGoalsFromPoints(5));
    }

    @Test
    public void twentyPointsEqualTwoGoals() {
        assertEquals(2, goalCalculator.calculateGoalsFromPoints(20));
    }

    @Test
    public void thirtyNinePointsEqualFourGoals() {
        assertEquals(4, goalCalculator.calculateGoalsFromPoints(39));
    }

    @Test
    public void negativPointsEqualZeroGoals() {
        assertEquals(0, goalCalculator.calculateGoalsFromPoints(-5));
    }

    @Test
    public void zeroPointsEqualZeroGoals() {
        assertEquals(0, goalCalculator.calculateGoalsFromPoints(0));
    }
}
