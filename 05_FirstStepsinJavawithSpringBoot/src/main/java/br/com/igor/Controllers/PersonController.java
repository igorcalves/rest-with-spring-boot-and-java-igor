package br.com.igor.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.PagedModel;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import br.com.igor.config.util.MediaType;
import br.com.igor.data.vo.v1.PersonVO;
import br.com.igor.services.PersonServices;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.ArraySchema;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;

//@CrossOrigin
@RestController
@RequestMapping("api/person/v1")
@Tag(name = "People", description = "Endpoints for Managing People ")
public class PersonController {

	@Autowired
	PersonServices service;
	
	@CrossOrigin(origins = {"http://localhost:8080", "https://igor.com.br"})
	@PostMapping(produces = {
			MediaType.APPLICATION_JSON, 
			MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML}, 
				consumes ={ 
						MediaType.APPLICATION_JSON, 
						MediaType.APPLICATION_XML,
						MediaType.APPLICATION_YML})
	@Operation(summary = "Adds a new  People in JSON, XML or YML", description = "Adds a new  People in JSON, XML or YML",
	tags = {"People"},
	responses = {
			@ApiResponse(description = "Success", responseCode = "200",
					content = @Content(schema = @Schema(implementation = PersonVO.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400",content = {@Content}),
			@ApiResponse(description = "Unauthorized", responseCode = "401",content = {@Content}),
			@ApiResponse(description = "Internal Error", responseCode = "500",content = {@Content})
	})
	public PersonVO create(@RequestBody PersonVO person) {
		return service.create(person);
	}
	
	
	@CrossOrigin(origins = "http://localhost:8080")
	@GetMapping(value = "/{id}", produces = {
			MediaType.APPLICATION_JSON, 
			MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML})
	@Operation(summary = "Finds a People", description = "Finds a Poeple",
	tags = {"People"},
	responses = {
			@ApiResponse(description = "Success", responseCode = "200",
					content = @Content(schema = @Schema(implementation = PersonVO.class))),
			@ApiResponse(description = "No Content", responseCode = "204",content = {@Content}),
			@ApiResponse(description = "Bad Request", responseCode = "400",content = {@Content}),
			@ApiResponse(description = "Unauthorized", responseCode = "401",content = {@Content}),
			@ApiResponse(description = "Not Found", responseCode = "404",content = {@Content}),
			@ApiResponse(description = "Internal Error", responseCode = "500",content = {@Content})
	})
	public PersonVO findById(@PathVariable(value = "id") Long id) {
		return service.findById(id);
	}

	@GetMapping(produces = {
			MediaType.APPLICATION_JSON, 
			MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML})
	@Operation(summary = "Finds all People", description = "Finds All Poeple",
	tags = {"People"},
	responses = {
			@ApiResponse(description = "Success", responseCode = "200",
					content = {
					 @Content(
							 mediaType = "application/json",
							 array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
							 )
				}),
			@ApiResponse(description = "No Content", responseCode = "204",content = {@Content}),
			@ApiResponse(description = "Bad Request", responseCode = "400",content = {@Content}),
			@ApiResponse(description = "Unauthorized", responseCode = "401",content = {@Content}),
			@ApiResponse(description = "Not Found", responseCode = "404",content = {@Content}),
			@ApiResponse(description = "Internal Error", responseCode = "500",content = {@Content})
	})
	public ResponseEntity<PagedModel<EntityModel<PersonVO>>> findALL
	(@RequestParam(value = "page", defaultValue = "0") Integer page,
	@RequestParam(value = "size", defaultValue = "12") Integer size,
	@RequestParam(value = "direction", defaultValue = "asc") String direction
			){
		var sortdirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC; 
		
		Pageable pageable = PageRequest.of(page, size,Sort.by(sortdirection,"firstName"));
		return ResponseEntity.ok(service.findAll(pageable));
	}
	@GetMapping(value = "/findPersonByName/{firstName}",
			produces = {
			MediaType.APPLICATION_JSON, 
			MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML})
	@Operation(summary = "Finds People by name ", description = "Finds People by name",
	tags = {"People"},
	responses = {
			@ApiResponse(description = "Success", responseCode = "200",
					content = {
							@Content(
									mediaType = "application/json",
									array = @ArraySchema(schema = @Schema(implementation = PersonVO.class))
									)
			}),
			@ApiResponse(description = "No Content", responseCode = "204",content = {@Content}),
			@ApiResponse(description = "Bad Request", responseCode = "400",content = {@Content}),
			@ApiResponse(description = "Unauthorized", responseCode = "401",content = {@Content}),
			@ApiResponse(description = "Not Found", responseCode = "404",content = {@Content}),
			@ApiResponse(description = "Internal Error", responseCode = "500",content = {@Content})
	})
	public ResponseEntity<PagedModel<EntityModel<PersonVO>>> findPersonByName
	(@RequestParam(value = "page", defaultValue = "0") Integer page,
			@PathVariable(value = "firstName") String firstName,
			@RequestParam(value = "size", defaultValue = "12") Integer size,
			@RequestParam(value = "direction", defaultValue = "asc") String direction
			){
		var sortdirection = "desc".equalsIgnoreCase(direction) ? Direction.DESC : Direction.ASC; 
		
		Pageable pageable = PageRequest.of(page, size,Sort.by(sortdirection,"firstName"));
		return ResponseEntity.ok(service.findPersonByName(firstName, pageable));
	}

	@PutMapping(produces = {
			MediaType.APPLICATION_JSON, 
			MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML}, 
				consumes ={ 
						MediaType.APPLICATION_JSON, 
						MediaType.APPLICATION_XML,
						MediaType.APPLICATION_YML})
	@Operation(summary = "Update  a People by parsing in a JSON ,XML or YML", description = "Update  a People by parsing in a JSON ,XML or YML",
	tags = {"People"},
	responses = {
			@ApiResponse(description = "Success", responseCode = "200",
					content = @Content(schema = @Schema(implementation = PersonVO.class))),
			@ApiResponse(description = "Bad Request", responseCode = "400",content = {@Content}),
			@ApiResponse(description = "Unauthorized", responseCode = "401",content = {@Content}),
			@ApiResponse(description = "Not Found", responseCode = "404",content = {@Content}),
			@ApiResponse(description = "Internal Error", responseCode = "500",content = {@Content})
	})
	public PersonVO update(@RequestBody PersonVO person) {
		return service.update(person);
	}
	
	@PatchMapping(value = "/{id}", produces = {
			MediaType.APPLICATION_JSON, 
			MediaType.APPLICATION_XML,
			MediaType.APPLICATION_YML})
	@Operation(summary = "Disable a specific Person by your ID", description = "Disable a specific Person by your ID",
	tags = {"People"},
	responses = {
			@ApiResponse(description = "Success", responseCode = "200",
					content = @Content(schema = @Schema(implementation = PersonVO.class))),
			@ApiResponse(description = "No Content", responseCode = "204",content = {@Content}),
			@ApiResponse(description = "Bad Request", responseCode = "400",content = {@Content}),
			@ApiResponse(description = "Unauthorized", responseCode = "401",content = {@Content}),
			@ApiResponse(description = "Not Found", responseCode = "404",content = {@Content}),
			@ApiResponse(description = "Internal Error", responseCode = "500",content = {@Content})
	})
	public PersonVO disablePerson(@PathVariable(value = "id") Long id) {
		return service.disablePerson(id);
	}

	@DeleteMapping(value = "/{id}")
	@Operation(summary = "delete a People by id", description = "delete a Poeple by id",
	tags = {"People"},
	responses = {
			@ApiResponse(description = "No Content", responseCode = "204",content = {@Content}),
			@ApiResponse(description = "Bad Request", responseCode = "400",content = {@Content}),
			@ApiResponse(description = "Unauthorized", responseCode = "401",content = {@Content}),
			@ApiResponse(description = "Not Found", responseCode = "404",content = {@Content}),
			@ApiResponse(description = "Internal Error", responseCode = "500",content = {@Content})
	})
	public ResponseEntity<?> delete(@PathVariable(value = "id") Long id) {
		service.delete(id);
		return ResponseEntity.noContent().build();
	}
}
//	@PostMapping(value = "/v2",produces = {
//			MediaType.APPLICATION_JSON, 
//			MediaType.APPLICATION_XML,
//			MediaType.APPLICATION_YML}, 
//				consumes ={ 
//						MediaType.APPLICATION_JSON, 
//						MediaType.APPLICATION_XML
//						,MediaType.APPLICATION_YML})
//	@Operation(summary = "Finds a People", description = "Finds a Poeple",
//	tags = {"People"},
//	responses = {
//			@ApiResponse(description = "Success", responseCode = "200",
//					content = @Content(schema = @Schema(implementation = PersonVO.class))),
//			@ApiResponse(description = "No Content", responseCode = "204",content = {@Content}),
//			@ApiResponse(description = "Bad Request", responseCode = "400",content = {@Content}),
//			@ApiResponse(description = "Unauthorized", responseCode = "401",content = {@Content}),
//			@ApiResponse(description = "Not Found", responseCode = "404",content = {@Content}),
//			@ApiResponse(description = "Internal Error", responseCode = "500",content = {@Content})
//	})
//	public PersonVOV2 createV2(@RequestBody PersonVOV2 person) {
//		return service.createV2(person);
//	}