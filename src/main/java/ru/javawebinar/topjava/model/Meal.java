package ru.javawebinar.topjava.model;

import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@NamedQueries({
        @NamedQuery(name = Meal.GET, query = "FROM Meal m WHERE m.id = :id AND m.user = :user"),
        @NamedQuery(name = Meal.ALL_SORTED, query = "FROM Meal WHERE user = :user ORDER BY dateTime DESC"),
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal m WHERE m.id = :id AND user = :user"),
        @NamedQuery(name = Meal.BETWEEN_HALF_OPEN, query = "FROM Meal WHERE user = :user  AND dateTime >= :startDateTime AND dateTime < :endDateTime ORDER BY dateTime DESC")
})
@Entity
@Table(name = "meal", uniqueConstraints = {
        @UniqueConstraint(columnNames = { "user_id", "date_time" })})
public class Meal extends AbstractBaseEntity {
    public static final String GET = "Meal.get";
    public static final String ALL_SORTED = "Meal.sorted";
    public static final String DELETE = "Meal.delete";
    public static final String BETWEEN_HALF_OPEN = "Meal.betweenHalfOpen";
    @Column(name = "date_time")
    @NotNull
    private LocalDateTime dateTime;
    @Column(name = "description")
    @NotBlank
    @Size(min = 2, max = 120)
    private String description;
    @Column(name = "calories")
    @Size(min = 5, max = 5000)
    private int calories;
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
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

    public User getUser(){
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
