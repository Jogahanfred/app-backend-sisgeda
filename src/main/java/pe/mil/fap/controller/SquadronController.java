package pe.mil.fap.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus; 
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
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
import pe.mil.fap.dto.response.ResponseDTO;
import pe.mil.fap.entity.SquadronEntity;
import pe.mil.fap.exception.NotFoundException;
import pe.mil.fap.service.general.inf.SquadronService;
import pe.mil.fap.utils.constants.MessageConstants;

@RestController
@RequestMapping("/v1/squadrons")
public class SquadronController {

	@Autowired
	private SquadronService squadronService;
	
    @GetMapping("/page")
    public ResponseEntity<?> getAllSquadrons(
            @RequestParam String filter,
            @PageableDefault(size = 5) Pageable pageable) {
        Page<SquadronEntity> squadrons = squadronService.pageSquadrons(filter, pageable); 
		return new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_PAGE_RETURNED, squadrons), HttpStatus.OK);
    } 
    
	@GetMapping
	public ResponseEntity<ResponseDTO> findAll(){
	    List<SquadronEntity> squadrons = squadronService.findAll();
	    return Optional.ofNullable(squadrons)
	    		       .filter(list -> !list.isEmpty())
	    		       .map(list -> new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_DATA_RETURNED, list), HttpStatus.OK))
	    		       .orElseGet(()-> new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.INFO_MESSAGE_NO_DATA_FOUND, squadrons), HttpStatus.OK));
	}

	@GetMapping("/findAllHistory")
	public ResponseEntity<ResponseDTO> findAllHistory(){
	    List<SquadronEntity> squadrons = squadronService.findAllHistory();
	    return Optional.ofNullable(squadrons)
	    		       .filter(list -> !list.isEmpty())
	    		       .map(list -> new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_DATA_RETURNED, list), HttpStatus.OK))
	    		       .orElseGet(()-> new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.INFO_MESSAGE_NO_DATA_FOUND, squadrons), HttpStatus.OK));
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<ResponseDTO> findById(@PathVariable(name = "id") Long id){
		Optional<SquadronEntity> optSquadronFound = squadronService.findByID(id);
		return optSquadronFound.map(squadron -> new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_SQUADRON_FOUND, squadron), HttpStatus.OK))
							   .orElseThrow(() -> new NotFoundException(MessageConstants.INFO_MESSAGE_SQUADRON_NOT_FOUND));
	}
	
	
	@PostMapping
	public ResponseEntity<ResponseDTO> save(@Valid @RequestBody SquadronEntity squadron) {
		return new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_SQUADRON_CREATED, squadronService.save(squadron)), HttpStatus.CREATED);
	}
	
	@PutMapping
	public ResponseEntity<ResponseDTO> update(@Valid @RequestBody SquadronEntity squadron) {
		return new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_SQUADRON_UPDATED, squadronService.save(squadron)), HttpStatus.OK);
	}

	@PostMapping("/saveWithImage")
	public ResponseEntity<ResponseDTO> saveAll(@RequestPart("squadron") SquadronEntity squadron, @RequestPart("file") MultipartFile file){
		return new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_SQUADRON_CREATED, squadronService.saveWithImage(squadron, file)), HttpStatus.CREATED);
	}

	@PutMapping("/{id}/image") 
	public ResponseEntity<?> updateSquadronImage(@PathVariable Long id, @RequestPart("file") MultipartFile file) {
		SquadronEntity squadron = new SquadronEntity();
		squadron.setIdSquadron(id);
		return new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_SQUADRON_UPDATED, squadronService.updateWithImage(file, squadron)), HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<ResponseDTO> delete(@PathVariable(name = "id") Long id) {
		SquadronEntity squadron = new SquadronEntity();
		squadron.setIdSquadron(id);
		return new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_SQUADRON_DELETED, squadronService.delete(squadron)), HttpStatus.OK);
	}
}
