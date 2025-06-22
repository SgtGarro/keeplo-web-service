package com.acme.keeplo.platform.shared.domain.model.entities;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;
/**
 * Base class for entities that require auditing fields.
 *
 * This abstract model automatically captures creation and update timestamps,
 * and assigns a unique identifier to each persisted entity.
 */
@EntityListeners(AuditingEntityListener.class)
@MappedSuperclass
public class AuditableModel {
    /**
     * Unique identifier for the entity.
     */
    @Id
    @Getter
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * Timestamp indicating when the entity was created.
     * This value is set automatically on persist.
     */
    @Getter
    @CreatedDate
    @Column(nullable = false, updatable = false)
    private Date createdAt;

    /**
     * Timestamp indicating when the entity was last updated.
     * This value is set automatically on update.
     */
    @Getter
    @LastModifiedDate
    @Column(nullable = false)
    private Date updatedAt;
}