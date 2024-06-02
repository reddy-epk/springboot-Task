package model;

import java.util.UUID;

import jakarta.persistence.GeneratedValue;

@Entity
@Table(name = "managers")
public class Manager {
    @Id
    @GeneratedValue
    private UUID manager_id;
    private String full_name;
    private String mob_num;

    // getters and setters

    public UUID getManager_id() {
        return manager_id;
    }

    public void setManager_id(UUID manager_id) {
        this.manager_id = manager_id;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getMob_num() {
        return mob_num;
    }

    public void setMob_num(String mob_num) {
        this.mob_num = mob_num;
    }
}