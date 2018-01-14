package controller;

import DAO.impl.UserDAOImpl;

import eschool.CustomResponse;
import model.User;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/user")
public class UserREST {

    @Inject
    UserDAOImpl userDAOImpl;

    @Context
    HttpServletRequest request;

    @GET
    @Path("/getAll")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAll() {
        User user = (User) request.getSession().getAttribute("user");
        if(user != null)
            return Response.ok(userDAOImpl.getAll()).build();
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @GET
    @Path("/Me")
    @Produces(MediaType.APPLICATION_JSON)
    public Response me() {
        User user = (User) request.getSession().getAttribute("user");
        if(user != null)
            return Response.ok(user).build();
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @GET
    @Path("/hello")
    public String hello(@Context HttpServletRequest request) {
        User x  = (User) request.getSession().getAttribute("user");
        return x == null ? "i dont know you" : "hello, " + x.getName();
    }

    @POST
    @Path("/register")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces({ "application/json" })
    public Response register(@BeanParam User user) {
        CustomResponse cr = new CustomResponse();
        user = this.userDAOImpl.addUser(user);
        cr.status = user != null ? "ok" : "fail";
        cr.data = user;
        return Response.ok(cr).build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response authenticateUser(@BeanParam User user ) {
        CustomResponse cr = new CustomResponse();
        User isExist = this.userDAOImpl.validate(user);
        request.getSession().setAttribute("user", isExist);
        cr.status = isExist != null ? "ok" : "fail";
        cr.data = request.getSession().getAttribute("user");
        return Response.ok(cr).build();
    }
}


//    @Context HttpServletRequest request