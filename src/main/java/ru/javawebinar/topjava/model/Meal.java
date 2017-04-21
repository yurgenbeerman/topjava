package ru.javawebinar.topjava.model;

import org.hibernate.validator.constraints.Range;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

/**
 * GKislin
 * 11.01.2015.
 */
@NamedQueries({
        @NamedQuery(name = Meal.DELETE, query = "DELETE FROM Meal m WHERE m.id=:id AND m.user.id=:userId"),
        @NamedQuery(name = Meal.ALL_SORTED, query = "SELECT m FROM Meal m WHERE m.user=:user ORDER BY m.dateTime DESC"),
        //@NamedQuery(name = Meal.BETWEEN_DATE_TIME, query = "SELECT m FROM Meal m WHERE m.dateTime BETWEEN :startDateTime AND :endDateTime"),
        @NamedQuery(name = Meal.BETWEEN_DATE_TIME, query = "SELECT m FROM Meal m WHERE m.dateTime >= :startDateTime AND m.dateTime <= :endDateTime"),
})
@Entity
@Table(name = "meals")
public class Meal extends BaseEntity {

    //All the below are related to a user (I do not write "by user")
    //public static final String DELETE_ALL = "Meal.deleteAll";
    public static final String DELETE = "Meal.delete";
    public static final String BETWEEN_DATE_TIME = "Meal.getBetweenDateTime";
    public static final String ALL_SORTED = "Meal.getAllSorted";

    @Column(name = "date_time", columnDefinition = "timestamp default now()")
    private LocalDateTime dateTime;

    @Column
    private String description;

    @Column(name = "calories", columnDefinition = "int default 2000")
    @Range(min = 10, max = 10000)
    private int calories;

    @JoinColumn(name="user_id")
    @ManyToOne(fetch = FetchType.LAZY)
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

    public Meal(Integer id, LocalDateTime dateTime, String description, int calories, User user) {
        super(id);
        this.dateTime = dateTime;
        this.description = description;
        this.calories = calories;
        this.user = user;
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

    public boolean isNew() {
        return id == null;
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
