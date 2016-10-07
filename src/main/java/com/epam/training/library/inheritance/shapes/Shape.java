package com.epam.training.library.inheritance.shapes;

import java.util.Date;

public abstract class Shape implements ICreationDateAware {

    private Date created;

    private String color;

    public Shape(String color) {
        this.color = color;
    }

    public Shape() {
        // nothing
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getType() {
        return "Shape";
    }

    public abstract int calculateSquare();

    @Override
    public Date getCreated() {
        return created;
    }

}
