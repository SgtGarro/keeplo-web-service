package com.acme.keeplo.platform.wishlist.interfaces.rest.resources;

public record AddWishResource(        String title,
                                      String description,
                                      String url, Long collectionId){}
