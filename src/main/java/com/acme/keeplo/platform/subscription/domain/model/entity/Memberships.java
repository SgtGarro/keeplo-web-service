package com.acme.keeplo.platform.subscription.domain.model.entity;

import com.acme.keeplo.platform.shared.domain.model.entities.AuditableModel;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Getter;

@Getter
@Entity
public class Memberships extends AuditableModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String Name;
    private float Price;
    private String Description;

    public boolean isFree() {
        return Price == 0;
    }
}
