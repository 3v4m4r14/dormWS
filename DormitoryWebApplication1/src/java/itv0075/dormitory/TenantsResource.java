/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itv0075.dormitory;

import ee.ttu.idu0075._2015.ws.dormitory.AddTenantRequest;
import ee.ttu.idu0075._2015.ws.dormitory.AddTenantResponse;
import ee.ttu.idu0075._2015.ws.dormitory.GetTenantListRequest;
import ee.ttu.idu0075._2015.ws.dormitory.GetTenantListResponse;
import ee.ttu.idu0075._2015.ws.dormitory.GetTenantRequest;
import ee.ttu.idu0075._2015.ws.dormitory.TenantType;
import java.math.BigInteger;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author emvei
 */
@Path("tenants")
public class TenantsResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of TenantsResource
     */
    public TenantsResource() {
    }

    /**
     * Retrieves representation of an instance of dormitory.TenantsResource
     * @return an instance of ee.ttu.idu0075._2015.ws.dormitory.TenantType
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public GetTenantListResponse getTenantList(@QueryParam("token") String token,
            @QueryParam("firstName") String firstName,
            @QueryParam("lastName") String lastName,
            @QueryParam("gender") String gender,
            @QueryParam("status") String studentStatus,
            @QueryParam("university") String university) {
        DormitoryWebService1 ws = new DormitoryWebService1();
        GetTenantListRequest request = new GetTenantListRequest();
        request.setToken(token);
        request.setFirstName(firstName);
        request.setLastName(lastName);
        request.setGender(gender);
        request.setStudentStatus(studentStatus);
        request.setUniversity(university);
        return ws.getTenantList(request);
    }
    
    /**
     * Retrieves representation of an instance of dormitory.TenantsResource
     * @return an instance of ee.ttu.idu0075._2015.ws.dormitory.TenantType
     */
    @GET
    @Path("{id : \\d+}") //digits only
    @Produces(MediaType.APPLICATION_JSON)
    public TenantType getTenant(@PathParam("id") String id,
            @QueryParam("token") String token) {
        DormitoryWebService1 ws = new DormitoryWebService1();
        GetTenantRequest request = new GetTenantRequest();
        request.setId(BigInteger.valueOf(Integer.parseInt(id)));
        request.setToken(token);
        return ws.getTenant(request);
    }

    /**
     * POST method for updating or creating an instance of TenantsResource
     * @param content representation for the resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AddTenantResponse addTenant(AddTenantRequest content, 
            @QueryParam("requestCode") int requestCode,
            @QueryParam("token") String token) {
        DormitoryWebService1 ws = new DormitoryWebService1();
        AddTenantRequest request = new AddTenantRequest();
        request.setRequestCode(BigInteger.valueOf(requestCode)); //TODO: breaks the service
        request.setToken(token);
        //request.setRequestCode(content.getRequestCode());
        request.setFirstName(content.getFirstName());
        request.setLastName(content.getLastName());
        request.setIdCode(content.getIdCode());
        request.setGender(content.getGender());
        request.setStudentStatus(content.getStudentStatus());
        request.setUniversity(content.getUniversity());
        return ws.addTenant(request);
    }
}
