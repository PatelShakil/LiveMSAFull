/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.shakilpatel.livemsaresource.entity;

import jakarta.persistence.Basic;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.NamedQueries;
import jakarta.persistence.NamedQuery;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.io.Serializable;

/**
 *
 * @author Acer
 */
@Entity
@Table(name = "land")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Land.findAll", query = "SELECT l FROM Land l"),
    @NamedQuery(name = "Land.findById", query = "SELECT l FROM Land l WHERE l.id = :id"),
    @NamedQuery(name = "Land.findByPlotNo", query = "SELECT l FROM Land l WHERE l.plotNo = :plotNo"),
    @NamedQuery(name = "Land.findByArea", query = "SELECT l FROM Land l WHERE l.area = :area"),
    @NamedQuery(name = "Land.findByPerson", query = "SELECT l FROM Land l WHERE l.person = :person"),
    @NamedQuery(name = "Land.findByQuality", query = "SELECT l FROM Land l WHERE l.quality = :quality")})
public class Land implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "id")
    private Integer id;
    @Size(max = 255)
    @Column(name = "plot_no")
    private String plotNo;
    @Size(max = 255)
    @Column(name = "area")
    private String area;
    @Size(max = 255)
    @Column(name = "person")
    private String person;
    @Size(max = 255)
    @Column(name = "quality")
    private String quality;
    @JoinColumn(name = "village_id", referencedColumnName = "id")
    @ManyToOne
    private Village villageId;
    @JoinColumn(name = "district_id", referencedColumnName = "id")
    @ManyToOne
    private District districtId;

    public Land() {
    }

    public Land(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPlotNo() {
        return plotNo;
    }

    public void setPlotNo(String plotNo) {
        this.plotNo = plotNo;
    }

    public String getArea() {
        return area;
    }

    public void setArea(String area) {
        this.area = area;
    }

    public String getPerson() {
        return person;
    }

    public void setPerson(String person) {
        this.person = person;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public Village getVillageId() {
        return villageId;
    }

    public void setVillageId(Village villageId) {
        this.villageId = villageId;
    }

    public District getDistrictId() {
        return districtId;
    }

    public void setDistrictId(District districtId) {
        this.districtId = districtId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Land)) {
            return false;
        }
        Land other = (Land) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.shakilpatel.livemsaresource.entity.Land[ id=" + id + " ]";
    }
    
}
