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
import pe.mil.fap.dto.helpers.VisitScheduleByVisitorDTO;
import pe.mil.fap.dto.response.ResponseDTO;
import pe.mil.fap.entity.SquadronEntity;
import pe.mil.fap.entity.VisitEntity;
import pe.mil.fap.exception.NotFoundException;
import pe.mil.fap.service.processes.inf.VisitService;
import pe.mil.fap.utils.constants.MessageConstants;
import pe.mil.fap.utils.enums.PersonalSituationEnum;

@RestController
@RequestMapping("/v1/visits")
public class VisitController {

	@Autowired
	private VisitService visitService;
	
	@GetMapping
	public ResponseEntity<ResponseDTO> findAll(){
	    List<VisitEntity> visits = visitService.findAll();
	    return Optional.ofNullable(visits)
	    		       .filter(list -> !list.isEmpty())
	    		       .map(list -> new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_DATA_RETURNED, list), HttpStatus.OK))
	    		       .orElseGet(()-> new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.INFO_MESSAGE_NO_DATA_FOUND, visits), HttpStatus.OK));
	}

	@GetMapping("/findVisitsScheduledOnTheDayByNuDocument")
	public ResponseEntity<ResponseDTO> findVisitsScheduledOnTheDayByNuDocument(@RequestParam(value = "document") String document){
	    VisitScheduleByVisitorDTO visitsByVisitor = visitService.findVisitsScheduledOnTheDayByNuDocument(document);
	    return Optional.ofNullable(visitsByVisitor.getVisits()) 
 		               .filter(list -> !list.isEmpty())
	    		       .map(list -> new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.INFO_MESSAGE_DATA_RETURNED_VISIT_BY_DOCUMENT, visitsByVisitor), HttpStatus.OK))
	    		       .orElseThrow(()-> new NotFoundException(MessageConstants.INFO_MESSAGE_NO_DATA_FOUND_VISIT_BY_DOCUMENT));
	}
	@GetMapping("/{id}")
	public ResponseEntity<ResponseDTO> findById(@PathVariable(name = "id") Long id){
		Optional<VisitEntity> optVisitFound = visitService.findByID(id);
		return optVisitFound.map(visit -> new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_VISIT_FOUND, visit), HttpStatus.OK))
							   .orElseThrow(() -> new NotFoundException(MessageConstants.INFO_MESSAGE_VISIT_NOT_FOUND));
	}
	
	@PostMapping
	public ResponseEntity<ResponseDTO> save(@Valid @RequestBody VisitEntity visit) {
		return new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_VISIT_CREATED, visitService.save(visit)), HttpStatus.CREATED);
	}
	
	@PutMapping("/updateSituationVisitor")
	public ResponseEntity<ResponseDTO> updateSituationVisitor(@RequestParam(name = "idVisitor") Long idVisitor,@RequestParam(name = "idVisit") Long idVisit , @RequestParam(name = "coSituation") PersonalSituationEnum coSituation) {
		return new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_VISITOR_UPDATED_SITUATION, visitService.updateSituationVisitor(idVisit, idVisitor, coSituation)), HttpStatus.OK);
	} 
}
