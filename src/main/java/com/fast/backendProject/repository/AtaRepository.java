package com.fast.backendProject.repository;

import com.fast.backendProject.entity.Ata;
import com.fast.backendProject.entity.Colaborador;
import com.fast.backendProject.entity.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.TreeMap;

@Repository
public interface AtaRepository extends JpaRepository<Ata, Integer> {
    default TreeMap<String, Workshop> getAllColaboratorsPresentAndYourWorkshops(List<Ata> atas) {
        TreeMap<String, Workshop> colaboratorsWorkshops = new TreeMap<>();

        for (Ata ata : atas) {
            for (Colaborador colaborador : ata.getColaboradores()) {
                colaboratorsWorkshops.put(colaborador.getNome(), ata.getWorkshop());
            }
        }
        return colaboratorsWorkshops;
    }

    default List<Colaborador> getAllColaboratorsByWorkshopName(List<Ata> atas, String workshopName) {
        for (Ata ata : atas) {
            if (ata.getWorkshop().getNome().equals(workshopName)) {
                return ata.getColaboradores();
            }
        }
        return new ArrayList<>();
    }

    default List<Colaborador> getAllColaboratorsByWorkshopDate(List<Ata> atas, LocalDateTime localDateTime) {
        for (Ata ata : atas) {
            if (ata.getWorkshop().getDataRealizacao().equals(localDateTime)) {
                return ata.getColaboradores();
            }
        }
        return new ArrayList<>();
    }

}
