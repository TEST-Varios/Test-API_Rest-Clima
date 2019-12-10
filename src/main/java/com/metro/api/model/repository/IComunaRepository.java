package com.metro.api.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.metro.api.model.entity.Comuna;

@Repository
public interface IComunaRepository  extends JpaRepository<Comuna, Long>{

}
