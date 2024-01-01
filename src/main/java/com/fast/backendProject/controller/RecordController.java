package com.fast.backendProject.controller;

import com.fast.backendProject.entity.Collaborator;
import com.fast.backendProject.entity.Record;
import com.fast.backendProject.entity.Workshop;
import com.fast.backendProject.repository.CollaboratorRepository;
import com.fast.backendProject.repository.RecordRepository;
import com.fast.backendProject.repository.WorkshopRepository;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@AllArgsConstructor
@RequestMapping("/api/records")
@RestController
public class RecordController {
    private final CollaboratorRepository _collaboratorRepository;
    private final RecordRepository _recordRepository;
    private final WorkshopRepository _workshopRepository;

    @GetMapping
    public Set<String> getRecords(@RequestParam(required = false) LocalDateTime workshopDate, @RequestParam(required = false) String workshopName) {

        if (workshopDate != null) {
            return _recordRepository.getAllCollaboratorsByWorkshopDate(_recordRepository.findAll(), workshopDate);
        } else if (workshopName != null) {
            return _recordRepository.getAllCollaboratorsByWorkshopName(_recordRepository.findAll(), workshopName);
        }

        return _recordRepository.getAllCollaboratorsPresent(_recordRepository.findAll());
    }

    @DeleteMapping("/{recordId}/collaborators/{collaboratorId}")
    public ResponseEntity<Object> deleteCollaboratorFromRecord(@PathVariable(value = "recordId") Integer recordId, @PathVariable(value = "collaboratorId") Integer collaboratorId) {
        Optional<Collaborator> collaboratorOptional = _collaboratorRepository.findById(collaboratorId);
        Optional<Record> recordOptional = _recordRepository.findById(recordId);

        if (recordOptional.isPresent() && collaboratorOptional.isPresent()) {
            Record record = recordOptional.get();

            List<Collaborator> collaboratorsList = record.getCollaborators();

            collaboratorsList.remove(collaboratorOptional.get());

            _recordRepository.save(record);

            return new ResponseEntity<>(HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    public ResponseEntity<Record> saveRecord(@RequestParam(required = true) Integer workshopId) {
        Optional<Workshop> workshopOptional = _workshopRepository.findById(
                workshopId);

        if (workshopOptional.isPresent()) {
            Record record = new Record(workshopOptional.get(), new ArrayList<>());

            return new ResponseEntity<Record>(_recordRepository.save(record), HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @PutMapping("/{recordId}/collaborators/{collaboratorId}")
    public ResponseEntity<Record> addCollaboratorToRecord(@PathVariable(value = "recordId") Integer recordId, @PathVariable(value = "collaboratorId") Integer collaboratorId) {
        Optional<Collaborator> collaboratorOptional = _collaboratorRepository.findById(collaboratorId);
        Optional<Record> recordOptional = _recordRepository.findById(recordId);

        if (recordOptional.isPresent() && collaboratorOptional.isPresent()) {
            Record record = recordOptional.get();

            List<Collaborator> collaboratorsList = record.getCollaborators();

            collaboratorsList.add(collaboratorOptional.get());

            _recordRepository.save(record);

            return new ResponseEntity<Record>(record, HttpStatus.OK);
        }

        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

}
