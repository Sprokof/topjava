package ru.javawebinar.topjava.repositories;

import ru.javawebinar.topjava.database.MemoryDB;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalTime;
import java.util.*;

public class InMemoryCrudRepository implements MealCrudRepository {
    private final MemoryDB memoryDB = new MemoryDB();
    @Override
    public void deleteById(Integer id) {
        memoryDB.getDatabase().remove(id);
    }

    @Override
    public void createMeal(Meal meal) {
        if(memoryDB.getDatabase().get(meal.getId()) != null){
            memoryDB.getDatabase().put(memoryDB.setNewId(meal), meal);
        }
        memoryDB.getDatabase().put(meal.getId(), meal);
    }

    @Override
    public void updateMeal(Meal meal) {
        memoryDB.getDatabase().put(meal.getId(), meal);
    }

    @Override
    public List<MealTo> getMealsTo() {
        List<Meal> meals = new ArrayList<>(memoryDB.getDatabase().values());
        return MealsUtil.filteredByStreams(meals, LocalTime.of(0, 0),
                LocalTime.of(23, 59), MealsUtil.CALORIES_PER_DAY);
    }

    @Override
    public Meal getMealById(Integer id) {
        return memoryDB.getDatabase().get(id);
    }
}
