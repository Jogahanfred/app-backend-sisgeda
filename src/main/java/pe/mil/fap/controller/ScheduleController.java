package pe.mil.fap.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired; 
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity; 
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam; 
import org.springframework.web.bind.annotation.RestController; 

import jakarta.validation.Valid;
import pe.mil.fap.dto.helpers.PageDTO;
import pe.mil.fap.dto.response.ResponseDTO;
import pe.mil.fap.entity.DocumentEntity;
import pe.mil.fap.entity.ScheduleEntity;
import pe.mil.fap.entity.VisitEntity;
import pe.mil.fap.entity.WorkingHoursEntity;
import pe.mil.fap.exception.NotFoundException;
import pe.mil.fap.service.processes.inf.VisitService;
import pe.mil.fap.service.processes.inf.ScheduleService;
import pe.mil.fap.utils.constants.MessageConstants;

@RestController
@RequestMapping("/v1/schedules")
public class ScheduleController {

	@Autowired
	private ScheduleService scheduleService;
 
	  
	@GetMapping
	public ResponseEntity<ResponseDTO> findAll(){
		    List<ScheduleEntity> schedules = scheduleService.findAll();
		    return Optional.ofNullable(schedules)
		    		       .filter(list -> !list.isEmpty())
		    		       .map(list -> new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_DATA_RETURNED, list), HttpStatus.OK))
		    		       .orElseGet(()-> new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.INFO_MESSAGE_NO_DATA_FOUND, schedules), HttpStatus.OK));
		}
		
	@PostMapping
	public ResponseEntity<ResponseDTO> save(@Valid @RequestBody ScheduleEntity schedule) {
		return new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_SCHEDULE_CREATED, scheduleService.save(schedule)), HttpStatus.CREATED);
	} 
	
}
