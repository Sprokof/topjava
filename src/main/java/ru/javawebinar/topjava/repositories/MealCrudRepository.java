package ru.javawebinar.topjava.repositories;


import ru.javawebinar.topjava.model.MealTo;

import java.util.Collection;
import java.util.List;
import java.util.Set;

public interface MealCrudRepository {
    void saveMeal(MealTo meal);
    void updateMeal(MealTo meal);
    void deleteById(Integer id);
    MealTo getMealToById(Integer id);
    Collection<MealTo> getMealsTo();
}
