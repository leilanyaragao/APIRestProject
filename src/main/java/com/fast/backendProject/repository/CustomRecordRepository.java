package com.fast.backendProject.repository;

import com.fast.backendProject.entity.Record;
import com.fast.backendProject.entity.Collaborator;
import com.fast.backendProject.entity.Workshop;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public interface CustomRecordRepository {

    public Set<String> getAllCollaboratorsByWorkshopDate(List<Record> records, LocalDateTime localDateTime);

    public Set<String> getAllCollaboratorsByWorkshopName(List<Record> records, String workshopName);

    public Set<String> getAllCollaboratorsPresent(
            List<Record> records);

}