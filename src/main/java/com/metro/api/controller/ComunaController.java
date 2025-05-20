package com.metro.api.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.metro.api.model.repository.IComunaRepository;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import com.metro.api.model.entity.Comuna;
import com.metro.api.model.entity.ComunaXml;
import com.metro.api.exception.ResourceNotFoundException;

@Api(value = "Datos del tiempo en comunas de Santiago")
@RestController
@RequestMapping("/api/v1")
public class ComunaController {
	
	@Value("${endpointjson}")
	String endpointjson;
	
	@Value("${endpoint}")
	String endpoint;
	
	@Autowired
	private IComunaRepository comunaRepository;
	
	@Autowired
	private RestTemplate restTemplate;	
	

	@ApiOperation(value = "Lista completa de datos de la comuna", response = Comuna.class)
	@ApiResponses(value = {
        @ApiResponse(code = 200, message = "Successfully retrieved list"),
        @ApiResponse(code = 401, message = "You are not authorized to view the resource"),
        @ApiResponse(code = 403, message = "Accessing the resource you were trying to reach is forbidden"),
        @ApiResponse(code = 404, message = "The resource you were trying to reach is not found")
    })
	
	@GetMapping("/comuna-objeto")
	public ComunaXml getComunasObjeto(){
		return restTemplate.getForObject(endpointjson, ComunaXml.class);
	}
	
	@GetMapping("/comuna-entidad")
	public Comuna getComunasEntidad() {
		final ResponseEntity<Comuna> responseEntity = restTemplate.getForEntity(endpointjson, Comuna.class);
		return responseEntity.getBody();
	}
	
	
	@GetMapping("/comuna")
	public List<Comuna> getAllComunas() {
		return comunaRepository.findAll();
	}

	@GetMapping("/comuna/{id}")
	public ResponseEntity<Comuna> getComunaById(@PathVariable(value = "id") Long comunaId)
			throws ResourceNotFoundException {
		Comuna comuna = comunaRepository.findById(comunaId)
				.orElseThrow(() -> new ResourceNotFoundException("Cumuna no encontrada con el ID :: " + comunaId));
		return ResponseEntity.ok().body(comuna);
	}

	@PostMapping("/comuna")
	public Comuna createComuna(@Validated @RequestBody Comuna comuna) {
		return comunaRepository.save(comuna);
	}
	
	/*
	 * @PutMapping("/comuna/{id}") public ResponseEntity<ComunaXml>
	 * updateComuna(@PathVariable(value = "id") Long comunaId,
	 * 
	 * @Valid @RequestBody ComunaXml comuna) throws ResourceNotFoundException {
	 * Comuna comunasXml = comunaRepository.findById(comunaId) .orElseThrow(() ->
	 * new ResourceNotFoundException("Cumuna no encontrada con el ID :: " +
	 * comunaId));
	 * 
	 * comunas.settMin(comunas.gettMin()); comunas.settMax(comunas.gettMax());
	 * comunas.setViento(comunas.getViento()); comunas.setDia(comunas.getDia());
	 * comunas.setDefAtmofera(comunas.getDefAtmofera());
	 * comunas.setSimboloTiempo(comunas.getSimboloTiempo());
	 * 
	 * final Comuna updateComuna = comunaRepository.save(comuna); return
	 * ResponseEntity.ok(updateComuna); }
	 */
	
	@DeleteMapping("/comuna/{id}")
	public Map<String, Boolean> deleteComuna(@PathVariable(value = "id") Long comunaId)
			throws ResourceNotFoundException {
		Comuna comuna = comunaRepository.findById(comunaId)
			.orElseThrow(() -> new ResourceNotFoundException("Cumuna no encontrada con el ID :: " + comunaId));
		
		comunaRepository.delete(comuna);
		Map<String, Boolean> response = new HashMap<>();
		response.put("borrado", Boolean.TRUE);
		
		return response;
	}
	
}
