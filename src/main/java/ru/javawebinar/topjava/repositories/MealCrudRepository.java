package ru.javawebinar.topjava.repositories;


import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface MealCrudRepository {
    void createMeal(Meal meal);
    void updateMeal(Meal meal);
    void deleteById(Integer id);
    Meal getMealById(Integer id);
    List<MealTo> getMealsTo();
}
