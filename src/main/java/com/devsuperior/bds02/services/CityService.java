package com.devsuperior.bds02.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
import com.devsuperior.bds02.exceptions.DatabaseException;
import com.devsuperior.bds02.exceptions.ResourceNotFoundException;
import com.devsuperior.bds02.mappers.CityMapper;
import com.devsuperior.bds02.repositories.CityRepository;

@Service
public class CityService {

	@Autowired
	private CityRepository repository;
	
	@Autowired
	private CityMapper mapper;
	
	@Transactional(readOnly = true)
	public List<CityDTO> findAll() {
		List<City> entities = repository.findAll(Sort.by("name"));
		return mapper.convertEntityListToDTOList(entities);
	}
	
	@Transactional
	public CityDTO save(CityDTO dto) {
		City entity = new City();
		mapper.copyDTOToEntity(dto, entity);
		entity = repository.save(entity);
		return mapper.convertEntityToDTO(entity);
	}
	
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		}
		catch(EmptyResultDataAccessException e) {
			throw new ResourceNotFoundException("City not found by ID: " + id);
		}
		catch(DataIntegrityViolationException e) {
			throw new DatabaseException("Cannot delete city that already has one event registered");
		}
	}
}
