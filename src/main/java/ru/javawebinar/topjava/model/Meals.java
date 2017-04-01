package ru.javawebinar.topjava.model;

import java.time.LocalDateTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

/**
 * Created by yurii.pyvovarenko on 3/27/2017.
 */
public class Meals {
    private static int idCounter = 0;

    private static ArrayList<Meal> meals = new ArrayList<Meal>(Arrays.asList(
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 10, 0), "Завтрак", 500, nextId()),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 13, 0), "Обед", 1000, nextId()),
            new Meal(LocalDateTime.of(2015, Month.MAY, 30, 20, 0), "Ужин", 500, nextId()),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 10, 0), "Завтрак", 1000, nextId()),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 13, 0), "Обед", 500, nextId()),
            new Meal(LocalDateTime.of(2015, Month.MAY, 31, 20, 0), "Ужин", 510, nextId())
    ));

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

    public static synchronized void  add(LocalDateTime dateTime, String description, int calories) {
        meals.add(new Meal(dateTime, description, calories, nextId()));
    }

    public static synchronized void edit(int id, LocalDateTime dateTime, String description, int calories) {
        Meal meal = getById(id);
        meal.setDateTime(dateTime);
        meal.setDescription(description);
        meal.setCalories(calories);
    }

    private static synchronized int nextId() {
        return idCounter++;
    }
}
