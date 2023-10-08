package ru.javawebinar.topjava.web;

import org.slf4j.Logger;
import ru.javawebinar.topjava.model.MealTo;
import ru.javawebinar.topjava.repositories.InMemoryCrudRepository;
import ru.javawebinar.topjava.repositories.MealCrudRepository;
import ru.javawebinar.topjava.util.MealsUtil;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.time.LocalDateTime;


@WebServlet("/meal")
public class MealServlet extends HttpServlet {
    private final MealCrudRepository repository = new InMemoryCrudRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "/meals.jsp";
        String action = request.getParameter("action");
        if(action != null && action.equals("delete")){
            int id = Integer.parseInt(request.getParameter("id"));
            repository.deleteById(id);
            forward = "/meals.jsp";
            request.setAttribute("meals", repository.getMealsTo());
        }
        if(action != null && action.equals("add")){
            forward = "/addMeal.jsp";
        }
        if(action != null && action.equals("update")){
            int id = Integer.parseInt(request.getParameter("id"));
            MealTo meal =  repository.getMealToById(id);
            forward = "/updateMeal.jsp";
            request.setAttribute("meal", meal);
        }
        else {
            request.setAttribute("meals", repository.getMealsTo());
        }
        request.getRequestDispatcher(forward).forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("dateTime"));
        String description = new String(req.getParameter("desc").getBytes(StandardCharsets.ISO_8859_1), StandardCharsets.UTF_8);
        int calories = Integer.parseInt(req.getParameter("calories"));
        String formName = req.getParameter("name");
        String forward = "";
        MealTo meal = new MealTo(dateTime, description, calories, false);
        if (formName.equals("add")) {
            forward = "/addMeal.jsp";
            repository.saveMeal(meal);
        }
        if(formName.equals("update")){
            int id = Integer.parseInt(req.getParameter("id"));
            meal.setId(id);
            repository.updateMeal(meal);
            forward = "/updateMeal.jsp";
        }
        req.getRequestDispatcher(forward).forward(req, resp);

    }


}

