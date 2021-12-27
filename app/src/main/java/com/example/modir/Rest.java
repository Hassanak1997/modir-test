package com.example.modir;

public class Rest {

    private String id;
    private String request_time;
    private String rest_time;
    private String description;
    private String status;
    private User user;

    public String getStatus() {
        return status;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getRequest_time() {
        return request_time;
    }

    public String getRest_time() {
        return rest_time;
    }

    public String getDescription() {
        return description;
    }
}
