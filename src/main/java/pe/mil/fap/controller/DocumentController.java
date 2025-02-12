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
import pe.mil.fap.exception.NotFoundException;
import pe.mil.fap.service.general.inf.DocumentService;
import pe.mil.fap.utils.constants.MessageConstants;

@RestController
@RequestMapping("/v1/documents")
public class DocumentController {

	@Autowired
	private DocumentService documentService;
	
    @GetMapping("/page")
    public ResponseEntity<?> getAllDocuments(
            @RequestParam String filter,
            @PageableDefault(size = 5) Pageable pageable) {
        PageDTO<DocumentEntity> documents = documentService.pageDocuments(filter, pageable); 
		return new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_PAGE_RETURNED, documents), HttpStatus.OK);
    } 
    
	@GetMapping
	public ResponseEntity<ResponseDTO> findAll(){
	    List<DocumentEntity> documents = documentService.findAll();
	    return Optional.ofNullable(documents)
	    		       .filter(list -> !list.isEmpty())
	    		       .map(list -> new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_DATA_RETURNED, list), HttpStatus.OK))
	    		       .orElseGet(()-> new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.INFO_MESSAGE_NO_DATA_FOUND, documents), HttpStatus.OK));
	}

	@GetMapping("/findAllHistory")
	public ResponseEntity<ResponseDTO> findAllHistory(){
	    List<DocumentEntity> documents = documentService.findAllHistory();
	    return Optional.ofNullable(documents)
	    		       .filter(list -> !list.isEmpty())
	    		       .map(list -> new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_DATA_RETURNED, list), HttpStatus.OK))
	    		       .orElseGet(()-> new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.INFO_MESSAGE_NO_DATA_FOUND, documents), HttpStatus.OK));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseDTO> findById(@PathVariable(name = "id") Long id){
		Optional<DocumentEntity> optDocumentFound = documentService.findByID(id);
		return optDocumentFound.map(document -> new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_DOCUMENT_FOUND, document), HttpStatus.OK))
							   .orElseThrow(() -> new NotFoundException(MessageConstants.INFO_MESSAGE_DOCUMENT_NOT_FOUND));
	}
	
	
	@PostMapping
	public ResponseEntity<ResponseDTO> save(@Valid @RequestBody DocumentEntity document) {
		return new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_DOCUMENT_CREATED, documentService.save(document)), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<ResponseDTO> update(@Valid @RequestBody DocumentEntity document) {
		return new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_DOCUMENT_UPDATED, documentService.save(document)), HttpStatus.OK);
	} 
}
