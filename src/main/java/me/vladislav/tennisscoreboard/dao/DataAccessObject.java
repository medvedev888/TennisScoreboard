package me.vladislav.tennisscoreboard.dao;


import java.util.List;

public interface DataAccessObject<T> {
    public T getById(int id);
    public List<T> getList();
    public void add(T t);
}
