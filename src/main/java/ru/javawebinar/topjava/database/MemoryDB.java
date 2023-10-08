package ru.javawebinar.topjava.database;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.util.MealsUtil;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static ru.javawebinar.topjava.util.MealsUtil.*;

public class MemoryDB {
    private static AtomicInteger ID_COUNTER = new AtomicInteger(0);
    private static List<Meal> meals;
    private static final Map<Integer, MealTo> datebase = new ConcurrentHashMap<>();

    static {
         meals = Arrays.asList(
                new Meal(LocalDateTime.of(2023, Month.OCTOBER, 31, 10, 0), "Завтрак", 500),
                new Meal(LocalDateTime.of(2023, Month.OCTOBER, 31, 13, 0), "Обед", 1000),
                new Meal(LocalDateTime.of(2023, Month.OCTOBER, 31, 20, 0), "Ужин", 500),
                new Meal(LocalDateTime.of(2023, Month.NOVEMBER, 1, 0, 0), "Еда на граничное значение", 100),
                new Meal(LocalDateTime.of(2023, Month.NOVEMBER, 1, 10, 0), "Завтрак", 1000),
                new Meal(LocalDateTime.of(2023, Month.NOVEMBER, 1, 13, 0), "Обед", 500),
                new Meal(LocalDateTime.of(2023, Month.NOVEMBER, 1, 20, 0), "Ужин", 410)
        );
        iniDatabase(meals);
    }
    private static int getIncrementMealId(MealTo meal){
        int id = ID_COUNTER.incrementAndGet();
        meal.setId(id);
        return id;
    }

    public MemoryDB() {
    }

    public Map<Integer, MealTo> getMealsTo(){
        return datebase;
    }


    private static void iniDatabase(Collection<Meal> mealsCollection){
        List<Meal> meals = (List<Meal>) mealsCollection;
        List<MealTo> mealsTo = filteredByStreams(meals, LocalTime.of(0, 0), LocalTime.of(23, 59), CALORIES_PER_DAY);
        mealsTo.forEach(meal -> {
            datebase.put(getIncrementMealId(meal), meal);
        });
    }

    private static void initDatabase(){
        refreshMealsTo(datebase.values()).forEach(meal -> {
            datebase.put(meal.getId(), meal);
        });
    }

    public void saveOrUpdateMeal(MealTo mealTo){
        int id = mealTo.getId() == 0 ? getIncrementMealId(mealTo) : mealTo.getId();
        datebase.put(id, mealTo);
        initDatabase();
    }

    public void deleteMealById(int id){
        datebase.remove(id);
        initDatabase();
    }



}

