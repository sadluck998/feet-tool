package org.example.feettool.repository;

import org.example.feettool.dto.EmissionDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface EmissionRepository extends JpaRepository<EmissionDto, String>, JpaSpecificationExecutor<EmissionDto> {
}
