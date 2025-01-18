package pe.mil.fap.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1/home")
public class HomeController {

	@GetMapping({"/",""})
	public ResponseEntity<String> home() {
		String msg = "Hola Mundo";
		return new ResponseEntity<>(msg, HttpStatus.OK);
	}
}
