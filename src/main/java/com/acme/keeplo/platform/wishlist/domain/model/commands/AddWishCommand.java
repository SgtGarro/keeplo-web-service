package com.acme.keeplo.platform.wishlist.domain.model.commands;

public record AddWishCommand(Long collectionId, String title, String description, String url) {}

