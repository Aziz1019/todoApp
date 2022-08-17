package controllers;

import dao.TaskDao;
import models.Task;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/EditServlet")
public class EditController extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        Task task = TaskDao.findById(Long.valueOf(id));

        PrintWriter writer = resp.getWriter();
        resp.setContentType("text/html");
        writer.print("<html><body>");
        writer.print("<form action=\"/add\" method=\"post\">\n" +
                "<input style=\"display: none\" name = \"id\" value = '"+task.getId()+"'/>"+
                "    <label for=\"name\">Text </label>\n" +
                "    <input type=\"text\" id=\"name\" name=\"name\" value= \""+task.getName()+"\">\n" +
                "    <button type=\"submit\">Save</button>\n" +
                "\n" +
                "</form>");
        writer.print("</body></html>");
        writer.close();
    }
}

