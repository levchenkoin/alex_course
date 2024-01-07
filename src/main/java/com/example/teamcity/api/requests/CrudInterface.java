package com.example.teamcity.api.requests;

public interface CrudInterface {
    public Object create(Object obj);

    public Object get(String id);

    Object update(Object obj);

    public Object delete(String id);
}
