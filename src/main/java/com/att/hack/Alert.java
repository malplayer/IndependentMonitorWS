package com.att.hack;

/**
 * Created by malcolm on 2/21/14.
 */
public class Alert {

    private String name;
    private String description;


    public Alert(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
