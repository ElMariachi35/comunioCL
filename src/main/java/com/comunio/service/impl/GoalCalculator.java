package com.comunio.service.impl;

public class GoalCalculator {
    private static final int GOAL_DIVISOR = 10;

    public int calculateGoalsFromPoints(int points) {
        if (points <= 0) {
            return 0;
        }
        return (int) Math.round((double) points / GOAL_DIVISOR);
    }

}
