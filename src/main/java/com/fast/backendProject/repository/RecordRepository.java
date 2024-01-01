package com.fast.backendProject.repository;

import com.fast.backendProject.entity.Record;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RecordRepository extends CustomRecordRepository, JpaRepository<Record, Integer> {
}