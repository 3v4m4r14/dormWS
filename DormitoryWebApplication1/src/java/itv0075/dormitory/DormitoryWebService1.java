/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package itv0075.dormitory;

import com.sun.xml.internal.ws.developer.SchemaValidation;
import ee.ttu.idu0075._2015.ws.dormitory.AddDormitoryRequest;
import ee.ttu.idu0075._2015.ws.dormitory.AddDormitoryResponse;
import ee.ttu.idu0075._2015.ws.dormitory.AddDormitoryTenantRequest;
import ee.ttu.idu0075._2015.ws.dormitory.AddDormitoryTenantResponse;
import ee.ttu.idu0075._2015.ws.dormitory.AddTenantRequest;
import ee.ttu.idu0075._2015.ws.dormitory.AddTenantResponse;
import ee.ttu.idu0075._2015.ws.dormitory.DormitoryTenantListType;
import ee.ttu.idu0075._2015.ws.dormitory.DormitoryTenantType;
import ee.ttu.idu0075._2015.ws.dormitory.DormitoryType;
import ee.ttu.idu0075._2015.ws.dormitory.GetDormitoryListRequest;
import ee.ttu.idu0075._2015.ws.dormitory.GetDormitoryListResponse;
import ee.ttu.idu0075._2015.ws.dormitory.GetDormitoryRequest;
import ee.ttu.idu0075._2015.ws.dormitory.GetDormitoryTenantListRequest;
import ee.ttu.idu0075._2015.ws.dormitory.GetTenantListRequest;
import ee.ttu.idu0075._2015.ws.dormitory.GetTenantListResponse;
import ee.ttu.idu0075._2015.ws.dormitory.GetTenantRequest;
import ee.ttu.idu0075._2015.ws.dormitory.TenantType;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import javax.jws.WebService;

/**
 * Web Service
 * 
 * @author Eva Maria Veitmaa
 */
@WebService(serviceName = "dormitoryService1", portName = "dormitoryPort", endpointInterface = "ee.ttu.idu0075._2015.ws.dormitory.DormitoryPortType", targetNamespace = "http://www.ttu.ee/idu0075/2015/ws/dormitory", wsdlLocation = "WEB-INF/wsdl/DormitoryService1/dormitoryService.wsdl")
@SchemaValidation
public class DormitoryWebService1 {

    private static final String API_TOKEN = "asd";
    static int nextDormitoryId = 1;
    static int nextTenantId = 1;
    static List<DormitoryType> dormitoryList = new ArrayList<DormitoryType>();
    static List<TenantType> tenantList = new ArrayList<TenantType>();
    static HashMap<BigInteger, TenantType> addTenantMap = new HashMap();
    static HashMap<BigInteger, DormitoryType> addDormitoryMap = new HashMap();
    static HashMap<BigInteger, DormitoryTenantType> addDormitoryTenantMap = new HashMap();

    public TenantType getTenant(GetTenantRequest parameter) throws InvalidTokenException {
        TenantType tenant = null;
        if (API_TOKEN.equalsIgnoreCase(parameter.getToken())) {
            for (TenantType t : tenantList) {
                if (t.getId().equals(parameter.getId())) {
                    tenant = t;
                }
            }
        } else {
            throw new InvalidTokenException("Invalid token.");
        }
        return tenant;
    }

    public AddTenantResponse addTenant(AddTenantRequest parameter) throws InvalidTokenException {
        AddTenantResponse response = new AddTenantResponse();
        TenantType tenant = new TenantType();

        if (API_TOKEN.equalsIgnoreCase(parameter.getToken()) && parameter.getRequestCode() != null) {

            response.setResponseCode(parameter.getRequestCode());

            if (addTenantMap.containsKey(parameter.getRequestCode())) {
                response.setTenant(addTenantMap.get(parameter.getRequestCode()));
            } else {
                tenant.setId(BigInteger.valueOf(nextTenantId++));
                tenant.setFirstName(parameter.getFirstName());
                tenant.setLastName(parameter.getLastName());
                tenant.setIdCode(parameter.getIdCode());
                tenant.setGender(parameter.getGender());
                tenant.setStudentStatus(parameter.getStudentStatus());
                tenant.setUniversity(parameter.getUniversity());
                tenantList.add(tenant);
                addTenantMap.put(parameter.getRequestCode(), tenant);
                response.setTenant(tenant);
            }
        } else {
            throw new InvalidTokenException("Invalid token.");
        }

        return response;
    }

