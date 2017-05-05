
package ee.ttu.idu0075._2015.ws.dormitory;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for dormitoryTenantListType complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="dormitoryTenantListType"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="dormitoryTenant" type="{http://www.ttu.ee/idu0075/2015/ws/dormitory}dormitoryTenantType" maxOccurs="unbounded" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "dormitoryTenantListType", propOrder = {
    "dormitoryTenant"
})
public class DormitoryTenantListType {

    protected List<DormitoryTenantType> dormitoryTenant;

    /**
     * Gets the value of the dormitoryTenant property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the dormitoryTenant property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getDormitoryTenant().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link DormitoryTenantType }
     * 
     * 
     */
    public List<DormitoryTenantType> getDormitoryTenant() {
        if (dormitoryTenant == null) {
            dormitoryTenant = new ArrayList<DormitoryTenantType>();
        }
        return this.dormitoryTenant;
    }

}
