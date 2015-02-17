package com.guitar.repository;

import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.guitar.model.Manufacturer;

@Repository
public class ManufacturerRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ManufacturerJpaRepository manufacturerJpaRepository;
	
	/**
	 * Create
	 */
	public Manufacturer create(Manufacturer man) {
		return manufacturerJpaRepository.saveAndFlush(man);
	}

	/**
	 * Update
	 */
	public Manufacturer update(Manufacturer man) {
		return manufacturerJpaRepository.saveAndFlush(man);
	}

	/**
	 * Delete
	 */
	public void delete(Manufacturer man) {
		manufacturerJpaRepository.delete(man);
	}

	/**
	 * Find
	 */
	public Manufacturer find(Long id) {
		return manufacturerJpaRepository.findOne(id);
	}

	/**
	 * Custom finder
	 */
	public List<Manufacturer> getManufacturersFoundedBeforeDate(Date date) {
		return manufacturerJpaRepository.findByFoundedDateBefore(date);
	}

	/**
	 * Custom finder
	 */
	public Manufacturer getManufacturerByName(String name) {
		Manufacturer man = (Manufacturer)entityManager
				.createQuery("select m from Manufacturer m where m.name like :name")
				.setParameter("name", name + "%").getSingleResult();
		return man;
	}

	/**
	 * Native Query finder
	 */
	public List<Manufacturer> getManufacturersThatSellModelsOfType(String modelType) {
		return manufacturerJpaRepository.getAllThatSellAcoustics(modelType);
	}
}
