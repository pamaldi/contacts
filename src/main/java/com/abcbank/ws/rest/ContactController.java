package com.abcbank.ws.rest;

import com.abcbank.repository.ContactRepository;
import com.abcbank.model.Contact;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.*;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ContactController {

	private final ContactRepository repository;

	public ContactController(ContactRepository repository) {
		this.repository = repository;
	}

	
	@GetMapping("/employees")
	Resources<Resource<Contact>> all() {

		List<Resource<Contact>> employees = repository.findAll().stream()
			.map(employee -> new Resource<>(employee,
				linkTo(methodOn(ContactController.class).one(employee.getId())).withSelfRel(),
				linkTo(methodOn(ContactController.class).all()).withRel("employees")))
			.collect(Collectors.toList());
		
		return new Resources<>(employees,
			linkTo(methodOn(ContactController.class).all()).withSelfRel());
	}
	
        @GetMapping("/search-employees")
	Resources<Resource<Contact>> find(
                @RequestParam(value = "offset", required = false, defaultValue = "0") Integer offset,
                @RequestParam(value = "limit", required = false, defaultValue = "100") Integer limit,
                @RequestParam(value = "firstName", required = false) String firstName,
                @RequestParam(value = "secondName", required = false) String secondName
        
        ) {

		List<Resource<Contact>> employees = repository.find(firstName,secondName).stream()
			.map(employee -> new Resource<>(employee,
				linkTo(methodOn(ContactController.class).one(employee.getId())).withSelfRel(),
				linkTo(methodOn(ContactController.class).all()).withRel("employees")))
			.collect(Collectors.toList());
		
		return new Resources<>(employees,
			linkTo(methodOn(ContactController.class).all()).withSelfRel());
	}

	@PostMapping("/employees")
	Contact newContact(@RequestBody Contact newContact) {
		return repository.save(newContact);
	}

	
	@GetMapping("/employees/{id}")
	Resource<Contact> one(@PathVariable Long id) {
		
		Contact employee = repository.findById(id)
			.orElseThrow(() -> new ContactNotFoundException(id));
		
		return new Resource<>(employee,
			linkTo(methodOn(ContactController.class).one(id)).withSelfRel(),
			linkTo(methodOn(ContactController.class).all()).withRel("employees"));
	}
	

	@PutMapping("/employees/{id}")
	Contact replaceContact(@RequestBody Contact newContact, @PathVariable Long id) {
		
		return repository.findById(id)
			.map(employee -> {
				employee.setFirstName(newContact.getFirstName());
				employee.setSecondName(newContact.getSecondName());
				return repository.save(employee);
			})
			.orElseGet(() -> {
				newContact.setId(id);
				return repository.save(newContact);
			});
	}

	@DeleteMapping("/employees/{id}")
	void deleteContact(@PathVariable Long id) {
		repository.deleteById(id);
	}
}
