package com.example.springexample.services.lib;

import java.util.List;

public interface CRUDService<T> {
    public T getById(Long id);
    public List<T> getTop(Long item, Integer count);
    public void create (T item);
    public void update(T item);
    public void delete(Long id);
}
