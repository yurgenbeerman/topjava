package ru.javawebinar.topjava.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.exception.NotFoundException;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public class JpaMealRepositoryImpl implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    @Transactional
    public Meal save(Meal meal, int userId) {
        User userRef = em.getReference(User.class, userId);
        meal.setUser(userRef);
        //em.persist(meal);

        if (meal.isNew()) {
            em.persist(meal);
            return meal;
        } else if (meal.getUser().getId()==userId) { //getUser() makes DB request - TODO fix it!
            return em.merge(meal);
        } else {
            throw new NotFoundException("Meal with id=" + meal.getId() + " is not new and was created by user with id different from "+ userId +". Cannot update it.");
            //return null;
        }
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return em.createNamedQuery(Meal.DELETE).setParameter("id", id).setParameter("userId", userId).executeUpdate() != 0;
    }

    @Override
    @Transactional
    public Meal get(int id, int userId) {
        Meal meal = em.find(Meal.class, id);
        User userRef = em.getReference(User.class, userId);
        if (meal.getUser().getId()==userRef.getId()) {//getUser() makes DB request - TODO fix it!
            return meal;
        } else {
            throw new NotFoundException("Meal with id=" + meal.getId() + " was created by user with id different from " + userId + ". Cannot get it.");
        }
    }

    @Override
    public List<Meal> getAll(int userId) {
        User userRef = em.getReference(User.class, userId);
        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class).setParameter("user", userRef).getResultList();
    }

    @Override
    public List<Meal> getBetween(LocalDateTime startDate, LocalDateTime endDate, int userId)
    {
        return em.createNamedQuery(Meal.BETWEEN_DATE_TIME, Meal.class).setParameter("startDateTime", startDate).setParameter("endDateTime", endDate).getResultList();
    }
}