package ru.javawebinar.topjava.web;

import ru.javawebinar.topjava.model.Meal;
import ru.javawebinar.topjava.repositories.InMemoryCrudRepository;
import ru.javawebinar.topjava.repositories.MealCrudRepository;


import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.time.LocalDateTime;


public class MealServlet extends HttpServlet {
    private final MealCrudRepository repository = new InMemoryCrudRepository();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String forward = "/meals.jsp";
        String action = request.getParameter("action");
        if ("delete".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            repository.deleteById(id);
            response.sendRedirect(request.getContextPath() + "/meals");
            return;
        }
        if ("update".equals(action)) {
            int id = Integer.parseInt(request.getParameter("id"));
            Meal meal = repository.getMealById(id);
            forward = "/addUpdateMeal.jsp";
            request.setAttribute("meal", meal);
        }
        if("add".equals(action)){
            forward = "/addUpdateMeal.jsp";
        }
        else {
            request.setAttribute("meals", repository.getMealsTo());
        }
        request.getRequestDispatcher(forward).forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setCharacterEncoding("UTF-8");
        LocalDateTime dateTime = LocalDateTime.parse(req.getParameter("dateTime"));
        String description = req.getParameter("desc");
        int calories = Integer.parseInt(req.getParameter("calories"));
        Meal meal = new Meal(dateTime, description, calories);
        String id = req.getParameter("id");
        if(id.isEmpty()){
            repository.createMeal(meal);
        }
        else {
            meal.setId(Integer.parseInt(id));
            repository.updateMeal(meal);
        }
        resp.sendRedirect(req.getContextPath()+"/meals");
    }


}

