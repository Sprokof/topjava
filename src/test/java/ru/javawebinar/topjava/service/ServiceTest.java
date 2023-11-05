package ru.javawebinar.topjava.service;

import org.junit.AfterClass;
import org.junit.Rule;
import org.junit.rules.Stopwatch;
import org.junit.runner.Description;
import org.slf4j.Logger;

import java.util.concurrent.TimeUnit;

import static org.slf4j.LoggerFactory.getLogger;

public abstract class ServiceTest {
    private static final Logger log = getLogger("result");
    private static final StringBuilder results = new StringBuilder();
    void create(){}
    void delete(){}
    void deletedNotFound(){}
    void deleteNotOwn(){}
    void duplicateDateTimeCreate(){}
    void duplicateMailCreate(){}
    void get(){}
    void getByEmail(){}
    void getNotFound(){}
    void getNotOwn(){}
    void update(){}
    void updateNotOwn(){}
    void getAll(){}
    void getBetweenInclusive(){}
    void getBetweenWithNullDates(){}


    @Rule
    public final Stopwatch getStopWatch = new Stopwatch() {
        @Override
        protected void finished(long nanos, Description description) {
            String result = String.format("\n%-25s %7d", description.getMethodName(), TimeUnit.NANOSECONDS.toMillis(nanos));
            results.append(result);
            log.info(result + " ms\n");
        }
    };

    @AfterClass
    public static void printResult() {
        log.info("\n---------------------------------" +
                "\nTest                 Duration, ms" +
                "\n---------------------------------" +
                results +
                "\n---------------------------------");
    }
}
