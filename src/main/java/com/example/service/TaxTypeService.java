package com.example.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.model.TaxType;
import com.example.repository.TaxTypeRepository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class TaxTypeService {

	@Autowired
	private TaxTypeRepository taxTypeRepository;

	public List<TaxType> findAll() {
		return taxTypeRepository.findAll();
	}

	public Optional<TaxType> findOne(Long id) {
		return taxTypeRepository.findById(id);
	}

	@Transactional(readOnly = false)
	public TaxType save(TaxType entity) {
		return taxTypeRepository.save(entity);
	}

	@Transactional(readOnly = false)
	public void delete(TaxType entity) {
		taxTypeRepository.delete(entity);
	}
}
