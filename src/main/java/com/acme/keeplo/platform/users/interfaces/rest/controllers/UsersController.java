package com.acme.keeplo.platform.users.interfaces.rest.controllers;

import com.acme.keeplo.platform.users.domain.model.aggregates.Users;
import com.acme.keeplo.platform.users.domain.model.queries.GetAllUsersQuery;
import com.acme.keeplo.platform.users.domain.model.queries.GetAllUsersByIdQuery;
import com.acme.keeplo.platform.users.domain.model.queries.GetUserByEmailQuery;
import com.acme.keeplo.platform.users.domain.model.queries.GetUserByIdAndEmailQuery;
import com.acme.keeplo.platform.users.domain.services.UsersCommandService;
import com.acme.keeplo.platform.users.domain.services.UsersQueryService;
import com.acme.keeplo.platform.users.interfaces.rest.resources.CreateUsersResource;
import com.acme.keeplo.platform.users.interfaces.rest.resources.UsersResource;
import com.acme.keeplo.platform.users.interfaces.rest.transform.CreateUsersCommandFromResourceAssembler;

import com.acme.keeplo.platform.users.interfaces.rest.transform.UserResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.Parameters;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/users", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Users", description = "Endpoints for users")
public class UsersController {

    private final UsersCommandService usersCommandService;
    private final UsersQueryService usersQueryService;

    /**
     * Constructor for UsersController.
     * @param usersCommandService Users command service
     * @param usersQueryService Users query service
     * @since 1.0
     * @see UsersCommandService
     * @see UsersQueryService
     */
    public UsersController(UsersCommandService usersCommandService, UsersQueryService usersQueryService) {
        this.usersCommandService = usersCommandService;
        this.usersQueryService = usersQueryService;
    }

    /**
     * Creates a user.
     * @param resource CreateUsersResource
     * @return ResponseEntity with the created users resource, or bad request if the resource is invalid
     * @since 1.0
     * @see CreateUsersResource
     * @see UsersResource
     */

    @Operation(
            summary = "Create a User",
            description = "Creates a user with the provided email, password")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "User created"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @PostMapping
    //@RequestBody metod recibe un JSON en el cuerpo del request y convierte automáticamente en una instancia de CreateUsersResource
    public ResponseEntity<UsersResource> createUser(@RequestBody CreateUsersResource resource) {
        Optional<Users> user = usersCommandService
                //Ejecuta la lógica de negocio para crear el usuario
                //Convierte el JSON recibido (CreateUsersResource) en un objeto de negocio (CreateUsersCommand).
                .handle(CreateUsersCommandFromResourceAssembler.toCommandFromResource(resource));

        //Convierte el objeto del dominio (Users) en el JSON de salida (UsersResource).
        return user.map(u -> new ResponseEntity<>(UserResourceFromEntityAssembler.toResourceFromEntity(u), CREATED))
                .orElseGet(() -> ResponseEntity.badRequest().build());
    }

    /**
     * Gets a User by ID.
     * @param id user ID
     * @return ResponseEntity with the user resource if found, or not found otherwise
     * @since 1.0
     * @see UsersResource
     */
    @Operation(
            summary = "Get a user by ID",
            description = "Gets a user by the provided ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "user found"),
            @ApiResponse(responseCode = "404", description = "user not found")
    })
    @GetMapping("{id}")
    public ResponseEntity<UsersResource> getUserById(@PathVariable Long id) {
        Optional<Users> user = usersQueryService.handle(new GetAllUsersByIdQuery(id));
        return user.map(u -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(u)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private ResponseEntity<UsersResource> getUserByIdAndEmail(Long id, String email) {
        Optional<Users> user = usersQueryService.handle(new GetUserByIdAndEmailQuery(id, email));
        return user.map(u -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(u)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    private ResponseEntity<UsersResource> getUserByEmail(String email) {
        Optional<Users> user = usersQueryService.handle(new GetUserByEmailQuery(email));
        return user.map(u -> ResponseEntity.ok(UserResourceFromEntityAssembler.toResourceFromEntity(u)))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    /**
     * Gets all users
     * @return ResponseEntity with the list of users
     * @since 1.0
     * @see UsersResource
     */
    private ResponseEntity<List<UsersResource>> getAllUsers() {
        var getAllUsersQuery = new GetAllUsersQuery();
        var users = usersQueryService.handle(getAllUsersQuery);
        if (users.isEmpty()) return ResponseEntity.notFound().build();
        var usersResource = users.stream().map(UserResourceFromEntityAssembler::toResourceFromEntity).toList();
        return ResponseEntity.ok(usersResource);
    }

    @Operation(
            summary = "Get users with filters",
            description = "Get users optionally filtered by email and id"
    )
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Users found"),
            @ApiResponse(responseCode = "400", description = "Bad request")
    })
    @Parameters({
            @Parameter(name = "email", description = "Email of the user"),
            @Parameter(name = "id", description = "id of the user")
    })
    @GetMapping
    public ResponseEntity<?> getUsersWithFilters(@RequestParam Map<String, String> params) {
        boolean hasId = params.containsKey("id");
        boolean hasEmail = params.containsKey("email");

        if (hasId && hasEmail) {
            try {
                Long id = Long.parseLong(params.get("id"));
                return getUserByIdAndEmail(id, params.get("email"));
            } catch (NumberFormatException e) {
                return ResponseEntity.badRequest().body("Invalid ID format");
            }
        } else if (hasId) {
            try {
                Long id = Long.parseLong(params.get("id"));
                return getUserById(id);
            } catch (NumberFormatException e) {
                return ResponseEntity.badRequest().body("Invalid ID format");
            }
        } else if (hasEmail) {
            return getUserByEmail(params.get("email"));
        } else {
            return getAllUsers();
        }
    }

}
