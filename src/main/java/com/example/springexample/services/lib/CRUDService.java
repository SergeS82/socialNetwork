package com.example.springexample.services.lib;

import java.util.List;

public interface CRUDService<T> {
    public T getById(Integer id);
    public List<T> getTop(Integer item, Integer count);
    public void create (T item);
    public void update(T item);
    public void delete(Integer id);
}
