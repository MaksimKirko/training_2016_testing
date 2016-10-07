package com.epam.training.library.inheritance.shapes;

import java.util.Date;

public class User implements ICreationDateAware {
    private Date created;

    private String username;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public Date getCreated() {
        return created;
    }

}
