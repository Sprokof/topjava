package ru.javawebinar.topjava.repository.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.ActiveDbProfileResolver;
import ru.javawebinar.topjava.repository.MealRepository;

@ActiveProfiles(resolver = ActiveDbProfileResolver.class, profiles = {"jdbc"})
public class JdbcMealRepositoryTest extends BaseMealRepositoryTest {

    @Autowired
    private MealRepository mealRepository;

    @Override
    protected MealRepository getRepository() {
        return this.mealRepository;
    }

    @Override
    public void getAll() {
        super.getAll();
    }

    @Override
    public void delete() {
        super.delete();
    }

    @Override
    public void duplicateDateTimeCreate() {
        super.duplicateDateTimeCreate();
    }

    @Override
    public void get() {
        super.get();
    }

    //not work with hsqldb
    @Override
    public void getBetweenHalfOpen() {
        super.getBetweenHalfOpen();
    }

    @Override
    public void save() {
        super.save();
    }
}
