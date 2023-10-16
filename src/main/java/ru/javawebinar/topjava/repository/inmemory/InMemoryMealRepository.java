package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import ru.javawebinar.topjava.web.SecurityUtil;


import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer,Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(SecurityUtil.authUserId(), meal));

    }

    @Override
    public Meal save(Integer userId, Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());;
            return repository.computeIfAbsent(userId, id -> new ConcurrentHashMap<>()).put(meal.getId(), meal);

        }
        return repository.get(userId).computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int userId, int id) {
        return repository.getOrDefault(userId, new ConcurrentHashMap<>()).remove(id) != null;
    }

    @Override
    public Meal get(int userId, int id) {
        return repository.getOrDefault(userId, new ConcurrentHashMap<>()).get(id);
    }

    @Override
    public List<Meal> getAll(int userId) {
        List<Meal> userMeals = new ArrayList<>(repository.get(userId).values());
        Collections.reverse(userMeals);
        return userMeals;
    }

}

