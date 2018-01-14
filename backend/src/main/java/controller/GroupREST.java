package controller;

import DAO.impl.GroupDAOImpl;
import eschool.CustomResponse;
import model.Group;
import model.User;

import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


@Path("/group")
public class GroupREST {

    @Inject
    GroupDAOImpl groupDAOImpl;
    @Context
    HttpServletRequest request;

    @GET
    @Path("/getAll")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllGroups() {
        User user = (User) request.getSession().getAttribute("user");
        if(user != null)
            return Response.ok(groupDAOImpl.getAll()).build();
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @POST
    @Path("/add")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addGroup(@BeanParam Group group){
        User user = (User) request.getSession().getAttribute("user");
        if(user != null) {
            Group added = groupDAOImpl.addGroup(group);
            CustomResponse cr = new CustomResponse();
            cr.status = added != null ? "ok" : "fail";
            cr.data = added;
            return Response.ok(cr).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @POST
    @Path("/addStudent")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response addStudent(@FormParam("groupId") String groupId,
                               @FormParam("studentId") String studentId){
        User user = (User) request.getSession().getAttribute("user");
        if(user != null) {
            CustomResponse cr = new CustomResponse();
            Boolean added = this.groupDAOImpl.addStudent(groupId, studentId);
            cr.status = added ? "ok" : "fail";
            return Response.ok(cr).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

    @POST
    @Path("/removeStudent")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    @Produces(MediaType.APPLICATION_JSON)
    public Response removeStudent(@FormParam("groupId") String groupId,
                               @FormParam("studentId") String studentId){
        User user = (User) request.getSession().getAttribute("user");
        if(user != null) {
            CustomResponse cr = new CustomResponse();
            Boolean removed = this.groupDAOImpl.removeStudent(groupId, studentId);
            cr.status = removed ? "ok" : "fail";
            return Response.ok(cr).build();
        }
        return Response.status(Response.Status.FORBIDDEN).build();
    }

}
