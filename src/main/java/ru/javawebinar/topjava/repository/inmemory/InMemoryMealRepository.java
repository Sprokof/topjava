package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.MealsUtil;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final int userId = 1;
    private final Map<Integer,Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(userId, meal));

    }

    @Override
    public Meal save(int userId, Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());;
            repository.computeIfAbsent(userId, id -> new ConcurrentHashMap<>()).put(meal.getId(), meal);
            return meal;
        }
        return repository.get(userId).computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int userId, int id) {
        return Optional.ofNullable(repository.get(userId)).isPresent() && repository.get(userId).remove(id) != null;
    }

    @Override
    public Meal get(int userId, int id) {
        return Optional.ofNullable(repository.get(userId)).isPresent() ? repository.get(userId).get(id) : null;

    }

    @Override
    public List<Meal> getAll(int userId) {
        List<Meal> userMeals = repository.get(userId).values().stream()
                .sorted(Comparator.comparing(Meal::getDateTime))
                .collect(Collectors.toList());
        Collections.reverse(userMeals);
        return userMeals;
    }


}

