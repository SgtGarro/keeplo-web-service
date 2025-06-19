package com.acme.keeplo.platform.wishlist.domain.model.entities;
import jakarta.persistence.Embeddable;
import com.acme.keeplo.platform.wishlist.domain.model.aggregates.Collection;
import com.acme.keeplo.platform.shared.domain.model.entities.AuditableModel;
import com.acme.keeplo.platform.wishlist.domain.model.valueobjects.Tag;
import jakarta.persistence.*;
import lombok.Getter;

import java.util.HashSet;
import java.util.Set;

@Entity
@Getter
public class Wish extends AuditableModel {

    private String title;
    private String description;
    private String url;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "collection_id", nullable = false)
    private Collection collection;
    @ElementCollection
    @CollectionTable(name = "wish_tags", joinColumns = @JoinColumn(name = "wish_id"))
    private Set<Tag> tags = new HashSet<>();
    

    protected Wish() {
    }

    public Wish(String title, String description, String url, Collection collection) {
        this.title = title;
        this.description = description;
        this.url = url;
        this.collection = collection;
    }

    public void setCollection(Collection collection) {
        this.collection = collection;
    }

    public void setTitle(String title) { this.title = title; }
    public void setDescription(String description) { this.description = description; }
    public void setUrl(String url) { this.url = url; }

    public void addTag(Tag tag) {
        this.tags.add(tag);
    }



}