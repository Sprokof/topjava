package ru.javawebinar.topjava.web.meal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MealRestController {

    @Autowired
    private MealService mealService;

    @Autowired
    private UserService userService;

    public List<MealTo> getAll() {
        User user = userService.get(SecurityUtil.authUserId());
        return mealService.getAll(user.getId(), user.getCaloriesPerDay());
    }

    public List<MealTo> getFilteredAll(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime){
        return getAll().stream()
                .filter(meal -> DateTimeUtil.isBetweenDates(meal.getDateTime().toLocalDate(), startDate, endDate))
                .filter(meal -> DateTimeUtil.isBetweenHalfOpen(meal.getDateTime().toLocalTime(), startTime, endTime))
                .collect(Collectors.toList());
    }

    public Meal get(int id) {
        return mealService.get(SecurityUtil.authUserId(), id);
    }
    public Meal create(Meal meal) {
        return mealService.create(SecurityUtil.authUserId(), meal);
    }

    public void delete(int id) {
        mealService.delete(SecurityUtil.authUserId(), id);
    }

    public void update(Meal meal) {
        mealService.update(SecurityUtil.authUserId(), meal);
    }
}