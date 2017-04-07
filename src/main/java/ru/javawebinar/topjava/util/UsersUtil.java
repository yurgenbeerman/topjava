package ru.javawebinar.topjava.util;

import ru.javawebinar.topjava.model.User;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by yurii.pyvovarenko on 4/4/2017.
 */
public class UsersUtil {
    public static final List<User> USERS = Arrays.asList(
            new User("John", "john1@email.com", "psw1"),
            new User("John", "john2@email.com", "psw2"),
            new User("John3", "john3@email.com", "psw3")
    );

    public static List<User> getSorted(Collection<User> users, boolean ascending) {
        return users.stream()
                .sorted()
                .sorted((user1, user2) -> new User().compare(user1, user2)) // user1.getEmail().compareTo(user2.getEmail()))
                .collect(Collectors.toList());
    }
}
