package ru.javawebinar.topjava.repository.database;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import ru.javawebinar.topjava.ActiveDbProfileResolver;
import ru.javawebinar.topjava.repository.UserRepository;

@ActiveProfiles(resolver = ActiveDbProfileResolver.class, profiles = {"datajpa"})
public class DataJpaUserRepositoryTest extends BaseUserRepositoryTest {

    @Autowired
    private UserRepository repository;
    @Override
    protected UserRepository getRepository() {
        return this.repository;
    }

    @Override
    public void save() {
        super.save();
    }

    @Override
    public void duplicateEmailCreate() {
        super.duplicateEmailCreate();
    }

    @Override
    public void delete() {
        super.delete();
    }

    @Override
    public void get() {
        super.get();
    }

    @Override
    public void getByEmail() {
        super.getByEmail();
    }

    @Override
    public void getAll() {
        super.getAll();
    }
}
