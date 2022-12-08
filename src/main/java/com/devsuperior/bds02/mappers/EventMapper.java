package com.devsuperior.bds02.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devsuperior.bds02.dto.EventDTO;
import com.devsuperior.bds02.entities.Event;

@Service
public class EventMapper {

	public EventDTO convertEntityToDTO(Event entity) {
		EventDTO dto = new EventDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		dto.setDate(entity.getDate());
		dto.setUrl(entity.getUrl());
		dto.setCityId(entity.getCity().getId());
		return dto;
	}
	
	public List<EventDTO> convertEntityListToDTOList(List<Event> entities) {
		return entities
				.stream()
				.map(entity -> convertEntityToDTO(entity))
				.collect(Collectors.toList());
	}
	
	public void copyDTOToEntity(EventDTO dto, Event entity) {
		entity.setName(dto.getName());
		entity.setDate(dto.getDate());
		entity.setUrl(dto.getUrl());
	}
	
}
