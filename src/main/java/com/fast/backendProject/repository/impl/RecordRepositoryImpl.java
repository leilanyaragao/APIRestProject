package com.fast.backendProject.repository.impl;

import com.fast.backendProject.entity.Collaborator;
import com.fast.backendProject.entity.Record;
import com.fast.backendProject.entity.Workshop;
import com.fast.backendProject.repository.CustomRecordRepository;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class RecordRepositoryImpl implements CustomRecordRepository {

    public Set<String> getAllCollaboratorsByWorkshopDate(List<Record> records, LocalDateTime workshopSearchedRealizationDate) {
        Set<String> collaboratorsSet = new HashSet<>();

        for (Record record : records) {
            Workshop workshop = record.getWorkshop();
            LocalDateTime workshopRealizationDate = workshop.getRealizationDate();

            if (workshopRealizationDate.equals(workshopSearchedRealizationDate)) {
                for (Collaborator collaborator : record.getCollaborators()) {
                    collaboratorsSet.add(collaborator.getName());
                }
            }
        }

        return collaboratorsSet;
    }

    public Set<String> getAllCollaboratorsByWorkshopName(List<Record> records, String workshopSearchedName) {
        Set<String> collaboratorsSet = new HashSet<>();

        for (Record record : records) {
            Workshop workshop = record.getWorkshop();
            String workshopName = workshop.getName();

            if (workshopName.equals(workshopSearchedName)) {
                for (Collaborator collaborator : record.getCollaborators()) {
                    collaboratorsSet.add(collaborator.getName());
                }

                return collaboratorsSet;
            }
        }

        return new HashSet<>();
    }

    public Set<String> getAllCollaboratorsPresent(List<Record> records) {
        Set<String> collaboratorsSet = new HashSet<>();

        for (Record record : records) {
            for (Collaborator collaborator : record.getCollaborators()) {
                collaboratorsSet.add(collaborator.getName());
            }
        }

        return collaboratorsSet;
    }

}