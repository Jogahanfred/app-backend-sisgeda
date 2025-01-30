package pe.mil.fap.mappers.page;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import pe.mil.fap.dto.helpers.PageDTO;

@Component
public class PageMapper<T> {
	
	public PageDTO<T> toDTO(Page<T> page) {
		PageDTO<T> pageDTO = new PageDTO<>();
		pageDTO.setContent(page.getContent());
		pageDTO.setSize(page.getSize());
		pageDTO.setNumberOfElements(page.getNumberOfElements());
		pageDTO.setTotalPages(page.getTotalPages());
		pageDTO.setTotalElements(page.getTotalElements());
		return pageDTO;
	}
}
