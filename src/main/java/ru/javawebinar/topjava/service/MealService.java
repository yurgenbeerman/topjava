package ru.javawebinar.topjava.service;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import java.util.Collection;

public interface MealService {
    Meal save(Meal meal);

    void delete(int id) throws NotFoundException;

    Meal get(int id) throws NotFoundException;

    Collection<Meal> getAll();

    void update(Meal meal);

    boolean belongsToUser(int id, int userId);

    boolean belongsToUser(Meal meal, int userId);
}