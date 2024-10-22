package com.example.entity;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "base_followmessage")
public class MaterialRecord {

    @Id
    private String id;  // 主键

    @Column(name = "up_site")
    private String upSite;

    private String down_site;
    private String material_name;
    private LocalDateTime create_date;
    private LocalDateTime up_date;

    @Override
    public String toString() {
        return "MaterialRecord{" +
                "id='" + id + '\'' +
                ", up_site='" + upSite + '\'' +
                ", down_site='" + down_site + '\'' +
                ", material_name='" + material_name + '\'' +
                ", create_date=" + create_date +
                ", up_date=" + up_date +
                '}';
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUpSite() {
        return upSite;
    }

    public void setUpSite(String up_site) {
        this.upSite = up_site;
    }

    public String getDown_site() {
        return down_site;
    }

    public void setDown_site(String down_site) {
        this.down_site = down_site;
    }

    public String getMaterial_name() {
        return material_name;
    }

    public void setMaterial_name(String material_name) {
        this.material_name = material_name;
    }

    public LocalDateTime getCreate_date() {
        return create_date;
    }

    public void setCreate_date(LocalDateTime create_date) {
        this.create_date = create_date;
    }

    public LocalDateTime getUp_date() {
        return up_date;
    }

    public void setUp_date(LocalDateTime up_date) {
        this.up_date = up_date;
    }
}
