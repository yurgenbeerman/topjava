package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by yurii.pyvovarenko on 3/27/2017.
 */
public class Meals {
    private static int idCounter = 0;

    private static List<Meal> meals = Arrays.asList(
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500, nextId()),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000, nextId()),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500, nextId()),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000, nextId()),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500, nextId()),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510, nextId())
    );

    private static int caloriesPerDay = 2000;

    public static Collection<Meal> getAll() {
        return meals;
    }

    public static int getCaloriesPerDay() {
        return caloriesPerDay;
    }

    public static synchronized void delete(int id) {
        for(int i=0; i < meals.size(); i++) {
            if (meals.get(i).getId() == id) {
                meals.remove(i);
                break;
            }
        }
    }

    public static synchronized Meal getById(int id) {
        for(int i=0; i < meals.size(); i++) {
            if (meals.get(i).getId() == id) {
                return meals.get(i);
            }
        }
        return null;
    }


    private static synchronized int nextId() {
        return idCounter++;
    }
}
