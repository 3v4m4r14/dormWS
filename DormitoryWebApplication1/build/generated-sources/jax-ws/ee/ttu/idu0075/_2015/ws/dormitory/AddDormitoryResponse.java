
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
 *         &lt;element name="dormitory" type="{http://www.ttu.ee/idu0075/2015/ws/dormitory}dormitoryType"/&gt;
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
    "dormitory"
})
@XmlRootElement(name = "addDormitoryResponse")
public class AddDormitoryResponse {

    @XmlElement(required = true)
    protected BigInteger responseCode;
    @XmlElement(required = true)
    protected DormitoryType dormitory;

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
     * Gets the value of the dormitory property.
     * 
     * @return
     *     possible object is
     *     {@link DormitoryType }
     *     
     */
    public DormitoryType getDormitory() {
        return dormitory;
    }

    /**
     * Sets the value of the dormitory property.
     * 
     * @param value
     *     allowed object is
     *     {@link DormitoryType }
     *     
     */
    public void setDormitory(DormitoryType value) {
        this.dormitory = value;
    }

}
