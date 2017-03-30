package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * GKislin
 * 11.01.2015.
 */
public class MealWithExceed {
    //private static final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm");//2015-05-30T10:00
    private final LocalDateTime dateTime; //use String type when using dateTimeFormatter

    private final String description;

    private final int calories;

    private final boolean exceed;

    private final long id;

    public MealWithExceed(LocalDateTime dateTime, String description, int calories, boolean exceed, long id) {
        this.dateTime = dateTime;//.format(dateTimeFormatter);
        this.description = description;
        this.calories = calories;
        this.exceed = exceed;
        this.id = id;
    }

    @Override
    public String toString() {
        return "UserMealWithExceed{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                ", exceed=" + exceed +
                '}';
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public boolean isExceed() {
        return exceed;
    }

    public long getId() {
        return id;
    }
}
