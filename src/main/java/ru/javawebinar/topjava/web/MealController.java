package ru.javawebinar.topjava.web;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.time.LocalDateTime;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.model.Meals;
import ru.javawebinar.topjava.util.MealsUtil;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by yurii.pyvovarenko on 3/29/2017.
 */
public class MealController extends HttpServlet {
    private static final Logger LOG = getLogger(MealController.class);
    private static final long serialVersionUID = 1L;
    private static String INSERT_OR_EDIT = "/meal.jsp";
    private static String LIST_MEALS = "/meals.jsp";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LocalDateTime dateTime = LocalDateTime.parse(request.getParameter("dateTime"));
        String description = request.getParameter("description");
        int calories = Integer.parseInt(request.getParameter("calories"));
        String action = request.getParameter("action");
        int id = Integer.parseInt(request.getParameter("id"));

        if (action.equalsIgnoreCase("edit")){
            Meals.edit(id, dateTime, description, calories);
        } else if (action.equalsIgnoreCase("add")) {
            Meals.add(dateTime, description, calories);
        }

        RequestDispatcher view = request.getRequestDispatcher(LIST_MEALS);
        response.setCharacterEncoding("UTF-8");
        request.setAttribute("meals", Meals.getAll());
        request.setAttribute("mealsWithExceeded", MealsUtil.getWithExceeded(Meals.getAll(), Meals.getCaloriesPerDay()));
        view.forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward="";
        String action = request.getParameter("action");

        if (action.equalsIgnoreCase("delete")){
            int mealId = Integer.parseInt(request.getParameter("id"));
            Meals.delete(mealId);
            forward = LIST_MEALS;
            //request.setAttribute("mealsWithExceeded", MealsUtil.getWithExceeded(Meals.getAll(), Meals.getCaloriesPerDay()));
            HttpSession session = request.getSession();
            session.setAttribute("mealsWithExceeded", MealsUtil.getWithExceeded(Meals.getAll(), Meals.getCaloriesPerDay()));
        } else if (action.equalsIgnoreCase("edit")){
            forward = INSERT_OR_EDIT;
            int mealId = Integer.parseInt(request.getParameter("id"));
            Meal meal = Meals.getById(mealId);
            request.setAttribute("id", mealId);
            request.setAttribute("action", action);
            request.setAttribute("meal", meal);
        } else if (action.equalsIgnoreCase("listMeals")){
            forward = LIST_MEALS;
            request.setAttribute("mealsWithExceeded", MealsUtil.getWithExceeded(Meals.getAll(), Meals.getCaloriesPerDay()));
        } else {
            forward = LIST_MEALS; //INSERT_OR_EDIT;
        }

        response.setCharacterEncoding("UTF-8");
        RequestDispatcher view = request.getRequestDispatcher(forward);
        view.forward(request, response);
    }
}
