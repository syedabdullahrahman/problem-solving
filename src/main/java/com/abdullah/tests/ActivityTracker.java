package com.abdullah.tests;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class ActivityTracker {
    private List<Activity> activities = new ArrayList<>();

    public void addActivity(Activity activity) {
        activities.add(activity);
    }

    public double getTotalCaloriesBurned() {
        return activities.stream().mapToDouble(Activity::getCaloriesBurned).sum();
    }
        public Activity findHighestCalorieActivity () {
            return activities.stream().max((a, b) -> Double.compare(a.getCaloriesBurned(), b.getCaloriesBurned())).orElse(null);
        }
}

    class Activity {
        private String type;
        private double duration;
        private double caloriesBurned;

        public Activity(String type, double duration, double caloriesBurned) {
            this.type = type;
            this.duration = duration;
            this.caloriesBurned = caloriesBurned;
        }

        public double getCaloriesBurned() {
            return caloriesBurned;
        }
    }



class ActivityTrackerTest {

    @Test
    void testAddActivityAndTotalCalories() {
        // Arrange
        ActivityTracker tracker = new ActivityTracker();
        Activity running = new Activity("Running", 30, 300);
        Activity cycling = new Activity("Cycling", 45, 400);

        // Act
        tracker.addActivity(running);
        tracker.addActivity(cycling);

        // Assert
        assertEquals(700, tracker.getTotalCaloriesBurned(),
                "Total calories should be the sum of added activities");
    }

    @Test
    void testFindHighestCalorieActivity() {
        // Arrange
        ActivityTracker tracker = new ActivityTracker();
        Activity swimming = new Activity("Swimming", 60, 500);
        Activity hiking = new Activity("Hiking", 120, 600);

        tracker.addActivity(swimming);
        tracker.addActivity(hiking);

        // Act
        Activity highestCalorieActivity = tracker.findHighestCalorieActivity();

        // Assert
        assertEquals(hiking, highestCalorieActivity,
                "Hiking should be the highest calorie activity");
    }

    @Test
    void testTotalCaloriesWithNoActivities() {
        // Arrange
        ActivityTracker tracker = new ActivityTracker();

        // Act & Assert
        assertEquals(0, tracker.getTotalCaloriesBurned(),
                "Total calories should be 0 when no activities are added");
    }

    @Test
    void testFindHighestCalorieActivityWithNoActivities() {
        // Arrange
        ActivityTracker tracker = new ActivityTracker();

        // Act & Assert
        assertNull(tracker.findHighestCalorieActivity(),
                "Should return null when no activities are present");
    }

    @Test
    void testAddActivityWithZeroDuration() {
        // Arrange
        ActivityTracker tracker = new ActivityTracker();
        Activity rest = new Activity("Resting", 0, 0);

        // Act
        tracker.addActivity(rest);

        // Assert
        assertEquals(0, tracker.getTotalCaloriesBurned(),
                "Total calories should remain 0 for an activity with zero duration");
    }

    @Test
    void testAddActivityWithZeroDurationAndNonZeroCalories() {
        // Arrange
        ActivityTracker tracker = new ActivityTracker();
        Activity shortWorkout = new Activity("Short Workout", 0, 150);

        // Act
        tracker.addActivity(shortWorkout);

        // Assert
        assertEquals(150, tracker.getTotalCaloriesBurned(),
                "Total calories should account for activities with zero duration but non-zero calories");
    }

    @Test
    void testAddActivityWithNegativeValues() {
        // Arrange
        ActivityTracker tracker = new ActivityTracker();
        Activity invalidActivity = new Activity("Invalid Activity", -30, -200);

        // Act
        tracker.addActivity(invalidActivity);

        // Assert
        assertEquals(-200, tracker.getTotalCaloriesBurned(),
                "Total calories should correctly handle negative calorie values, though they represent invalid input");
    }

    @Test
    void testFindHighestCalorieActivityWithTies() {
        // Arrange
        ActivityTracker tracker = new ActivityTracker();
        Activity cycling = new Activity("Cycling", 60, 300);
        Activity rowing = new Activity("Rowing", 45, 300);

        tracker.addActivity(cycling);
        tracker.addActivity(rowing);

        // Act
        Activity highestCalorieActivity = tracker.findHighestCalorieActivity();

        // Assert
        assertNotNull(highestCalorieActivity,
                "Method should return one of the highest calorie activities even if there's a tie");
        assertEquals(300, highestCalorieActivity.getCaloriesBurned(),
                "The highest calorie activity should have 300 calories burned");
    }

    @Test
    void testExtremeValuesForCaloriesAndDuration() {
        // Arrange
        ActivityTracker tracker = new ActivityTracker();
        Activity intenseWorkout = new Activity("Intense Workout", Double.MAX_VALUE, Double.MAX_VALUE);

        tracker.addActivity(intenseWorkout);

        // Act & Assert
        assertEquals(Double.MAX_VALUE, tracker.findHighestCalorieActivity().getCaloriesBurned(),
                "Highest calorie activity should handle large values without overflow");

        assertEquals(Double.MAX_VALUE, tracker.getTotalCaloriesBurned(),
                "Total calories should accurately reflect large values without overflow");
    }
}
