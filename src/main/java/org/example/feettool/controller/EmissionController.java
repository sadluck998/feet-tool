package org.example.feettool.controller;

import jakarta.annotation.Resource;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.extern.slf4j.Slf4j;
import org.example.feettool.dto.EmissionDto;
import org.example.feettool.enums.CarbonCategory;
import org.example.feettool.info.Result;
import org.example.feettool.info.ValidList;
import org.example.feettool.service.EmissionService;
import org.example.feettool.util.EnumUtil;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

@Slf4j
@Validated
@RestController
@RequestMapping("/emission")
public class EmissionController {
    @Resource
    private EmissionService emissionService;

    @PostMapping
    public Result<String> addOrUpdate(@RequestBody @Valid EmissionDto dto) {
        return Result.success(emissionService.addOrUpdate(dto));
    }

    @PostMapping("/list")
    public Result<List<EmissionDto>> addOrUpdate(@RequestBody @Valid @NotNull @Size(min = 1, max = 100) List<EmissionDto> dtoList) {
        return Result.success(emissionService.addOrUpdate(dtoList));
    }

    @GetMapping("/list")
    public Result<List<EmissionDto>> list() {
        return Result.success(emissionService.list());
    }

    @GetMapping
    public Result<EmissionDto> fetch(@RequestParam("id") String id) {
        Optional<EmissionDto> optional = emissionService.fetch(id);
        return optional.map(Result::success).orElseGet(() -> Result.fail(0));
    }

    @DeleteMapping
    public Result<Boolean> delete(@RequestBody @Valid ValidList<String> ids) {
        return Result.success(emissionService.delete(ids.getList()));
    }

    @GetMapping("/categoryList")
    public Result<List<Map<String, String>>> carbonCategoryList() {
        return Result.success(EnumUtil.list(CarbonCategory.values()));
    }
}
