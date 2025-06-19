package com.acme.keeplo.platform.wishlist.interfaces.rest;

import com.acme.keeplo.platform.wishlist.application.internal.queryservices.WishQueryService;
import com.acme.keeplo.platform.wishlist.domain.model.valueobjects.Tag;
import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.AddTagResource;
import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.UpdateWishResource;
import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.WishResource;
import com.acme.keeplo.platform.wishlist.interfaces.rest.transform.WishResourceFromEntityAssembler;
import com.acme.keeplo.platform.wishlist.domain.model.entities.Wish;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/wishes")
public class WishController {

    private final WishQueryService wishQueryService;

    public WishController(WishQueryService wishQueryService) {
        this.wishQueryService = wishQueryService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Obtener un deseo por ID")
    public ResponseEntity<WishResource> getById(@PathVariable Long id) {
        return wishQueryService.getById(id)
                .map(WishResourceFromEntityAssembler::toResourceFromEntity)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/collection/{collectionId}")
    @Operation(summary = "Listar deseos por colección")
    public ResponseEntity<List<WishResource>> getByCollection(@PathVariable Long collectionId) {
        var wishes = wishQueryService.getByCollectionId(collectionId);
        var resources = wishes.stream()
                .map(WishResourceFromEntityAssembler::toResourceFromEntity)
                .toList();
        return ResponseEntity.ok(resources);
    }

    @PutMapping("/{id}")
    @Operation(summary = "Editar un deseo")
    public ResponseEntity<WishResource> updateWish(@PathVariable Long id, @RequestBody UpdateWishResource resource) {
        var wishOptional = wishQueryService.getById(id);

        if (wishOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var wish = wishOptional.get();
        // Actualiza los campos manualmente
        wish.setTitle(resource.title());
        wish.setDescription(resource.description());
        wish.setUrl(resource.url());

        var updatedWish = wishQueryService.save(wish); // lo agregamos en el servicio
        var response = WishResourceFromEntityAssembler.toResourceFromEntity(updatedWish);
        return ResponseEntity.ok(response);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Eliminar un deseo por ID")
    public ResponseEntity<Void> deleteWish(@PathVariable Long id) {
        var wishOptional = wishQueryService.getById(id);
        if (wishOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        wishQueryService.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{id}/tags")
    @Operation(summary = "Agregar una etiqueta a un deseo")
    public ResponseEntity<WishResource> addTagToWish(@PathVariable Long id, @RequestBody AddTagResource resource) {
        var wishOptional = wishQueryService.getById(id);
        if (wishOptional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        var wish = wishOptional.get();
        var tag = new Tag(resource.name(), resource.color());
        wish.addTag(tag);

        wishQueryService.save(wish);

        var response = WishResourceFromEntityAssembler.toResourceFromEntity(wish);
        return ResponseEntity.ok(response);
    }
}

