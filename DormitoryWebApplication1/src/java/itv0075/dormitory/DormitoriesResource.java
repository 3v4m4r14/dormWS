/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itv0075.dormitory;

import ee.ttu.idu0075._2015.ws.dormitory.AddDormitoryRequest;
import ee.ttu.idu0075._2015.ws.dormitory.AddDormitoryResponse;
import ee.ttu.idu0075._2015.ws.dormitory.AddDormitoryTenantRequest;
import ee.ttu.idu0075._2015.ws.dormitory.AddDormitoryTenantResponse;
import ee.ttu.idu0075._2015.ws.dormitory.DormitoryTenantListType;
import ee.ttu.idu0075._2015.ws.dormitory.DormitoryType;
import ee.ttu.idu0075._2015.ws.dormitory.GetDormitoryListRequest;
import ee.ttu.idu0075._2015.ws.dormitory.GetDormitoryListResponse;
import ee.ttu.idu0075._2015.ws.dormitory.GetDormitoryRequest;
import ee.ttu.idu0075._2015.ws.dormitory.GetDormitoryTenantListRequest;
import ee.ttu.idu0075._2015.ws.dormitory.TenantType;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
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
import javax.xml.datatype.DatatypeFactory;

/**
 * REST Web Service
 *
 * @author Eva Maria Veitmaa
 */
@Path("dormitories")
public class DormitoriesResource {

    @Context
    private UriInfo context;

    /**
     * Creates a new instance of DormitoriesResource
     */
    public DormitoriesResource() {
    }

    /**
     * Retrieves representation of an instance of ee.ttu.idu0075._2015.ws.dormitory.DormitoryListResource
     * @return an instance of ee.ttu.idu0075._2015.ws.dormitory.GetDormitoryListResponse
     */
    @GET
    @Produces(MediaType.APPLICATION_XML)
    public GetDormitoryListResponse getDormitoryList(@QueryParam("token") String token,
            @QueryParam("area") String administrativeArea,
            @QueryParam("owner") String dormitoryOwner,
            @QueryParam("condition") String dormitoryCondition,
            @QueryParam("tenants") String hasRelatedTenants) {
        DormitoryWebService1 ws = new DormitoryWebService1();
        GetDormitoryListRequest request = new GetDormitoryListRequest();
        request.setToken(token);
        request.setAdministrativeArea(administrativeArea);
        request.setDormitoryOwner(dormitoryOwner);
        request.setDormitoryCondition(dormitoryCondition);
        request.setHasRelatedTenants(hasRelatedTenants);
        return ws.getDormitoryList(request);
    }

    /**
     * Retrieves representation of an instance of dormitory.DormitoryResource
     * @return an instance of ee.ttu.idu0075._2015.ws.dormitory.DormitoryType
     */
    @GET
    @Path("{id : \\d+}") //digits only
    @Produces(MediaType.APPLICATION_JSON)
    public DormitoryType getDormitory(@PathParam("id") String id,
            @QueryParam("token") String token) {
        DormitoryWebService1 ws = new DormitoryWebService1();
        GetDormitoryRequest request = new GetDormitoryRequest();
        request.setId(BigInteger.valueOf(Integer.parseInt(id)));
        request.setToken(token);
        return ws.getDormitory(request);
    }
    
    /**
     * POST method for updating or creating an instance of DormitoryResource
     * @param content representation for the resource
     */
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AddDormitoryResponse addDormitory(AddDormitoryRequest content, 
            @QueryParam("requestCode") int requestCode,
            @QueryParam("token") String token) {
        DormitoryWebService1 ws = new DormitoryWebService1();
        AddDormitoryRequest request = new AddDormitoryRequest();
        request.setRequestCode(BigInteger.valueOf(requestCode));
        request.setToken(token);
        request.setAdministrativeArea(content.getAdministrativeArea());
        request.setDormitoryAddress(content.getDormitoryAddress());
        request.setDormitoryCapacity(content.getDormitoryCapacity());
        request.setDormitoryOwner(content.getDormitoryOwner());
        request.setDormitoryCondition(content.getDormitoryCondition());
        return ws.addDormitory(request);
    }
    
    /**
     * POST method for adding a TenantType to a DormitoryType
     * @param content representation for the resource
     */
    @POST
    @Path("{id : \\d+}/tenants")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public AddDormitoryTenantResponse addDormitoryTenant(AddDormitoryTenantRequest content, 
                                        @QueryParam("token") String token,
                                        @QueryParam("requestCode") int requestCode,
                                        @PathParam("id") String id) {
        DormitoryWebService1 ws = new DormitoryWebService1();
        AddDormitoryTenantRequest request = new AddDormitoryTenantRequest();
        request.setToken(token);
        request.setRequestCode(BigInteger.valueOf(requestCode));
        request.setDormitoryId(BigInteger.valueOf(Integer.parseInt(id)));
        request.setTenantId(content.getTenantId());
        
        //TODO date format, not in seconds
        System.out.println("Content startDate in REST: " + content.getStartDate());
        
        request.setStartDate(content.getStartDate());
        System.out.println("Request startDate in REST: " + request.getStartDate());
        request.setEndDate(content.getEndDate());
        request.setStatus(content.getStatus());
        return ws.addDormitoryTenant(request);
    }
    
    /**
     * Retrieves representation of an instance of dormitory.DormitoryTenantListResource
     * @return an instance of ee.ttu.idu0075._2015.ws.dormitory.DormitoryTenantListType
     */
    @GET
    @Path("{id : \\d+}/tenants") //digits only
    @Produces(MediaType.APPLICATION_JSON)
    public DormitoryTenantListType getDormitoryTenantList(@PathParam("id") String id,
            @QueryParam("token") String token) {
        DormitoryWebService1 ws = new DormitoryWebService1();
        GetDormitoryTenantListRequest request = new GetDormitoryTenantListRequest();
        request.setDormitoryId(BigInteger.valueOf(Integer.parseInt(id)));
        request.setToken(token);
        return ws.getDormitoryTenantList(request);
    }
}
