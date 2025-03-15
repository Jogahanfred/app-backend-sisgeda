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
import pe.mil.fap.dto.helpers.DatasetGraphicDTO;
import pe.mil.fap.dto.helpers.DatasetNumberEntriesPerPersonDTO;
import pe.mil.fap.dto.helpers.GraphicPatternsDTO;
import pe.mil.fap.dto.helpers.PageDTO;
import pe.mil.fap.dto.helpers.StatsWidgetDTO;
import pe.mil.fap.dto.response.ResponseDTO;
import pe.mil.fap.entity.DocumentEntity;
import pe.mil.fap.exception.NotFoundException;
import pe.mil.fap.service.general.inf.DocumentService;
import pe.mil.fap.service.general.inf.GraphicService;
import pe.mil.fap.utils.constants.MessageConstants;

@RestController
@RequestMapping("/v1/graphics")
public class GraphicController {

	@Autowired
	private GraphicService graphicService;
	 
	@GetMapping("/findDatasetGraphic")
	public ResponseEntity<ResponseDTO> findDatasetGraphic(){
		List<DatasetGraphicDTO> dataset = graphicService.findDatasetGraphic();
	    return Optional.ofNullable(dataset)
	    		       .filter(list -> !list.isEmpty())
	    		       .map(list -> new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_DATA_RETURNED, list), HttpStatus.OK))
	    		       .orElseGet(()-> new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.INFO_MESSAGE_NO_DATA_FOUND, dataset), HttpStatus.OK)); 
	}	
	
	@GetMapping("/findReportNumberEntriesPerPersonDTO")
	public ResponseEntity<ResponseDTO> findReportNumberEntriesPerPersonDTO(@RequestParam(value = "month") String month){
		List<DatasetNumberEntriesPerPersonDTO> dataset = graphicService.findReportNumberEntriesPerPersonDTO(month);
	    return Optional.ofNullable(dataset)
	    		       .filter(list -> !list.isEmpty())
	    		       .map(list -> new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_DATA_RETURNED, list), HttpStatus.OK))
	    		       .orElseGet(()-> new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.INFO_MESSAGE_NO_DATA_FOUND, dataset), HttpStatus.OK)); 
	}
	
	@GetMapping("/findStatsWidgets")
	public ResponseEntity<ResponseDTO> findStatsWidgets(){
		Optional<StatsWidgetDTO> stats = graphicService.findStatsWidgets();
	    return Optional.ofNullable(stats)
	    		       .filter(list -> !list.isEmpty())
	    		       .map(list -> new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.SUCCESS_MESSAGE_STATS_WIDGET_RETURNED, list), HttpStatus.OK))
	    		       .orElseGet(()-> new ResponseEntity<>(ResponseDTO.createSuccess(MessageConstants.INFO_MESSAGE_NO_DATA_FOUND, stats), HttpStatus.OK)); 
	}
}
