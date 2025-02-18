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
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import jakarta.validation.Valid;
import pe.mil.fap.dto.helpers.PageDTO;
import pe.mil.fap.dto.response.ResponseDTO;
import pe.mil.fap.entity.VisitorEntity;
import pe.mil.fap.exception.NotFoundException;
import pe.mil.fap.service.general.inf.VisitorService;
import pe.mil.fap.utils.constants.MessageConstants;

@RestController
@RequestMapping("/v1/visitors")
public class VisitorController {

	@Autowired
	private VisitorService visitorService;
	
    @GetMapping("/page")
    public ResponseEntity<?> getAllVisitors(
            @RequestParam String filter,
            @PageableDefault(size = 5) Pageable pageable) {
        PageDTO<VisitorEntity> visitors = visitorService.pageVisitors(filter, pageable); 
		return new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_PAGE_RETURNED, visitors), HttpStatus.OK);
    } 
    
	@GetMapping
	public ResponseEntity<ResponseDTO> findAll(){
	    List<VisitorEntity> visitors = visitorService.findAll();
	    return Optional.ofNullable(visitors)
	    		       .filter(list -> !list.isEmpty())
	    		       .map(list -> new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_DATA_RETURNED, list), HttpStatus.OK))
	    		       .orElseGet(()-> new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.INFO_MESSAGE_NO_DATA_FOUND, visitors), HttpStatus.OK));
	}

	@GetMapping("/findAllHistory")
	public ResponseEntity<ResponseDTO> findAllHistory(){
	    List<VisitorEntity> visitors = visitorService.findAllHistory();
	    return Optional.ofNullable(visitors)
	    		       .filter(list -> !list.isEmpty())
	    		       .map(list -> new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_DATA_RETURNED, list), HttpStatus.OK))
	    		       .orElseGet(()-> new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.INFO_MESSAGE_NO_DATA_FOUND, visitors), HttpStatus.OK));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseDTO> findById(@PathVariable(name = "id") Long id){
		Optional<VisitorEntity> optVisitorFound = visitorService.findByID(id);
		return optVisitorFound.map(visitor -> new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_VISITOR_FOUND, visitor), HttpStatus.OK))
							   .orElseThrow(() -> new NotFoundException(MessageConstants.INFO_MESSAGE_VISITOR_NOT_FOUND));
	}
	
	@GetMapping("/findByNroDocument/{document}")
	public ResponseEntity<ResponseDTO> findByNroDocument(@PathVariable(name = "document") String document){
		Optional<VisitorEntity> optVisitorFound = visitorService.findByDocumento(document);
		return optVisitorFound.map(visitor -> new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_VISITOR_FOUND, visitor), HttpStatus.OK))
							   .orElseThrow(() -> new NotFoundException(MessageConstants.INFO_MESSAGE_VISITOR_NOT_FOUND));
	}
	
	
	
	@PostMapping
	public ResponseEntity<ResponseDTO> save(@Valid @RequestBody VisitorEntity visitor) {
		return new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_VISITOR_CREATED, visitorService.save(visitor)), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<ResponseDTO> update(@Valid @RequestBody VisitorEntity visitor) {
		return new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_VISITOR_UPDATED, visitorService.save(visitor)), HttpStatus.OK);
	}

	@PostMapping("/saveWithImage")
	public ResponseEntity<ResponseDTO> saveWithImage(@RequestPart("visitor") VisitorEntity visitor, @RequestPart("file") MultipartFile file){
		return new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_VISITOR_CREATED, visitorService.saveWithImage(visitor, file)), HttpStatus.CREATED);
	}

	@PutMapping("/updateWithImage") 
	public ResponseEntity<?> updateWithImage(@RequestPart("visitor") VisitorEntity visitor, @RequestPart("file") MultipartFile file) {
		return new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_VISITOR_UPDATED, visitorService.updateWithImage(file, visitor)), HttpStatus.OK);
	}
	
}
