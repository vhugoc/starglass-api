package com.starglass.api.infra.repository;

import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T> extends JpaRepository<T, String>, PagingAndSortingRepository<T, String> {

    List<T> findAllByIsActiveTrue();

    PageImpl<T> findAllByIsActiveTrue(Pageable pageable);

}
