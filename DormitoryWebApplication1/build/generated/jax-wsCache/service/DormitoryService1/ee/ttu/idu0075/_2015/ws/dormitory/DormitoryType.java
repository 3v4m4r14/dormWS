
package ee.ttu.idu0075._2015.ws.dormitory;

import java.math.BigInteger;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dormitoryType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dormitoryType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="id" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="administrativeArea" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dormitoryAddress" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dormitoryCapacity" type="{http://www.w3.org/2001/XMLSchema}integer"/&gt;
 *         &lt;element name="dormitoryOwner" type="{http://www.w3.org/2001/XMLSchema}string"/&gt;
 *         &lt;element name="dormitoryCondition" type="{http://www.ttu.ee/idu0075/2015/ws/dormitory}dormitoryConditionType"/&gt;
 *         &lt;element name="dormitoryTenantList" type="{http://www.ttu.ee/idu0075/2015/ws/dormitory}dormitoryTenantListType"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dormitoryType", propOrder = {
    "id",
    "administrativeArea",
    "dormitoryAddress",
    "dormitoryCapacity",
    "dormitoryOwner",
    "dormitoryCondition",
    "dormitoryTenantList"
})
public class DormitoryType {

    @XmlElement(required = true)
    protected BigInteger id;
    @XmlElement(required = true)
    protected String administrativeArea;
    @XmlElement(required = true)
    protected String dormitoryAddress;
    @XmlElement(required = true)
    protected BigInteger dormitoryCapacity;
    @XmlElement(required = true)
    protected String dormitoryOwner;
    @XmlElement(required = true)
    protected String dormitoryCondition;
    @XmlElement(required = true)
    protected DormitoryTenantListType dormitoryTenantList;

    /**
     * Gets the value of the id property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setId(BigInteger value) {
        this.id = value;
    }

    /**
     * Gets the value of the administrativeArea property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAdministrativeArea() {
        return administrativeArea;
    }

    /**
     * Sets the value of the administrativeArea property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAdministrativeArea(String value) {
        this.administrativeArea = value;
    }

    /**
     * Gets the value of the dormitoryAddress property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDormitoryAddress() {
        return dormitoryAddress;
    }

    /**
     * Sets the value of the dormitoryAddress property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDormitoryAddress(String value) {
        this.dormitoryAddress = value;
    }

    /**
     * Gets the value of the dormitoryCapacity property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDormitoryCapacity() {
        return dormitoryCapacity;
    }

    /**
     * Sets the value of the dormitoryCapacity property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDormitoryCapacity(BigInteger value) {
        this.dormitoryCapacity = value;
    }

    /**
     * Gets the value of the dormitoryOwner property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDormitoryOwner() {
        return dormitoryOwner;
    }

    /**
     * Sets the value of the dormitoryOwner property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDormitoryOwner(String value) {
        this.dormitoryOwner = value;
    }

    /**
     * Gets the value of the dormitoryCondition property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDormitoryCondition() {
        return dormitoryCondition;
    }

    /**
     * Sets the value of the dormitoryCondition property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDormitoryCondition(String value) {
        this.dormitoryCondition = value;
    }

    /**
     * Gets the value of the dormitoryTenantList property.
     * 
     * @return
     *     possible object is
     *     {@link DormitoryTenantListType }
     *     
     */
    public DormitoryTenantListType getDormitoryTenantList() {
        return dormitoryTenantList;
    }

    /**
     * Sets the value of the dormitoryTenantList property.
     * 
     * @param value
     *     allowed object is
     *     {@link DormitoryTenantListType }
     *     
     */
    public void setDormitoryTenantList(DormitoryTenantListType value) {
        this.dormitoryTenantList = value;
    }

}
