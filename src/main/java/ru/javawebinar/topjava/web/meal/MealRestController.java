package ru.javawebinar.topjava.web.meal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.to.MealTo;
import ru.javawebinar.topjava.util.DateTimeUtil;
import ru.javawebinar.topjava.util.ValidationUtil;
import ru.javawebinar.topjava.web.SecurityUtil;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class MealRestController {
    protected final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    private MealService mealService;

    public List<MealTo> getAll() {
        log.info("getAll");
        return mealService.getAll(SecurityUtil.authUserId(), SecurityUtil.authUserCaloriesPerDay());
    }

    public List<MealTo> getFilteredAll(LocalDate startDate, LocalDate endDate, LocalTime startTime, LocalTime endTime){
        log.info("getAll");
        return getAll().stream()
                .filter(meal -> DateTimeUtil.isBetweenDates(meal.getDateTime().toLocalDate(), startDate, endDate))
                .filter(meal -> DateTimeUtil.isBetweenHalfOpen(meal.getDateTime().toLocalTime(), startTime, endTime))
                .collect(Collectors.toList());
    }

    public Meal get(int id) {
        log.info("get {}", id);
        return mealService.get(SecurityUtil.authUserId(), id);
    }
    public Meal create(Meal meal) {
        log.info("create {}", meal);
        return mealService.create(SecurityUtil.authUserId(), meal);
    }
    public void delete(int id) {
        log.info("delete {}", id);
        mealService.delete(SecurityUtil.authUserId(), id);
    }
    public void update(Meal meal, int id) {
        log.info("update {} with id={}", meal, id);
        mealService.update(SecurityUtil.authUserId(), meal);
        ValidationUtil.assureIdConsistent(meal, id);
    }
}