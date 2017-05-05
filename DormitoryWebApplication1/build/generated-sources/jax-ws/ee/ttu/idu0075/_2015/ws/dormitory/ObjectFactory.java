
package ee.ttu.idu0075._2015.ws.dormitory;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the ee.ttu.idu0075._2015.ws.dormitory package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _GetTenantResponse_QNAME = new QName("http://www.ttu.ee/idu0075/2015/ws/dormitory", "getTenantResponse");
    private final static QName _GetDormitoryResponse_QNAME = new QName("http://www.ttu.ee/idu0075/2015/ws/dormitory", "getDormitoryResponse");
    private final static QName _GetDormitoryTenantListResponse_QNAME = new QName("http://www.ttu.ee/idu0075/2015/ws/dormitory", "getDormitoryTenantListResponse");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: ee.ttu.idu0075._2015.ws.dormitory
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link GetTenantRequest }
     * 
     */
    public GetTenantRequest createGetTenantRequest() {
        return new GetTenantRequest();
    }

    /**
     * Create an instance of {@link TenantType }
     * 
     */
    public TenantType createTenantType() {
        return new TenantType();
    }

    /**
     * Create an instance of {@link AddTenantRequest }
     * 
     */
    public AddTenantRequest createAddTenantRequest() {
        return new AddTenantRequest();
    }

    /**
     * Create an instance of {@link AddTenantResponse }
     * 
     */
    public AddTenantResponse createAddTenantResponse() {
        return new AddTenantResponse();
    }

    /**
     * Create an instance of {@link GetTenantListRequest }
     * 
     */
    public GetTenantListRequest createGetTenantListRequest() {
        return new GetTenantListRequest();
    }

    /**
     * Create an instance of {@link GetTenantListResponse }
     * 
     */
    public GetTenantListResponse createGetTenantListResponse() {
        return new GetTenantListResponse();
    }

    /**
     * Create an instance of {@link GetDormitoryRequest }
     * 
     */
    public GetDormitoryRequest createGetDormitoryRequest() {
        return new GetDormitoryRequest();
    }

    /**
     * Create an instance of {@link DormitoryType }
     * 
     */
    public DormitoryType createDormitoryType() {
        return new DormitoryType();
    }

    /**
     * Create an instance of {@link AddDormitoryRequest }
     * 
     */
    public AddDormitoryRequest createAddDormitoryRequest() {
        return new AddDormitoryRequest();
    }

    /**
     * Create an instance of {@link AddDormitoryResponse }
     * 
     */
    public AddDormitoryResponse createAddDormitoryResponse() {
        return new AddDormitoryResponse();
    }

    /**
     * Create an instance of {@link GetDormitoryListRequest }
     * 
     */
    public GetDormitoryListRequest createGetDormitoryListRequest() {
        return new GetDormitoryListRequest();
    }

    /**
     * Create an instance of {@link GetDormitoryListResponse }
     * 
     */
    public GetDormitoryListResponse createGetDormitoryListResponse() {
        return new GetDormitoryListResponse();
    }

    /**
     * Create an instance of {@link GetDormitoryTenantListRequest }
     * 
     */
    public GetDormitoryTenantListRequest createGetDormitoryTenantListRequest() {
        return new GetDormitoryTenantListRequest();
    }

    /**
     * Create an instance of {@link DormitoryTenantListType }
     * 
     */
    public DormitoryTenantListType createDormitoryTenantListType() {
        return new DormitoryTenantListType();
    }

    /**
     * Create an instance of {@link AddDormitoryTenantRequest }
     * 
     */
    public AddDormitoryTenantRequest createAddDormitoryTenantRequest() {
        return new AddDormitoryTenantRequest();
    }

    /**
     * Create an instance of {@link AddDormitoryTenantResponse }
     * 
     */
    public AddDormitoryTenantResponse createAddDormitoryTenantResponse() {
        return new AddDormitoryTenantResponse();
    }

    /**
     * Create an instance of {@link DormitoryTenantType }
     * 
     */
    public DormitoryTenantType createDormitoryTenantType() {
        return new DormitoryTenantType();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link TenantType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ttu.ee/idu0075/2015/ws/dormitory", name = "getTenantResponse")
    public JAXBElement<TenantType> createGetTenantResponse(TenantType value) {
        return new JAXBElement<TenantType>(_GetTenantResponse_QNAME, TenantType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DormitoryType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ttu.ee/idu0075/2015/ws/dormitory", name = "getDormitoryResponse")
    public JAXBElement<DormitoryType> createGetDormitoryResponse(DormitoryType value) {
        return new JAXBElement<DormitoryType>(_GetDormitoryResponse_QNAME, DormitoryType.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DormitoryTenantListType }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.ttu.ee/idu0075/2015/ws/dormitory", name = "getDormitoryTenantListResponse")
    public JAXBElement<DormitoryTenantListType> createGetDormitoryTenantListResponse(DormitoryTenantListType value) {
        return new JAXBElement<DormitoryTenantListType>(_GetDormitoryTenantListResponse_QNAME, DormitoryTenantListType.class, null, value);
    }

}
