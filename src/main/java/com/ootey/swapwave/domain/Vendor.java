package com.ootey.swapwave.domain;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.ootey.swapwave.domain.util.CustomDateTimeDeserializer;
import com.ootey.swapwave.domain.util.CustomDateTimeSerializer;
import org.hibernate.annotations.Cache;
import org.hibernate.annotations.CacheConcurrencyStrategy;
import org.hibernate.annotations.Type;
import org.joda.time.DateTime;

import javax.persistence.*;
import java.io.Serializable;

/**
 * A Vendor.
 */
@Entity
@Table(name = "T_VENDOR")
@Cache(usage = CacheConcurrencyStrategy.NONSTRICT_READ_WRITE)
public class Vendor implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "dba")
    private String dba;

    @Column(name = "federal_id")
    private Long federalId;

    @Type(type = "org.jadira.usertype.dateandtime.joda.PersistentDateTime")
    @JsonSerialize(using = CustomDateTimeSerializer.class)
    @JsonDeserialize(using = CustomDateTimeDeserializer.class)
    @Column(name = "year_established", nullable = false)
    private DateTime yearEstablished;

    @Column(name = "ownership_type")
    private String ownershipType;

    @Column(name = "ownership_states")
    private String ownershipStates;

    @Column(name = "business_type")
    private String businessType;

    @Column(name = "location")
    private String location;

    @Column(name = "numberof_locations")
    private Integer numberofLocations;

    @Column(name = "years_in_present_loc")
    private Integer yearsInPresentLoc;

    @Column(name = "jbt_id")
    private Long jbtId;

    @Column(name = "jbt_rating")
    private Integer jbtRating;

    @Column(name = "bbbrating")
    private Integer bbbrating;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDba() {
        return dba;
    }

    public void setDba(String dba) {
        this.dba = dba;
    }

    public Long getFederalId() {
        return federalId;
    }

    public void setFederalId(Long federalId) {
        this.federalId = federalId;
    }

    public DateTime getYearEstablished() {
        return yearEstablished;
    }

    public void setYearEstablished(DateTime yearEstablished) {
        this.yearEstablished = yearEstablished;
    }

    public String getOwnershipType() {
        return ownershipType;
    }

    public void setOwnershipType(String ownershipType) {
        this.ownershipType = ownershipType;
    }

    public String getOwnershipStates() {
        return ownershipStates;
    }

    public void setOwnershipStates(String ownershipStates) {
        this.ownershipStates = ownershipStates;
    }

    public String getBusinessType() {
        return businessType;
    }

    public void setBusinessType(String businessType) {
        this.businessType = businessType;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Integer getNumberofLocations() {
        return numberofLocations;
    }

    public void setNumberofLocations(Integer numberofLocations) {
        this.numberofLocations = numberofLocations;
    }

    public Integer getYearsInPresentLoc() {
        return yearsInPresentLoc;
    }

    public void setYearsInPresentLoc(Integer yearsInPresentLoc) {
        this.yearsInPresentLoc = yearsInPresentLoc;
    }

    public Long getJbtId() {
        return jbtId;
    }

    public void setJbtId(Long jbtId) {
        this.jbtId = jbtId;
    }

    public Integer getJbtRating() {
        return jbtRating;
    }

    public void setJbtRating(Integer jbtRating) {
        this.jbtRating = jbtRating;
    }

    public Integer getBbbrating() {
        return bbbrating;
    }

    public void setBbbrating(Integer bbbrating) {
        this.bbbrating = bbbrating;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Vendor vendor = (Vendor) o;

        if (id != null ? !id.equals(vendor.id) : vendor.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return (int) (id ^ (id >>> 32));
    }

    @Override
    public String toString() {
        return "Vendor{" +
                "id=" + id +
                ", name='" + name + "'" +
                ", dba='" + dba + "'" +
                ", federalId='" + federalId + "'" +
                ", yearEstablished='" + yearEstablished + "'" +
                ", ownershipType='" + ownershipType + "'" +
                ", ownershipStates='" + ownershipStates + "'" +
                ", businessType='" + businessType + "'" +
                ", location='" + location + "'" +
                ", numberofLocations='" + numberofLocations + "'" +
                ", yearsInPresentLoc='" + yearsInPresentLoc + "'" +
                ", jbtId='" + jbtId + "'" +
                ", jbtRating='" + jbtRating + "'" +
                ", bbbrating='" + bbbrating + "'" +
                '}';
    }
}
