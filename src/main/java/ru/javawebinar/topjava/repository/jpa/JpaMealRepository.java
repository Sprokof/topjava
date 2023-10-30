package ru.javawebinar.topjava.repository.jpa;

import org.springframework.dao.support.DataAccessUtils;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public class JpaMealRepository implements MealRepository {

    @PersistenceContext
    private EntityManager em;

    @Override
    public Meal save(Meal meal, int userId) {
        meal.setUser(em.find(User.class, userId));
        if(meal.isNew()){
            em.persist(meal);
            return meal;
        }
        return get(meal.id(), userId) != null ? em.merge(meal) : null;
    }

    @Override
    public boolean delete(int id, int userId) {
        return get(id, userId) != null && em.createNamedQuery(Meal.DELETE)
                .setParameter("id", id)
                .setParameter("user", get(id, userId).getUser())
                .executeUpdate() != 0;
    }

    @Override
    public Meal get(int id, int userId) {
        User user = em.find(User.class, userId);
        List<Meal> meals = em.createNamedQuery(Meal.GET, Meal.class)
                .setParameter("id", id)
                .setParameter("user", user)
                .getResultList();
        return DataAccessUtils.singleResult(meals);

    }

    @Override
    public List<Meal> getAll(int userId) {
        return em.createNamedQuery(Meal.ALL_SORTED, Meal.class)
                .setParameter("user", em.find(User.class, userId))
                .getResultList();
    }

    @Override
    public List<Meal> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return em.createNamedQuery(Meal.BETWEEN_HALF_OPEN, Meal.class)
                .setParameter("user", em.find(User.class, userId))
                .setParameter("startDateTime", startDateTime)
                .setParameter("endDateTime", endDateTime)
                .getResultList();
    }
}