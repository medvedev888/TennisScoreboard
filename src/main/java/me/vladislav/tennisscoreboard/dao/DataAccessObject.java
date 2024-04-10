package me.vladislav.tennisscoreboard.dao;


import java.util.List;
import java.util.Optional;

public interface DataAccessObject<T> {
    public Optional<T> getById(int id);
    public Optional<List<T>> getList();
    public void add(T t);
}
