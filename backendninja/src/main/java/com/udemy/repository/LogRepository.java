package com.udemy.repository;

import java.io.Serializable;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.udemy.entity.LogEntity;

@Repository("logRepository")
public interface LogRepository extends JpaRepository<LogEntity, Serializable>{

}
