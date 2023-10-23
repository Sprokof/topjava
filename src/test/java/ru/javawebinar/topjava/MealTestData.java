package ru.javawebinar.topjava;

import ru.javawebinar.topjava.model.Meal;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static ru.javawebinar.topjava.model.AbstractBaseEntity.START_SEQ;

public class MealTestData {
    public static final int FOREIGN_MEAL_ID = START_SEQ + 9;
    public static final int MEAL_ID = START_SEQ + 3;
    public static final int NOT_FOUND = 50;
    public static final LocalDateTime START_DATE_TIME = LocalDateTime.of(2023, 10, 18, 10, 0);
    public static final LocalDateTime END_DATE_TIME = LocalDateTime.of(2023, 10, 21, 14, 30);
    public static final Meal meal1 = new Meal(START_SEQ + 3, LocalDateTime.of(2023, 10, 21, 10, 18), "завтрак", 350);
    public static final Meal meal2 = new Meal(START_SEQ + 4, LocalDateTime.of(2023, 10, 21, 13, 20), "обед", 870);
    public static final Meal meal3 = new Meal(START_SEQ + 5, LocalDateTime.of(2023, 10, 21, 18, 20), "ужин", 800);
    public static final Meal meal4 = new Meal(START_SEQ + 6, LocalDateTime.of(2023, 10, 22, 10, 15), "завтрак", 800);
    public static final Meal meal5 = new Meal(START_SEQ + 7, LocalDateTime.of(2023, 10, 22, 14, 30), "обед", 800);
    public static final Meal meal6 = new Meal(START_SEQ + 8, LocalDateTime.of(2023, 10, 22, 19, 0), "ужин", 800);


    public static Meal getNew() {
        return new Meal(LocalDateTime.of(2023, 10, 22, 17, 15), "ужин", 500);
    }

    public static void assertMatch(Meal actual, Meal expected) {
        assertThat(actual).usingRecursiveComparison().ignoringFields("id").isEqualTo(expected);
    }

    public static void assertMatch(Iterable<Meal> actual, Meal... expected) {
        assertMatch(actual, Arrays.asList(expected));
    }

    public static void assertMatch(Iterable<Meal> actual, Iterable<Meal> expected) {
        assertThat(actual).isEqualTo(expected);
    }

    public static Meal getUpdated() {
        Meal updated = new Meal(meal1);
        updated.setId(MEAL_ID);
        updated.setDateTime(LocalDateTime.of(2023, 10, 21, 11, 11));
        updated.setDescription("description");
        updated.setCalories(170);
        return updated;
    }

}
