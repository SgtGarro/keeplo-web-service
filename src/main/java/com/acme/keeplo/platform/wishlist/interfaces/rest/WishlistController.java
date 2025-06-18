package com.acme.keeplo.platform.wishlist.interfaces.rest;

import com.acme.keeplo.platform.wishlist.domain.model.services.CollectionCommandService;
import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.CollectionResource;
import com.acme.keeplo.platform.wishlist.interfaces.rest.resources.CreateCollectionResource;
import com.acme.keeplo.platform.wishlist.interfaces.rest.transform.CollectionResourceFromEntityAssembler;
import com.acme.keeplo.platform.wishlist.interfaces.rest.transform.CreateCollectionCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/collections")
@Tag(name = "Wishlist", description = "Operaciones sobre colecciones de deseos")
public class WishlistController {

    private final CollectionCommandService collectionCommandService;

    public WishlistController(CollectionCommandService collectionCommandService) {
        this.collectionCommandService = collectionCommandService;
    }

    @PostMapping
    @Operation(summary = "Crear una nueva colección")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Colección creada exitosamente"),
            @ApiResponse(responseCode = "400", description = "Solicitud inválida")
    })
    public ResponseEntity<CollectionResource> createCollection(@RequestBody CreateCollectionResource resource) {
        var command = CreateCollectionCommandFromResourceAssembler.toCommandFromResource(resource);
        var result = collectionCommandService.handle(command);
        if (result.isEmpty()) return ResponseEntity.badRequest().build();

        var collection = result.get();
        var response = CollectionResourceFromEntityAssembler.toResourceFromEntity(collection);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
