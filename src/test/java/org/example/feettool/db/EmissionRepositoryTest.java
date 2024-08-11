package org.example.feettool.db;

import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.example.feettool.dto.EmissionDto;
import org.example.feettool.repository.EmissionRepository;
import org.example.feettool.util.JacksonUtil;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

@Slf4j
@SpringBootTest
class EmissionRepositoryTest {
    @Resource
    private EmissionRepository repository;

    @Test
    void findOne() {
        try {
            Pageable page = Pageable.ofSize(1);
            Page<EmissionDto> dtoPage = repository.findAll(page);
            log.info(JacksonUtil.getString(dtoPage));
        } catch (Exception e) {
            log.error("", e);
            Assertions.fail();
        }
    }
}
