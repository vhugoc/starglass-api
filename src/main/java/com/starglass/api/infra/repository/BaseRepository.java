package com.starglass.api.infra.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;

import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, String> {

    List<T> findAllByIsActiveTrue();

}
