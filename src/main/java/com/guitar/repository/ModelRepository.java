package com.guitar.repository;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Repository;

import com.guitar.model.Model;

@Repository
public class ModelRepository {
	@PersistenceContext
	private EntityManager entityManager;

	@Autowired
	private ModelJpaRepository modelJpaRepository;
	
	/**
	 * Create
	 */
	public Model create(Model mod) {
		return modelJpaRepository.saveAndFlush(mod);
	}

	/**
	 * Update
	 */
	public Model update(Model mod) {
		return modelJpaRepository.saveAndFlush(mod);
	}

	/**
	 * Delete
	 */
	public void delete(Model mod) {
		modelJpaRepository.delete(mod);
	}

	/**
	 * Find
	 */
	public Model find(Long id) {
		return modelJpaRepository.findOne(id);
	}

	/**
	 * Custom finder
	 */
	public List<Model> getModelsInPriceRange(BigDecimal lowest, BigDecimal highest) {
		return modelJpaRepository.findByPriceGreaterThanEqualAndPriceLessThanEqual(lowest, highest);
	}

	/**
	 * Custom finder
	 */
	public Page<Model> getModelsByPriceRangeAndWoodType(BigDecimal lowest, BigDecimal highest, String wood) {
		Sort sort = new Sort(Sort.Direction.ASC, "name");
		Pageable page = new PageRequest(0, 2, sort);
		return modelJpaRepository.queryByPriceRangeAndWoodType(lowest, highest, "%" + wood + "%", page);
	}

	/**
	 * NamedQuery finder
	 */
	public List<Model> getModelsByType(String modelType) {
		return modelJpaRepository.findAllModelsByType(modelType);
	}

	/**
	 * Count
	 */
	public Long getModelCount() {
		CriteriaBuilder qb = entityManager.getCriteriaBuilder();
		CriteriaQuery<Long> cq = qb.createQuery(Long.class);
		cq.select(qb.count(cq.from(Model.class)));
		return entityManager.createQuery(cq).getSingleResult();
	}
}
