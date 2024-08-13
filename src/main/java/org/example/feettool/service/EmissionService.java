package org.example.feettool.service;

import org.example.feettool.dto.EmissionDto;

import java.util.List;
import java.util.Optional;

public interface EmissionService {
    String addOrUpdate(EmissionDto dto);

    List<EmissionDto> list();

    Optional<EmissionDto> fetch(String id);

    boolean delete(List<String> ids);

    /**
     * 保存 列表， 返回所有数据
     *
     * @param dtoList
     * @return
     */
    List<EmissionDto> addOrUpdate(List<EmissionDto> dtoList);
}
