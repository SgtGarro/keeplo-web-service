package com.acme.keeplo.platform.wishlist.domain.model.commands;

public record AddTagToCollectionCommand(Long collectionId, String name, String color) {
}
