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
        if (!API_TOKEN.equalsIgnoreCase(parameter.getToken())) {
            throw new InvalidTokenException();
        }

        for (TenantType t : tenantList) {
            if (t.getId().equals(parameter.getId())) {
                tenant = t;
            }
        }
        return tenant;
    }

    public AddTenantResponse addTenant(AddTenantRequest parameter) throws InvalidTokenException, InvalidInputDataException, InvalidRequestCodeException {
        AddTenantResponse response = new AddTenantResponse();
        TenantType tenant = new TenantType();

        if (!API_TOKEN.equalsIgnoreCase(parameter.getToken())) {
            throw new InvalidTokenException();
        }
        if (parameter.getRequestCode() == null) {
            throw new InvalidRequestCodeException();
        }

        response.setResponseCode(parameter.getRequestCode());

        if (addTenantMap.containsKey(parameter.getRequestCode())) {
            response.setTenant(addTenantMap.get(parameter.getRequestCode()));
        } else {
            if (!(genderIsValid(parameter)
                    && statusIsValid(parameter))) {
                throw new InvalidInputDataException();
            }
            tenant.setId(BigInteger.valueOf(nextTenantId++));
            tenant.setFirstName(parameter.getFirstName());
            tenant.setLastName(parameter.getLastName());
            tenant.setIdCode(parameter.getIdCode());
            tenant.setGender(parameter.getGender().toLowerCase());
            tenant.setStudentStatus(parameter.getStudentStatus().toLowerCase());
            tenant.setUniversity(parameter.getUniversity());
            tenantList.add(tenant);
            addTenantMap.put(parameter.getRequestCode(), tenant);
            response.setTenant(tenant);
        }
        return response;
    }

    private static boolean statusIsValid(AddTenantRequest parameter) {
        return parameter.getStudentStatus().equalsIgnoreCase("active")
                || parameter.getStudentStatus().equalsIgnoreCase("inactive");
    }

    private static boolean genderIsValid(AddTenantRequest parameter) {
        return parameter.getGender().equalsIgnoreCase("male") || parameter.getGender().equalsIgnoreCase("female")
                || parameter.getGender().equalsIgnoreCase("other");
    }

    public GetTenantListResponse getTenantList(GetTenantListRequest parameter) throws InvalidTokenException {
        GetTenantListResponse tenants = new GetTenantListResponse();
        if (!API_TOKEN.equalsIgnoreCase(parameter.getToken())) {
            throw new InvalidTokenException();
        }

        for (TenantType tenant : tenantList) {
            if (tenantParametersMatch(parameter, tenant)) {
                tenants.getTenant().add(tenant);
            }
        }
        return tenants;
    }

    public DormitoryType getDormitory(GetDormitoryRequest parameter) throws InvalidTokenException {
        DormitoryType response = null;
        if (!API_TOKEN.equalsIgnoreCase(parameter.getToken())) {
            throw new InvalidTokenException();
        }
        for (DormitoryType dormitory : dormitoryList) {
            if (dormitory.getId().equals(parameter.getId())) {
                response = dormitory;
            }
        }
        return response;
    }

    public AddDormitoryResponse addDormitory(AddDormitoryRequest parameter) throws InvalidTokenException, InvalidRequestCodeException, InvalidInputDataException {
        AddDormitoryResponse response = new AddDormitoryResponse();
        DormitoryType dormitory = new DormitoryType();
        if (!API_TOKEN.equalsIgnoreCase(parameter.getToken())) {
            throw new InvalidTokenException();
        }
        if (parameter.getRequestCode() == null) {
            throw new InvalidRequestCodeException();
        }

        response.setResponseCode(parameter.getRequestCode());

        if (addDormitoryMap.containsKey(parameter.getRequestCode())) {
            response.setDormitory(addDormitoryMap.get(parameter.getRequestCode()));
        } else {
            if (!conditionIsValid(parameter)) {
                throw new InvalidInputDataException();
            }
            dormitory.setId(BigInteger.valueOf(nextDormitoryId++));
            dormitory.setAdministrativeArea(parameter.getAdministrativeArea());
            dormitory.setDormitoryAddress(parameter.getDormitoryAddress());
            dormitory.setDormitoryCapacity(parameter.getDormitoryCapacity());
            dormitory.setDormitoryOwner(parameter.getDormitoryOwner());
            dormitory.setDormitoryCondition(parameter.getDormitoryCondition().toLowerCase());
            dormitory.setDormitoryTenantList(new DormitoryTenantListType());
            dormitoryList.add(dormitory);
            addDormitoryMap.put(parameter.getRequestCode(), dormitory);
            response.setDormitory(dormitory);
        }

        return response;
    }

    private static boolean conditionIsValid(AddDormitoryRequest parameter) {
        return parameter.getDormitoryCondition().equalsIgnoreCase("old")
                || parameter.getDormitoryCondition().equalsIgnoreCase("new")
                || parameter.getDormitoryCondition().equalsIgnoreCase("renovated");
    }

    public GetDormitoryListResponse getDormitoryList(GetDormitoryListRequest parameter) throws InvalidTokenException {

        GetDormitoryListResponse dormitories = new GetDormitoryListResponse();

        if (!API_TOKEN.equalsIgnoreCase(parameter.getToken())) {
            throw new InvalidTokenException();
        }
        for (DormitoryType dormitory : dormitoryList) {
            if (dormitoryParametersMatch(parameter, dormitory)) {
                dormitories.getDormitory().add(dormitory);
            }
        }
        return dormitories;
    }

    public DormitoryTenantListType getDormitoryTenantList(GetDormitoryTenantListRequest parameter) throws InvalidTokenException {
        DormitoryTenantListType dormitoryTenants = new DormitoryTenantListType();

        if (!API_TOKEN.equalsIgnoreCase(parameter.getToken())) {
            throw new InvalidTokenException();
        }
        for (DormitoryType dormitory : dormitoryList) {
            if (parameter.getDormitoryId().equals(dormitory.getId())) {
                dormitoryTenants = dormitory.getDormitoryTenantList();
            }
        }
        return dormitoryTenants;
    }

    public AddDormitoryTenantResponse addDormitoryTenant(AddDormitoryTenantRequest parameter) throws InvalidTokenException, InvalidRequestCodeException, InvalidInputDataException {
        AddDormitoryTenantResponse response = new AddDormitoryTenantResponse();
        DormitoryTenantType dormitoryTenant = new DormitoryTenantType();

        if (!API_TOKEN.equalsIgnoreCase(parameter.getToken())) {
            throw new InvalidTokenException();
        }
        if (parameter.getRequestCode() == null) {
            throw new InvalidRequestCodeException();
        }

        response.setResponseCode(parameter.getRequestCode());

        if (addDormitoryTenantMap.containsKey(parameter.getRequestCode())) {
            response.setDormitoryTenant(addDormitoryTenantMap.get(parameter.getRequestCode()));
        } else {

            GetTenantRequest tenantRequest = new GetTenantRequest();
            tenantRequest.setId(parameter.getTenantId());
            tenantRequest.setToken(parameter.getToken());

            if (getTenant(tenantRequest) != null) {
                if (!(parameter.getStatus().equalsIgnoreCase("active")
                        || parameter.getStatus().equalsIgnoreCase("inactive"))) {
                    throw new InvalidInputDataException();
                }
                dormitoryTenant.setTenant(getTenant(tenantRequest));
                dormitoryTenant.setStartDate(parameter.getStartDate());
                dormitoryTenant.setEndDate(parameter.getEndDate());
                dormitoryTenant.setStatus(parameter.getStatus().toLowerCase());

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
