package com.github.gleanv0.bazar.service;

import java.util.List;
import java.util.Optional;

public interface IService<ID, T> {

    Optional<T> get(ID id);

    List<T> getAll();

    Optional<T> edit(ID id, T entity);

    T save(T entity);

    void delete(ID id);

}
