package com.guitar.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.guitar.model.Location;

@Repository
public interface LocationJpaRepository extends JpaRepository<Location, Long> {
	List<Location> findByStateIgnoreCaseStartingWith(String stateName);
	Location findFirstByStateIgnoreCaseStartingWith(String stateName);
	List<Location> findByStateNotLikeOrderByStateAsc(String stateName);
	
	List<Location> findByStateIsOrCountryEquals(String value, String value2);
	List<Location> findByStateNot(String state);
	
}
