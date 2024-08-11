package org.example.feettool.dto;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.feettool.base.dto.EntityBase;

import java.io.Serial;
import java.math.BigDecimal;

@Data
@Entity
@Table(name = "emission")
@NoArgsConstructor
@AllArgsConstructor
//@EqualsAndHashCode(callSuper = false)
public class EmissionDto extends EntityBase {
    @Serial
    private static final long serialVersionUID = 2707755142800576929L;

    @NotBlank
    private String name;
    @NotBlank
    private String unit;
    @NotNull
    private BigDecimal factors;
}
