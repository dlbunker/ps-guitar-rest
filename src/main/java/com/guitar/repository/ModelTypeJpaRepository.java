package com.guitar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guitar.model.ModelType;

@Repository
public interface ModelTypeJpaRepository extends JpaRepository<ModelType, Long> {
	List<ModelType> findByNameIsNull();
}
