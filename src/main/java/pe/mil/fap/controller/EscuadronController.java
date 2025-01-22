package pe.mil.fap.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import pe.mil.fap.entity.EscuadronEntity;
import pe.mil.fap.service.inf.EscuadronService;

@RestController
@RequestMapping("/escuadrones")
public class EscuadronController {

	@Autowired
	private EscuadronService escuadronService;

	@GetMapping("/listar")
	public ResponseEntity<?> findAll() throws Exception {
		return new ResponseEntity<>(escuadronService.findAll(), HttpStatus.OK);
	}

	@PostMapping("guardar")
	public ResponseEntity<?> save(@RequestBody EscuadronEntity escuadron) throws Exception {
		return new ResponseEntity<>(escuadronService.save(escuadron), HttpStatus.CREATED);
	}

	@PostMapping("guardarAll")
	public ResponseEntity<?> saveAll(@RequestPart("escuadron") EscuadronEntity escuadron,
			@RequestPart("file") MultipartFile file) throws Exception {
		return new ResponseEntity<>(escuadronService.saveWithImage(escuadron, file), HttpStatus.CREATED);
	}

	@PutMapping("/{id}/image")
	public ResponseEntity<?> updateEscuadronImage(@PathVariable Long id, @RequestPart("file") MultipartFile file)
			throws Exception {
		Optional<EscuadronEntity> escuadron = escuadronService.findByID(id);
		if (escuadron.isPresent()) {
			EscuadronEntity updatedEscuadron = escuadronService.updateWithImage(file, escuadron.get());
			return new ResponseEntity<>(updatedEscuadron, HttpStatus.OK);
		} else {
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		}
	}

}
