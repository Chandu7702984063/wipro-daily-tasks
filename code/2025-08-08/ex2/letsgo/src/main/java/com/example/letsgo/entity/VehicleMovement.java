package com.example.letsgo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "vehicle_movement")
public class VehicleMovement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int vehId;
    private double lat;

    @Column(name = "longitude")
    private double longitude;
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getVehId() {
        return vehId;
    }

    public void setVehId(int vehId) {
        this.vehId = vehId;
    }

    public double getLat() {
        return lat;
    }

    public void setLat(double lat) {
        this.lat = lat;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }
}
