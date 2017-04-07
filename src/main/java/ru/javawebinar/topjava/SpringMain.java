package ru.javawebinar.topjava;

import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Role;
import ru.javawebinar.topjava.model.User;
import ru.javawebinar.topjava.repository.MealRepository;
import ru.javawebinar.topjava.repository.UserRepository;
import ru.javawebinar.topjava.service.MealService;
import ru.javawebinar.topjava.service.UserService;
import ru.javawebinar.topjava.util.exception.NotFoundException;
import ru.javawebinar.topjava.web.meal.MealRestController;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

import static java.lang.System.out;

public class SpringMain {
    public static void main(String[] args) {
        ConfigurableApplicationContext appCtx = new ClassPathXmlApplicationContext("spring/spring-app.xml");
        out.println("Bean definition names: " + Arrays.toString(appCtx.getBeanDefinitionNames()));

//        UserRepository userRepository = (UserRepository) appCtx.getBean("mockUserRepository");
        UserRepository userRepository = appCtx.getBean(UserRepository.class);
        List<User> users = userRepository.getAll();

        UserService userService = appCtx.getBean(UserService.class);
        userService.save(new User(null, "userName", "email", "password", Role.ROLE_ADMIN));


        MealRepository mealRepository = appCtx.getBean(MealRepository.class);
        System.out.println("Repo");
        mealRepository.getAll().stream().forEach(out::println);

        MealService mealService = appCtx.getBean(MealService.class);
        System.out.println("Service");
        System.out.println(mealService.belongsToUser(1,1));
        System.out.println(mealService.belongsToUser(2,1));
        try {
            System.out.println(mealService.belongsToUser(10, 1));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        try {
            System.out.println(mealService.belongsToUser(3,100));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        System.out.println("saving:" +
            mealService.save(new Meal(null, LocalDateTime.now(), "Це тест", 1000, -1))
        );

        MealRestController mealRestController = appCtx.getBean(MealRestController.class);
        System.out.println("REST Controller");

        mealRestController.getAll().stream().forEach(out::println);

        Meal newMeal = mealRestController.create(new Meal(null, LocalDateTime.now(), "Це тест", 1000, -1));
        System.out.println("created:" + newMeal);
        Meal newMealUpdated = new Meal(LocalDateTime.now(), "Це тест - updated", 9, -1);
        mealRestController.update(newMealUpdated, newMeal.getId());
        mealRestController.get(newMeal.getId());
        mealRestController.delete(newMeal.getId());
        try {
            System.out.println(mealRestController.get(newMeal.getId()));
        } catch (NotFoundException e) {
            e.printStackTrace();
        }

        System.out.println("Closing the app context");
        appCtx.close();
    }
}
