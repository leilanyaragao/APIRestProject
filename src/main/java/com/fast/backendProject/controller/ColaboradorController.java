package com.fast.backendProject.controller;

import com.fast.backendProject.entity.Colaborador;
import com.fast.backendProject.repository.ColaboradorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RequestMapping("/api/colaboradores")
@RestController
public class ColaboradorController {
    private final ColaboradorRepository _colaboradorRepository;

    public ColaboradorController(ColaboradorRepository colaboradorRepository) {
        _colaboradorRepository = colaboradorRepository;
    }

    @GetMapping
    public List<Colaborador> Get() {
        return _colaboradorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Colaborador> GetById(@PathVariable(value = "id") Integer id) {
        Optional<Colaborador> colaborador = _colaboradorRepository.findById(id);
        if (colaborador.isPresent())
            return new ResponseEntity<Colaborador>(colaborador.get(), HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Object> Delete(@PathVariable(value = "id") Integer id) {
        Optional<Colaborador> colaborador = _colaboradorRepository.findById(id);
        if (colaborador.isPresent()) {
            _colaboradorRepository.delete(colaborador.get());
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public Colaborador Post(@RequestBody Colaborador colaborador) {
        return _colaboradorRepository.save(colaborador);
    }

}
