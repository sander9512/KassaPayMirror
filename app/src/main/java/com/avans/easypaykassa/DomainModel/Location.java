package com.avans.easypaykassa.DomainModel;

import java.io.Serializable;

/**
 * Created by barti on 02-Jun-17.
 */

public class Location implements Serializable{

    private int id;
    private String name;

    public Location(int id, String name) {
        this.id = id;
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
