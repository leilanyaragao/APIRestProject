package com.fast.backendProject.controller;

import com.fast.backendProject.entity.Ata;
import com.fast.backendProject.entity.Colaborador;
import com.fast.backendProject.entity.Workshop;
import com.fast.backendProject.repository.AtaRepository;
import com.fast.backendProject.repository.ColaboradorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.TreeMap;

@RequestMapping("/api/atas")
@RestController
public class AtaController {
    private final ColaboradorRepository _colaboradorRepository;
    private final AtaRepository _ataRepository;

    public AtaController(ColaboradorRepository colaboradorRepository, AtaRepository ataRepository) {
        _colaboradorRepository = colaboradorRepository;
        _ataRepository = ataRepository;
    }

    @GetMapping
    public TreeMap<String, Workshop> Get() {
        return _ataRepository.getAllColaboratorsPresentAndYourWorkshops(_ataRepository.findAll());
    }

    @GetMapping("/{workshopName}")
    public List<Colaborador> GetColaboratorByWorkshopName(@PathVariable(value = "workshopName") String workshopName) {
        return _ataRepository.getAllColaboratorsByWorkshopName(_ataRepository.findAll(), workshopName);
    }

    @GetMapping("/{workshopDate}")
    public List<Colaborador> GetColaboratorByWorkshopDate(@PathVariable(value = "workshopDate") LocalDateTime workshopDate) {
        return _ataRepository.getAllColaboratorsByWorkshopDate(_ataRepository.findAll(), workshopDate);
    }

    @DeleteMapping("/{ataId}/colaboradores/{colaboradorId}")
    public ResponseEntity<Object> DeleteColaboradorFromAta(@PathVariable(value = "ataId") Integer ataId, @PathVariable(value = "colaboradorId") Integer colaboradorId) {
        Optional<Ata> ataOptional = _ataRepository.findById(ataId);
        Optional<Colaborador> colaboradorOptional = _colaboradorRepository.findById(colaboradorId);
        if (ataOptional.isPresent() && colaboradorOptional.isPresent()) {
            Ata ata = ataOptional.get();
            List<Colaborador> colaboradoresList = ata.getColaboradores();
            colaboradoresList.remove(colaboradorOptional);
            _ataRepository.save(ata);
            return new ResponseEntity<>(HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public Ata Post(@RequestBody Ata ata) {
        return _ataRepository.save(ata);
    }

    @PutMapping("/{ataId}/colaboradores/{colaboradorId}")
    public ResponseEntity<Ata> PutColaboradorInAta(@PathVariable(value = "ataId") Integer ataId, @PathVariable(value = "colaboradorId") Integer colaboradorId) {
        Optional<Ata> ataOptional = _ataRepository.findById(ataId);
        Optional<Colaborador> colaboradorOptional = _colaboradorRepository.findById(colaboradorId);
        if (ataOptional.isPresent() && colaboradorOptional.isPresent()) {
            Ata ata = ataOptional.get();
            ata.setColaboradores(colaboradorOptional.stream().toList());
            _ataRepository.save(ata);
            return new ResponseEntity<Ata>(ata, HttpStatus.OK);
        } else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
