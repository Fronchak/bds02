package com.devsuperior.bds02.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;
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
}
