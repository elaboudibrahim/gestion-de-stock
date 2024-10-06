package com.gestion_stock.model;

import jakarta.persistence.*;
import jakarta.persistence.Id;
import lombok.Data;
import org.springframework.data.annotation.*;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.io.Serializable;
import java.time.Instant;
@Data
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass

public class AbstractEntity implements Serializable {
    @Id
    @GeneratedValue
    private Integer id;

    @CreatedDate
    @Column(name = "creationDate", nullable = false, updatable = false)
    private Instant creationDate;

    @LastModifiedDate
    @Column(name = "lastModifiedDate")
    private Instant lastModifiedDate;

}
