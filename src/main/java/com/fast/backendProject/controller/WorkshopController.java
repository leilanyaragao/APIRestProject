package com.fast.backendProject.controller;

import com.fast.backendProject.entity.Workshop;
import com.fast.backendProject.repository.WorkshopRepository;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RequestMapping("/api/workshops")
@RestController
public class WorkshopController {

    private final WorkshopRepository _workshopRepository;

    public WorkshopController(WorkshopRepository workshopRepository) {
        _workshopRepository = workshopRepository;
    }

    @GetMapping
    public List<Workshop> getAllWorkshops() {
        return _workshopRepository.findAll();
    }

    @PostMapping
    public Workshop saveWorkshop(@Valid @RequestBody Workshop workshop) {
        return _workshopRepository.save(workshop);
    }
}
