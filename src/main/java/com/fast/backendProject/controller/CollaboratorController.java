package com.fast.backendProject.controller;

import com.fast.backendProject.entity.Collaborator;
import com.fast.backendProject.repository.CollaboratorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RequestMapping("/api/collaborators")
@RestController
public class CollaboratorController {
    private final CollaboratorRepository _collaboratorRepository;

    public CollaboratorController(CollaboratorRepository collaboratorRepository) {
        _collaboratorRepository = collaboratorRepository;
    }

    @GetMapping
    public List<Collaborator> getAllCollaborators() {
        return _collaboratorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Collaborator> getCollaboratorById(@PathVariable(value = "id") Integer id) {
        Optional<Collaborator> collaborator = _collaboratorRepository.findById(id);

        if (collaborator.isPresent()) {
            return new ResponseEntity<Collaborator>(collaborator.get(), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteCollaborator(@PathVariable(value = "id") Integer id) {
        Optional<Collaborator> collaborator = _collaboratorRepository.findById(id);

        if (collaborator.isPresent()) {
            _collaboratorRepository.delete(collaborator.get());

            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public Collaborator saveCollaborator(@Valid @RequestBody Collaborator collaborator) {
        return _collaboratorRepository.save(collaborator);
    }

}