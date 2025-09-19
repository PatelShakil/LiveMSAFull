/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/StatelessEjbClass.java to edit this template
 */
package ejb;

import com.shakilpatel.livemsaresource.entity.District;
import com.shakilpatel.livemsaresource.entity.Land;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import java.util.List;

/**
 *
 * @author Acer
 */
@Stateless
public class ReportBean implements ReportBeanLocal {
    
    @PersistenceContext(name = "mypu")
    EntityManager em;

    @Override
    public List<District> getDistricts() {
        return em.createNamedQuery("District.findAll").getResultList();
    }

    @Override
    public List<Land> getFertileLandByDistrict(int districtId) {
        District dis = em.find(District.class, districtId);
        System.out.println(districtId);
        List<Land> lands = em.createQuery("SELECT l FROM Land l WHERE l.districtId=:id").setParameter("id",dis).getResultList();
        System.out.println(lands.toString());
        return lands;
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")
    
    
    
}
