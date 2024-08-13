package org.example.feettool.service.impl;

import jakarta.annotation.Resource;
import org.apache.commons.lang3.StringUtils;
import org.example.feettool.dto.EmissionDto;
import org.example.feettool.repository.EmissionRepository;
import org.example.feettool.service.EmissionService;
import org.example.feettool.util.TimingUuid;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class EmissionServiceImpl implements EmissionService {
    @Resource
    private EmissionRepository repository;

    @Override
    public String addOrUpdate(EmissionDto dto) {
        if (StringUtils.isBlank(dto.getId())) {
            dto.setId(TimingUuid.uuid());
            dto.setCreateTime(new Date());
        } else {
            dto.setUpdateTime(new Date());
        }

        repository.save(dto);

        return dto.getId();
    }

    @Override
    public List<EmissionDto> list() {
        return repository.findAll();
    }

    @Override
    public Optional<EmissionDto> fetch(String id) {
        return repository.findById(id);
    }

    @Override
    public boolean delete(List<String> ids) {
        repository.deleteAllByIdInBatch(ids);
        return true;
    }

    /**
     * 保存 列表， 返回所有数据
     *
     * @param dtoList
     * @return
     */
    @Override
    public List<EmissionDto> addOrUpdate(List<EmissionDto> dtoList) {
        dtoList.forEach(o -> {
            if (StringUtils.isBlank(o.getId())) {
                o.setId(TimingUuid.uuid());
            }
        });
        repository.saveAll(dtoList);
        return repository.findAll();
    }
}
