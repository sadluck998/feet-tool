package org.example.feettool.service;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.example.feettool.dto.EmissionDto;
import org.example.feettool.enums.CarbonCategory;
import org.junit.jupiter.api.*;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.Optional;

@Slf4j
@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class EmissionServiceTest {
    private static String id;

    @Resource
    private EmissionService emissionService;

    @Test
    @Order(1)
    void add() {
        try {
            EmissionDto dto = new EmissionDto(
                    CarbonCategory.E.name(), "电力",
                    "kg CO₂e/kWh", new BigDecimal("0.5")
            );
            emissionService.addOrUpdate(dto);
            id = dto.getId();
            log.info("id= " + id);
        } catch (Exception e) {
            log.error("", e);
            Assertions.fail();
        }
    }

    @Test
    @Order(2)
    void fetch() {
        try {
            Optional<EmissionDto> fetch = emissionService.fetch(id);
            if (fetch.isEmpty()) {
                Assertions.fail();
            } else {
                EmissionDto dto = fetch.get();
                if (StringUtils.isBlank(dto.getName())) {
                    Assertions.fail();
                }
            }
        } catch (Exception e) {
            log.error("", e);
            Assertions.fail();
        }
    }

    @Test
    @Order(3)
    void update() {
        try {
            Optional<EmissionDto> fetch = emissionService.fetch(id);
            if (fetch.isPresent()) {
                EmissionDto dto = fetch.get();
                String updateName = "updateName";
                dto.setName(updateName);
                emissionService.addOrUpdate(dto);
                fetch = emissionService.fetch(id);
                if (!(fetch.isPresent() && updateName.equals(fetch.get().getName()))) {
                    Assertions.fail();
                }
            } else {
                Assertions.fail();
            }
        } catch (Exception e) {
            log.error("", e);
            Assertions.fail();
        }
    }

    @Test
    @Order(4)
    void delete() {
        boolean deleted = emissionService.delete(Collections.singletonList(id));
        if (!deleted) {
            Assertions.fail();
        } else {
            Optional<EmissionDto> fetch = emissionService.fetch(id);
            if (fetch.isPresent()) {
                Assertions.fail();
            }
        }
    }
}
