package controller;

import DAO.impl.StudentDAOImpl;
import eschool.CustomResponse;
import model.Student;
import model.User;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.List;

@Path("/student")
public class StudentREST {

    @Inject
    StudentDAOImpl studentDAOImpl;
    @Context
    HttpServletRequest request;

    @GET
    @Path("/getAll")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll(@Context HttpServletRequest request){
        User user = (User) request.getSession().getAttribute("user");
        if(user != null) {
            List<Student> list = this.studentDAOImpl.getAll();
            return Response.ok(list).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addStudent(@BeanParam Student student) {
        User user = (User) request.getSession().getAttribute("user");
        if(user != null) {
            CustomResponse cr = new CustomResponse();
            Student added = this.studentDAOImpl.addStudent(student, user);
            cr.status = added != null ? "ok" : "fail";
            cr.data = added;
            return Response.ok(cr).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }



}
