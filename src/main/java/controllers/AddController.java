package controllers;

import dao.TaskDao;
import models.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/add")
public class AddController extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String name = req.getParameter("name");
        boolean check;
        Task task = new Task(req.getParameter("name"));
        if (id == null || id.isEmpty()){
            check = TaskDao.make(task);
        }
        else{
            task.setId(Long.valueOf(id));
            check = TaskDao.update(task);
        }

        if (check){
            resp.sendRedirect("/view");
        }else {
            resp.sendRedirect("add.html");
        }


    }
}
