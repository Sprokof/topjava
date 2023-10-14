package ru.javawebinar.topjava.repository.inmemory;

import org.springframework.stereotype.Repository;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;

@Repository
public class InMemoryMealRepository implements MealRepository {
    private final Map<Integer,Map<Integer, Meal>> repository = new ConcurrentHashMap<>();
    private final AtomicInteger counter = new AtomicInteger(0);

    {
        MealsUtil.meals.forEach(meal -> save(meal.getUserId(), meal));

    }

    @Override
    public Meal save(int userId, Meal meal) {
        if (meal.isNew()) {
            meal.setId(counter.incrementAndGet());
            addAuthUserMeal(userId, meal);
            return meal;
        }
        if(!repository.containsKey(userId)) return null;
        return repository.get(userId).computeIfPresent(meal.getId(), (id, oldMeal) -> meal);
    }

    @Override
    public boolean delete(int userId, int id) {
        if(repository.containsKey(userId)) {
            return repository.get(userId).remove(id) != null;
        }
        return false;
    }

    @Override
    public Meal get(int userId, int id) {
        if(!repository.containsKey(userId)){
            return null;
        }
        return repository.get(userId).get(id);
    }

    @Override
    public Collection<Meal> getAll() {
        return repository.values().
                stream().flatMap(map -> map.values().stream())
                .sorted((m1, m2) -> DateTimeUtil.compareDates(m1.getDateTime(), m2.getDateTime()))
                .collect(Collectors.toList());
    }

    private void addAuthUserMeal(int userId, Meal meal){
        if(!repository.containsKey(userId)){
            repository.put(userId, new ConcurrentHashMap<>());
        }
        repository.get(userId).put(meal.getId(), meal);
    }

}

