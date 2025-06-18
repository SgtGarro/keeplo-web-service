package com.acme.keeplo.platform.wishlist.domain.model.aggregates;

import com.acme.keeplo.platform.shared.domain.model.aggregates.AuditableAbstractAggregateRoot;
import com.acme.keeplo.platform.wishlist.domain.model.valueobjects.CollectionId;
//import com.acme.keeplo.platform.wishlist.domain.model.events.CollectionCreatedEvent;
import jakarta.persistence.*;
import lombok.Getter;

@Entity
public class Collection extends AuditableAbstractAggregateRoot<Collection> {

    @EmbeddedId
    private CollectionId id;

    @Getter
    private String name;

    @Getter
    private String description;

    @Getter
    private boolean isPublic;

    public Collection() {
        this.id = new CollectionId(); // genera UUID
    }

    public Collection(String name, String description, boolean isPublic) {
        this();
        this.name = name;
        this.description = description;
        this.isPublic = isPublic;
        //this.addDomainEvent(new CollectionCreatedEvent(this.id));
    }

    public void updateDetails(String name, String description, boolean isPublic) {
        this.name = name;
        this.description = description;
        this.isPublic = isPublic;
    }
}
