/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSF/JSFManagedBean.java to edit this template
 */
package cdi;

import client.ReportClient;
import entity.District;
import entity.Land;
import jakarta.inject.Named;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.ws.rs.core.GenericType;
import java.io.Serializable;
import java.util.List;

/**
 *
 * @author Acer
 */
@Named(value = "landReportBean")
@SessionScoped
public class LandReportBean implements Serializable{
    
    @Inject ReportClient client;
    
    List<District> districts;
    List<Land> lands;
    
    private int districtId;

    /**
     * Creates a new instance of LandReportBean
     */
    public LandReportBean() {
        districtId = 0;
    }

    public List<District> getDistricts() {
        districts = client.getDistricts().readEntity(new GenericType<List<District>>(){});
        System.out.println(districts.toString());
        return districts;
    }
    public List<Land> getLands() {
        return lands;
    }
    public void loadLands() {
        lands = client.getLands(districtId).readEntity(new GenericType<List<Land>>() {});
        System.out.println(lands.toString());
    }

    public void setDistricts(List<District> districts) {
        this.districts = districts;
    }
    
   

    public void setLands(List<Land> lands) {
        this.lands = lands;
    }

    public int getDistrictId() {
        return districtId;
    }

    public void setDistrictId(int districtId) {
        System.out.println(districtId);
        this.districtId = districtId;
    }
    
    
    
}
