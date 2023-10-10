package ru.javawebinar.topjava.database;

import ru.javawebinar.topjava.model.Meal;
import java.time.LocalDateTime;
import java.time.Month;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class MemoryDB {
    private final AtomicInteger idCounter = new AtomicInteger(0);
    private static final List<Meal> meals;
    private final Map<Integer, Meal> database = new ConcurrentHashMap<>();

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

    }
    public int setNewId(Meal meal){
        int id = idCounter.incrementAndGet();
        meal.setId(id);
        return id;
    }

    public MemoryDB() {
        iniDatabase();
    }

    public Map<Integer, Meal> getDatabase(){
        return this.database;
    }


    private void iniDatabase(){
        meals.forEach(meal -> {
            database.put(setNewId(meal), meal);
        });
    }

}

