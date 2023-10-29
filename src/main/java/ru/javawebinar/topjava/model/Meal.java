package ru.javawebinar.topjava.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NamedNativeQueries({
        @NamedNativeQuery(name = Meal.GET, query = "SELECT * FROM Meal " +
                "WHERE id = :id AND user_id = :user_id",
                resultClass = Meal.class),
        @NamedNativeQuery(name = Meal.ALL_SORTED, query = "SELECT * FROM Meal WHERE user_id = :user_id ORDER BY date_time DESC",
                resultClass = Meal.class),
        @NamedNativeQuery(name = Meal.DELETE, query = "DELETE FROM Meal WHERE id = :id AND user_id = :user_id"),
        @NamedNativeQuery(name = Meal.BETWEEN_HALF_OPEN, query = "SELECT * FROM meal WHERE user_id = :user_id  AND date_time >= :startDateTime " +
                "AND date_time < :endDateTime ORDER BY date_time DESC")
})
@Entity
@Table(name = "meal")
public class Meal extends AbstractBaseEntity {
    public static final String GET = "Meal.get";
    public static final String ALL_SORTED = "Meal.sorted";
    public static final String DELETE = "Meal.delete";
    public static final String BETWEEN_HALF_OPEN = "Meal.betweenHalfOpen";
    @Column(name = "date_time")
    @NotNull
    @NotBlank
    private LocalDateTime dateTime;
    @Column(name = "description")
    @NotNull
    @NotBlank
    private String description;
    @Column(name = "calories")
    private int calories;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    public Meal() {
    }

    public Meal(LocalDateTime dateTime, String description, int calories) {
        this(null, dateTime, description, calories);
    }

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public int getCalories() {
        return calories;
    }

    public LocalDate getDate() {
        return dateTime.toLocalDate();
    }

    public LocalTime getTime() {
        return dateTime.toLocalTime();
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setCalories(int calories) {
        this.calories = calories;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Meal{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", description='" + description + '\'' +
                ", calories=" + calories +
                '}';
    }
}