    public GetTenantListResponse getTenantList(GetTenantListRequest parameter) throws InvalidTokenException {
        GetTenantListResponse tenants = new GetTenantListResponse();
        if (API_TOKEN.equalsIgnoreCase(parameter.getToken())) {
            for (TenantType tenant : tenantList) {
                if (tenantParametersMatch(parameter, tenant)) {
                    tenants.getTenant().add(tenant);
                }
            }
        } else {
            throw new InvalidTokenException("Invalid token.");
        }
        return tenants;
    }

    public DormitoryType getDormitory(GetDormitoryRequest parameter) throws InvalidTokenException {
        DormitoryType response = null;
        if (API_TOKEN.equalsIgnoreCase(parameter.getToken())) {
            for (DormitoryType dormitory : dormitoryList) {
                if (dormitory.getId().equals(parameter.getId())) {
                    response = dormitory;
                }
            }
        } else {
            throw new InvalidTokenException("Invalid token.");
        }
        return response;
    }

    public AddDormitoryResponse addDormitory(AddDormitoryRequest parameter) throws InvalidTokenException {
        AddDormitoryResponse response = new AddDormitoryResponse();
        DormitoryType dormitory = new DormitoryType();
        if (API_TOKEN.equalsIgnoreCase(parameter.getToken()) && parameter.getRequestCode() != null) {

            response.setResponseCode(parameter.getRequestCode());

            if (addDormitoryMap.containsKey(parameter.getRequestCode())) {
                response.setDormitory(addDormitoryMap.get(parameter.getRequestCode()));
            } else {
                dormitory.setId(BigInteger.valueOf(nextDormitoryId++));
                dormitory.setAdministrativeArea(parameter.getAdministrativeArea());
                dormitory.setDormitoryAddress(parameter.getDormitoryAddress());
                dormitory.setDormitoryCapacity(parameter.getDormitoryCapacity());
                dormitory.setDormitoryOwner(parameter.getDormitoryOwner());
                dormitory.setDormitoryCondition(parameter.getDormitoryCondition());
                dormitory.setDormitoryTenantList(new DormitoryTenantListType());
                dormitoryList.add(dormitory);
                addDormitoryMap.put(parameter.getRequestCode(), dormitory);
                response.setDormitory(dormitory);
            }
        } else {
            throw new InvalidTokenException("Invalid token.");
        }
        return response;
    }

    public GetDormitoryListResponse getDormitoryList(GetDormitoryListRequest parameter) throws InvalidTokenException {

        GetDormitoryListResponse dormitories = new GetDormitoryListResponse();

        if (API_TOKEN.equalsIgnoreCase(parameter.getToken())) {
            for (DormitoryType dormitory : dormitoryList) {
                if (dormitoryParametersMatch(parameter, dormitory)) {
                    dormitories.getDormitory().add(dormitory);
                }
            }
        } else {
            throw new InvalidTokenException("Invalid token.");
        }

        return dormitories;
    }

    public DormitoryTenantListType getDormitoryTenantList(GetDormitoryTenantListRequest parameter) throws InvalidTokenException {
        DormitoryTenantListType dormitoryTenants = new DormitoryTenantListType();

        if (API_TOKEN.equalsIgnoreCase(parameter.getToken())) {
            for (DormitoryType dormitory : dormitoryList) {
                if (parameter.getDormitoryId().equals(dormitory.getId())) {
                    dormitoryTenants = dormitory.getDormitoryTenantList();
                }
            }
        } else {
            throw new InvalidTokenException("Invalid token.");
        }
        return dormitoryTenants;
    }

