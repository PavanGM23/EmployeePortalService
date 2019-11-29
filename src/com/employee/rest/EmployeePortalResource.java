package com.employee.rest;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import org.json.simple.JSONArray;

import com.employee.dataaccess.EmployeeDataStore;
import com.employee.model.EmployeeData;

import io.swagger.annotations.ApiParam;


@Path("/resource")
public class EmployeePortalResource {

	@POST
    @Path("/employees")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveEmployees(@ApiParam(required = true) final EmployeeData employeeData) throws Exception
    {
            final Boolean response = new EmployeeDataStore().postEmployeeData(employeeData);
            if (response)
            {
                return Response.status(Status.CREATED).build();
            }
            return Response.status(Response.Status.BAD_REQUEST).build();
    }
	
	@GET
    @Path("/employees")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getEmployees() throws Exception
    {
            final JSONArray response = new EmployeeDataStore().getEmployeesData();
            
            return Response.status(200).entity(response).build();
    }
	
}
