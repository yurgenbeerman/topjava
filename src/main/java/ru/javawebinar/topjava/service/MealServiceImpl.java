package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import java.util.Collection;
import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;

@Service
public class MealServiceImpl implements MealService {

    @Autowired
    private MealRepository repository;

    @Override
    public Meal save(Meal meal) {
        return repository.save(meal);
    }

    @Override
    public void delete(int id) {
        checkNotFoundWithId(repository.delete(id), id);
    }

    @Override
    public Meal get(int id) throws NotFoundException {
        return checkNotFoundWithId(repository.get(id), id);
    }

    @Override
    public Collection<Meal> getAll() {
        return repository.getAll();
    }

    @Override
    public void update(Meal meal) {
        repository.save(meal);
    }

    @Override
    public boolean belongsToUser(int id, int userId) throws NotFoundException {
        Meal meal = repository.get(id);
        if (meal == null) {
            throw new NotFoundException("Meal with such id not found: " + id);
        }
        if (meal.getUserId() == userId) {
            return true;
        }
        throw new NotFoundException("Meal with id " + id + " does not belong to User with id " + userId);
    }

    @Override
    public boolean belongsToUser(Meal meal, int userId) throws NotFoundException {
        if (meal.getUserId() == userId) {
            return true;
        }
        throw new NotFoundException("Meal with id " + meal.getId() + " does not belong to User with id " + userId);
    }

}