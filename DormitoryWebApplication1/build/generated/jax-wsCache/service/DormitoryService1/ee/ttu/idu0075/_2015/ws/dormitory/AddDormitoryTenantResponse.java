
package ee.ttu.idu0075._2015.ws.dormitory;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for anonymous complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="responseCode" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="dormitoryTenant" type="{http://www.ttu.ee/idu0075/2015/ws/dormitory}dormitoryTenantType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "responseCode",
    "dormitoryTenant"
})
@XmlRootElement(name = "addDormitoryTenantResponse")
public class AddDormitoryTenantResponse {

    @XmlElement(required = true)
    protected BigInteger responseCode;
    @XmlElement(required = true)
    protected DormitoryTenantType dormitoryTenant;

    /**
     * Gets the value of the responseCode property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getResponseCode() {
        return responseCode;
    }

    /**
     * Sets the value of the responseCode property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setResponseCode(BigInteger value) {
        this.responseCode = value;
    }

    /**
     * Gets the value of the dormitoryTenant property.
     * 
     * @return
     *     possible object is
     *     {@link DormitoryTenantType }
     *     
     */
    public DormitoryTenantType getDormitoryTenant() {
        return dormitoryTenant;
    }

    /**
     * Sets the value of the dormitoryTenant property.
     * 
     * @param value
     *     allowed object is
     *     {@link DormitoryTenantType }
     *     
     */
    public void setDormitoryTenant(DormitoryTenantType value) {
        this.dormitoryTenant = value;
    }

}
