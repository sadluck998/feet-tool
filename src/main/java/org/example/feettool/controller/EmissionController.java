package org.example.feettool.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import org.apache.commons.lang3.StringUtils;
import org.example.feettool.dto.EmissionDto;
import org.example.feettool.info.Result;
import org.example.feettool.repository.EmissionRepository;
import org.example.feettool.util.TimingUuid;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Validated
@RestController
@RequestMapping("/emission")
public class EmissionController {
    @Resource
    private EmissionRepository repository;

    @PostMapping
    public Result<String> addOrUpdate(@Valid EmissionDto dto) {
        if (StringUtils.isBlank(dto.getId())) {
            dto.setId(TimingUuid.uuid());
            dto.setCreateTime(new Date());
        } else {
            dto.setUpdateTime(new Date());
        }

        repository.save(dto);

        return Result.success(dto.getId());
    }

    @GetMapping("/list")
    public Result<List<EmissionDto>> list() {
        return Result.success(repository.findAll());
    }

    @GetMapping
    public Result<EmissionDto> fetch(@RequestParam("id") String id) {
        Optional<EmissionDto> optional = repository.findById(id);
        return optional.map(Result::success).orElseGet(() -> Result.fail(0));
    }

    @DeleteMapping
    public Result<Boolean> delete(@NotBlank List<String> ids) {
        repository.deleteAllByIdInBatch(ids);
        return Result.success(true);
    }
}
