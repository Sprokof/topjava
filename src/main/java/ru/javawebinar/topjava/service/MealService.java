package ru.javawebinar.topjava.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.MealsUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static ru.javawebinar.topjava.util.ValidationUtil.checkNotFoundWithId;


@Service
public class MealService {

    @Autowired
    private final MealRepository repository;

    public MealService(MealRepository repository){
        this.repository = repository;
    }

    public void delete(int userId, int id) {
        checkNotFoundWithId(repository.delete(userId, id), id);
    }

    public Meal get(int userId, int id) {
        return checkNotFoundWithId(repository.get(userId, id), id);
    }

    public List<MealTo> getAll() {
        List<Meal> meals = repository.getAll().stream()
                .sorted((m1, m2) -> DateTimeUtil.compareDates(m1.getDateTime(), m2.getDateTime()))
                .collect(Collectors.toList());
        return MealsUtil.getTos(meals, MealsUtil.DEFAULT_CALORIES_PER_DAY);
    }

    public void createOrUpdate(int userId, Meal meal){
        checkNotFoundWithId(repository.save(userId, meal), meal.getId());
    }

    public Meal create(int userId, Meal meal){
        checkNotFoundWithId(repository.save(userId, meal), meal.getId());
        return meal;

    }

    public void update(int userId, Meal meal){
        checkNotFoundWithId(repository.save(userId, meal), meal.getId());
    }

}