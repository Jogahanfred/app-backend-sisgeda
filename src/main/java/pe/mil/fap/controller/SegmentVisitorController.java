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
import pe.mil.fap.entity.SegmentVisitorEntity;
import pe.mil.fap.entity.VisitEntity;
import pe.mil.fap.exception.NotFoundException;
import pe.mil.fap.service.processes.inf.SegmentVisitorService;
import pe.mil.fap.service.processes.inf.VisitService;
import pe.mil.fap.utils.constants.MessageConstants;
import pe.mil.fap.utils.enums.PersonalSituationEnum;
import pe.mil.fap.utils.enums.SegmentTypeEnum;

@RestController
@RequestMapping("/v1/segmentVisitor")
public class SegmentVisitorController {

	@Autowired
	private SegmentVisitorService segmentVisitorService;

	@PutMapping("/entranceSegmentVisitor")
	public ResponseEntity<ResponseDTO> entranceSegmentVisitor(@RequestParam("idVisit") Long idVisit, @RequestParam("idVisitor") Long idVisitor) {
		SegmentVisitorEntity segment = segmentVisitorService.entranceSegmentVisitor(idVisit, idVisitor);
		return new ResponseEntity<>(ResponseDTO.createSuccess(segment.getCoSegmentType().name().equals(SegmentTypeEnum.DETENTION.name())? MessageConstants.INFO_MESSAGE_VISITOR_NOT_PERMITTED : MessageConstants.SUCCESS_MESSAGE_VISIT_ENTRANCE, segment), segment.getCoSegmentType().name().equals(SegmentTypeEnum.DETENTION.name())? HttpStatus.OK : HttpStatus.CREATED);
	}
	
	@PutMapping("/exitSegmentVisitor")
	public ResponseEntity<ResponseDTO> exitSegmentVisitor(@RequestParam("idSegmentVisitor") Long idSegmentVisitor) {
		return new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_VISIT_EXIT, segmentVisitorService.exitSegmentVisitor(idSegmentVisitor)), HttpStatus.CREATED);
	}	
	
}
