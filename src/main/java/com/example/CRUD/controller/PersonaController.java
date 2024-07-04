package com.example.CRUD.controller;

import com.example.CRUD.model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.CRUD.service.PersonaService;

import java.util.Optional;

@RestController
@RequestMapping("/api/personas")
public class PersonaController {

    @Autowired
    private PersonaService personaService;

    @PostMapping
    public Persona createPersona(@RequestBody Persona persona) {
        return personaService.savePersona(persona);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPersona(@PathVariable Long id) {
        Optional<Persona> persona = personaService.getPersona(id);
        if (persona.isPresent()) {
            return ResponseEntity.ok(persona.get());
        } else {
            String errorMessage = "La persona con el ID " + id + " no existe.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @GetMapping
    public Iterable<Persona> getPersonas() {
        return personaService.getPersonas();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deletePersona(@PathVariable Long id) {
        Optional<Persona> persona = personaService.getPersona(id);
        if (persona.isPresent()) {
            personaService.deletePersona(id);
            return ResponseEntity.noContent().build();
        } else {
            String errorMessage = "La persona con el ID " + id + " no existe.";
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(errorMessage);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<Persona> updatePersona(@PathVariable Long id, @RequestBody Persona personaDetails) {
        try {
            Persona updatedPersona = personaService.updatePersona(id, personaDetails);
            return ResponseEntity.ok(updatedPersona);
        } catch (RuntimeException e) {
            return ResponseEntity.notFound().build();
        }
    }
}
