package service;

import model.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.PersonaRepository;


import java.util.Optional;

@Service
public class PersonaService {

    @Autowired
    private PersonaRepository personaRepository;


    public Iterable<Persona> getPersonas(){
        return personaRepository.findAll();
    }

    public Persona savePersona(Persona persona){
        return personaRepository.save(persona);
    }

    public Optional<Persona> getPersona(Long id){
        return personaRepository.findById(id);
    }

    public void deletePersona(Long id){
        personaRepository.deleteById(id);
    }

    public Persona updatePersona(Long id, Persona personaDetails) {
        return personaRepository.findById(id).map(persona -> {
            persona.setNombre(personaDetails.getNombre());
            persona.setApellido(personaDetails.getApellido());
            persona.setCorreo(personaDetails.getCorreo());
            return personaRepository.save(persona);
        }).orElseThrow(() -> new RuntimeException("Persona no encontrada con id " + id));
    }

}
