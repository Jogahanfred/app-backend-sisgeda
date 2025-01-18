package pe.mil.fap.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pe.mil.fap.entity.EscuadronEntity;
import pe.mil.fap.service.inf.EscuadronService;

@RestController
@RequestMapping("/escuadrones")
public class EscuadronController {

	@Autowired
	private EscuadronService escuadronService;
	
	@GetMapping("/listar")
	public ResponseEntity<?> findAll()throws Exception{
		return new ResponseEntity<>(escuadronService.findAll(),HttpStatus.OK);
	}
	
	@PostMapping("guardar")
	public ResponseEntity<?> save(@RequestBody EscuadronEntity escuadron) throws Exception{
		return new ResponseEntity<>(escuadronService.save(escuadron), HttpStatus.CREATED);
	}
}
