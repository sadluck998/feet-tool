package org.example.feettool.service;

import org.example.feettool.dto.EmissionDto;

import java.util.List;
import java.util.Optional;

public interface EmissionService {
    String addOrUpdate(EmissionDto dto);

    List<EmissionDto> list();

    Optional<EmissionDto> fetch(String id);

    boolean delete(List<String> ids);
}
