package ru.javawebinar.topjava.repositories;

import ru.javawebinar.topjava.database.MemoryDB;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class InMemoryCrudRepository implements MealCrudRepository {
    private final MemoryDB memoryDB = new MemoryDB();
    @Override
    public void deleteById(Integer id) {
        memoryDB.deleteMealById(id);
    }

    @Override
    public void saveMeal(MealTo meal) {
        memoryDB.saveOrUpdateMeal(meal);
    }

    @Override
    public void updateMeal(MealTo meal) {
        memoryDB.saveOrUpdateMeal(meal);
    }

    @Override
    public Collection<MealTo> getMealsTo() {
        return memoryDB.getMealsTo().values();
    }

    @Override
    public MealTo getMealToById(Integer id) {
        return memoryDB.getMealsTo().get(id);
    }
}