    public AddDormitoryTenantResponse addDormitoryTenant(AddDormitoryTenantRequest parameter) throws InvalidTokenException {
        AddDormitoryTenantResponse response = new AddDormitoryTenantResponse();
        DormitoryTenantType dormitoryTenant = new DormitoryTenantType();

        if (API_TOKEN.equalsIgnoreCase(parameter.getToken()) && parameter.getRequestCode() != null) {

            response.setResponseCode(parameter.getRequestCode());

            if (addDormitoryTenantMap.containsKey(parameter.getRequestCode())) {
                response.setDormitoryTenant(addDormitoryTenantMap.get(parameter.getRequestCode()));
            } else {

                GetTenantRequest tenantRequest = new GetTenantRequest();
                tenantRequest.setId(parameter.getTenantId());
                tenantRequest.setToken(parameter.getToken());

                if (getTenant(tenantRequest) != null) {
                    dormitoryTenant.setTenant(getTenant(tenantRequest));
                    dormitoryTenant.setStartDate(parameter.getStartDate());
                    dormitoryTenant.setEndDate(parameter.getEndDate());
                    dormitoryTenant.setStatus(parameter.getStatus());

                    for (DormitoryType dormitory : dormitoryList) {
                        if (dormitory.getId().equals(parameter.getDormitoryId())
                                && !dormitory.getDormitoryTenantList().getDormitoryTenant().contains(dormitoryTenant)) {
                            dormitory.getDormitoryTenantList().getDormitoryTenant().add(dormitoryTenant);
                            addDormitoryTenantMap.put(parameter.getRequestCode(), dormitoryTenant);
                            response.setDormitoryTenant(dormitoryTenant);
                        }
                    }
                }
            }
        } else {
            throw new InvalidTokenException("Invalid token.");
        }
        return response;
    }

    private static boolean tenantParametersMatch(GetTenantListRequest parameter, TenantType tt) {
        return firstNamesMatch(parameter, tt)
                && lastNamesMatch(parameter, tt)
                && gendersMatch(parameter, tt)
                && studentStatusesMatch(parameter, tt)
                && universitiesMatch(parameter, tt);
    }

    private static boolean universitiesMatch(GetTenantListRequest parameter, TenantType tt) {
        return parameter.getUniversity() == null || (parameter.getUniversity() != null && parameter.getUniversity().equalsIgnoreCase(tt.getUniversity()));
    }

    private static boolean studentStatusesMatch(GetTenantListRequest parameter, TenantType tt) {
        return parameter.getStudentStatus() == null || (parameter.getStudentStatus() != null && parameter.getStudentStatus().equalsIgnoreCase(tt.getStudentStatus()));
    }

    private static boolean gendersMatch(GetTenantListRequest parameter, TenantType tt) {
        return parameter.getGender() == null || (parameter.getGender() != null && parameter.getGender().equalsIgnoreCase(tt.getGender()));
    }

    private static boolean lastNamesMatch(GetTenantListRequest parameter, TenantType tt) {
        return parameter.getLastName() == null || (parameter.getLastName() != null && parameter.getLastName().equalsIgnoreCase(tt.getLastName()));
    }

    private static boolean firstNamesMatch(GetTenantListRequest parameter, TenantType tt) {
        return parameter.getFirstName() == null || (parameter.getFirstName() != null && parameter.getFirstName().equalsIgnoreCase(tt.getFirstName()));
    }

    private static boolean dormitoryParametersMatch(GetDormitoryListRequest parameter, DormitoryType dt) {
        return administrativeAreasMatch(parameter, dt)
                && ownersMatch(parameter, dt)
                && hasRelatedTenantsMatch(parameter, dt)
                && conditionsMatch(parameter, dt);
    }
    
    private static boolean conditionsMatch(GetDormitoryListRequest parameter, DormitoryType dt) {
        return parameter.getDormitoryCondition() == null
                || parameter.getDormitoryCondition().equalsIgnoreCase(dt.getDormitoryCondition());
    }

    private static boolean hasRelatedTenantsMatch(GetDormitoryListRequest parameter, DormitoryType dt) {
        return parameter.getHasRelatedTenants() == null
                || (parameter.getHasRelatedTenants().equalsIgnoreCase("yes")
                && !dt.getDormitoryTenantList().getDormitoryTenant().isEmpty())
                || (parameter.getHasRelatedTenants().equalsIgnoreCase("no")
                && dt.getDormitoryTenantList().getDormitoryTenant().isEmpty());
    }

    private static boolean ownersMatch(GetDormitoryListRequest parameter, DormitoryType dt) {
        return parameter.getDormitoryOwner() == null 
                || (parameter.getDormitoryOwner().equalsIgnoreCase(dt.getDormitoryOwner()));
    }

    private static boolean administrativeAreasMatch(GetDormitoryListRequest parameter, DormitoryType dt) {
        return parameter.getAdministrativeArea() == null || (parameter.getAdministrativeArea().equalsIgnoreCase(dt.getAdministrativeArea()));
    }

}
