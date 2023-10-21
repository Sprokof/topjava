package ru.javawebinar.topjava.repository;


import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.SqlConfig;
import org.springframework.test.context.junit4.SpringRunner;
import ru.javawebinar.topjava.model.Meal;

import java.util.List;

import static org.junit.Assert.assertTrue;
import static ru.javawebinar.topjava.MealTestData.*;
import static ru.javawebinar.topjava.UserTestData.USER_ID;

@ContextConfiguration({
        "classpath:spring/spring-app.xml",
        "classpath:spring/spring-db.xml"
})
@RunWith(SpringRunner.class)
@Sql(scripts = "classpath:db/populateDB.sql", config = @SqlConfig(encoding = "UTF-8"))
public class JdbcMealRepositoryTest {

    @Autowired
    private MealRepository repository;

    @Test
    public void save(){
        Meal created = repository.save(getNew(), USER_ID);
        Integer newId = created.getId();
        Meal newMeal = getNew();
        newMeal.setId(newId);
        assertMatch(created, newMeal);
        assertMatch(repository.get(newId, USER_ID), newMeal);
    }

    @Test
    public void update(){
        Meal updated = getUpdated();
        repository.save(updated, USER_ID);
        assertMatch(repository.get(MEAL_ID, USER_ID), getUpdated());
    }

    @Test
    public void delete(){
        boolean actual = repository.delete(MEAL_ID, USER_ID);
        assertTrue(actual);
    }

    @Test
    public void get(){
        assertMatch(repository.get(MEAL_ID, USER_ID), meal1);
    }

    @Test
    public void getAll(){
        List<Meal> all = repository.getAll(USER_ID);
        assertMatch(all, meal3, meal2, meal1);
    }

    @Test
    public void getBetweenHalfOpen(){
        List<Meal> meals = repository.getBetweenHalfOpen(START_DATE_TIME, END_DATE_TIME, USER_ID);
        assertMatch(meals, meal2, meal1);
    }
}
