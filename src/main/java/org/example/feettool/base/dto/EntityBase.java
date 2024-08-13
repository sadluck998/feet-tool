package org.example.feettool.base.dto;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import lombok.Data;

import java.io.Serial;
import java.io.Serializable;
import java.util.Date;

@Data
@MappedSuperclass
//@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class EntityBase implements Serializable {
    @Serial
    private static final long serialVersionUID = 8844662265450707202L;

    @Id
    @Column(length = 32)
    private String id;

    @Column(name = "create_by", length = 50)
    private String createBy;

    @Column(name = "create_time")
    private Date createTime;

    @Column(name = "update_by", length = 50)
    private String updateBy;

    @Column(name = "update_time")
    private Date updateTime;
}
