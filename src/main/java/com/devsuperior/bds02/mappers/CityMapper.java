package com.devsuperior.bds02.mappers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.devsuperior.bds02.dto.CityDTO;
import com.devsuperior.bds02.entities.City;

@Service
public class CityMapper {

	public CityDTO convertEntityToDTO(City entity) {
		CityDTO dto = new CityDTO();
		dto.setId(entity.getId());
		dto.setName(entity.getName());
		return dto;
	}
	
	public List<CityDTO> convertEntityListToDTOList(List<City> entities) {
		return entities
				.stream()
				.map(entity -> convertEntityToDTO(entity))
				.collect(Collectors.toList());
	}
	
	public void copyDTOToEntity(CityDTO dto, City entity) {
		entity.setName(dto.getName());
	}
}
