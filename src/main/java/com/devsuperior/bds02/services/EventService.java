package com.devsuperior.bds02.services;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.Event;
import com.devsuperior.bds02.exceptions.ResourceNotFoundException;
import com.devsuperior.bds02.mappers.EventMapper;
import com.devsuperior.bds02.repositories.CityRepository;
import com.devsuperior.bds02.repositories.EventRepository;

@Service
public class EventService {

	@Autowired
	private EventRepository repository;
	
	@Autowired
	private CityRepository cityRepository;
	
	@Autowired
	private EventMapper mapper;
	
	@Transactional
	public EventDTO update(EventDTO dto, Long id) {
		try {
			Event entity = repository.getOne(id);
			copyDTOToEntity(dto, entity);
			entity = repository.save(entity);
			return mapper.convertEntityToDTO(entity);
		}
		catch(EntityNotFoundException e) {
			throw new ResourceNotFoundException("Event not find by ID: " + id);
		}
	}
	
	private void copyDTOToEntity(EventDTO dto, Event entity) {
		mapper.copyDTOToEntity(dto, entity);
		entity.setCity(cityRepository.getOne(dto.getCityId()));
	}
}
