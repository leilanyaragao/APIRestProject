package com.fast.backendProject.repository;

import com.fast.backendProject.entity.Workshop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface WorkshopRepository extends JpaRepository<Workshop, Integer> {

}
