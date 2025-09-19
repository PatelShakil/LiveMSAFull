/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB40/SessionLocal.java to edit this template
 */
package ejb;

import com.shakilpatel.livemsaresource.entity.District;
import com.shakilpatel.livemsaresource.entity.Land;
import jakarta.ejb.Local;
import java.util.List;

/**
 *
 * @author Acer
 */
@Local
public interface ReportBeanLocal {
    List<District> getDistricts();
    List<Land> getFertileLandByDistrict(int districtId);
    
    
}
