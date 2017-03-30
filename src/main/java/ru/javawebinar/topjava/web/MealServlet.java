package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealWithExceed;
import ru.javawebinar.topjava.model.Meals;
import ru.javawebinar.topjava.util.MealsUtil;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

import static org.slf4j.LoggerFactory.getLogger;

/**
 * Created by yurii.pyvovarenko on 3/25/2017.
 */
public class MealServlet extends HttpServlet {
    private static final Logger LOG = getLogger(MealServlet.class);

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        LOG.debug("forward to meals");

        List<MealWithExceed> mealsWithExceeded = MealsUtil.getWithExceeded(Meals.getAll(), Meals.getCaloriesPerDay());
        response.setCharacterEncoding("UTF-8");  //uncomment this if you see "2015-05-30 10:00	???????	500" instead of "2015-05-30 10:00	Завтрак	500"
        request.setAttribute("mealsWithExceeded", mealsWithExceeded);
        getServletContext().getRequestDispatcher("/meals.jsp").forward(request, response);
    }
}
