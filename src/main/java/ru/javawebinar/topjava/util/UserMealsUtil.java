package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.UserMeal;
import ru.javawebinar.topjava.model.UserMealWithExcess;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class UserMealsUtil {
    public static void main(String[] args) {
        List<UserMeal> meals = Arrays.asList(
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 10, 0), "Завтрак", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 13, 0), "Обед", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 30, 20, 0), "Ужин", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 0, 0), "Еда на граничное значение", 100),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 10, 0), "Завтрак", 1000),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 13, 0), "Обед", 500),
                new UserMeal(LocalDateTime.of(2020, Month.JANUARY, 31, 20, 0), "Ужин", 410)
        );

        List<UserMealWithExcess> mealsTo = filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000);
        mealsTo.forEach(System.out::println);

//        System.out.println(filteredByStreams(meals, LocalTime.of(7, 0), LocalTime.of(12, 0), 2000));
    }

    public static List<UserMealWithExcess> filteredByCycles(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO return filtered list with excess. Implement by cycles
        List<UserMealWithExcess> userMealWithExcesses = new ArrayList<>();
        int calories = 0;
        for (int i = 0; i < meals.size(); i++) {
            UserMeal meal = meals.get(i);
            boolean excess = (calories += meal.getCalories()) > caloriesPerDay;
            if ((i + 1 < meals.size())) {
                if (getDayOfMonth(meal) != getDayOfMonth(meals.get(i + 1))) {
                    calories = 0;
                }
            }
            if (mealFilterConditions(meal.getDateTime().toLocalTime(), startTime, endTime)) {
                userMealWithExcesses.add(new UserMealWithExcess(meal.getDateTime(),
                        meal.getDescription(), meal.getCalories(), excess));
            }
        }
        return userMealWithExcesses;
    }

    public static List<UserMealWithExcess> filteredByStreams(List<UserMeal> meals, LocalTime startTime, LocalTime endTime, int caloriesPerDay) {
        // TODO Implement by streams
        final int[] data = {0, 0};
        return meals.stream().map(meal -> {
            if (data[0] != getDayOfMonth(meal)) data[1] = 0;
            data[0] = getDayOfMonth(meal);
            boolean excess = (data[1] += meal.getCalories()) > caloriesPerDay;
            return new UserMealWithExcess(meal.getDateTime(), meal.getDescription(),
                    meal.getCalories(), excess);
        }).filter(meal -> mealFilterConditions(meal.getDateTime().
                toLocalTime(), startTime, endTime)).collect(Collectors.toList());
    }


    private static boolean mealFilterConditions(LocalTime mealTime, LocalTime startTime, LocalTime endTime) {
        return (mealTime.isAfter(startTime) || mealTime.equals(startTime))
                && (mealTime.isBefore(endTime) || mealTime.equals(endTime));
    }

    private static int getDayOfMonth(UserMeal meal){
        return meal.getDateTime().toLocalDate().getDayOfMonth();
    }


}
