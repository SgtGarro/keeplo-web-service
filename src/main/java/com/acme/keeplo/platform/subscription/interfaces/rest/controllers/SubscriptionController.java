package com.acme.keeplo.platform.subscription.interfaces.rest.controllers;

import com.acme.keeplo.platform.subscription.domain.model.commands.subscriptions.CreateSubscriptionCommand;
import com.acme.keeplo.platform.subscription.domain.model.commands.subscriptions.UpdateSubscriptionCommand;
import com.acme.keeplo.platform.subscription.domain.model.queries.GetSubscriptionByUserIdQuery;
import com.acme.keeplo.platform.subscription.domain.services.SubscriptionCommandService;
import com.acme.keeplo.platform.subscription.domain.services.SubscriptionQueryService;
import com.acme.keeplo.platform.subscription.interfaces.rest.resources.CreateSubscriptionResource;
import com.acme.keeplo.platform.subscription.interfaces.rest.resources.SubscriptionResource;
import com.acme.keeplo.platform.subscription.interfaces.rest.resources.UpdateSubscriptionResource;
import com.acme.keeplo.platform.subscription.interfaces.rest.transform.CreateSubscriptionCommandFromResourceAssembler;
import com.acme.keeplo.platform.subscription.interfaces.rest.transform.SubscriptionResourceFromEntityAssembler;
import com.acme.keeplo.platform.subscription.interfaces.rest.transform.UpdateSubscriptionCommandFromResourceAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * REST controller for managing Subscriptions.
 * Provides endpoints to create, retrieve and update user subscriptions.
 */
@RestController
@RequestMapping(value = "/api/v1/subscriptions", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Subscription", description = "Endpoints for subscriptions")
public class SubscriptionController {

    private final SubscriptionCommandService commandService;
    private final SubscriptionQueryService queryService;

    /**
     * Constructs the SubscriptionController with required services.
     *
     * @param commandService service for handling subscription commands
     * @param queryService   service for handling subscription queries
     */
    public SubscriptionController(
            SubscriptionCommandService commandService,
            SubscriptionQueryService queryService
    ) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    /**
     * Creates a new subscription for a user.
     *
     * @param resource the resource containing subscription data
     * @return ResponseEntity with the created subscription or bad request
     */
    @PostMapping
    @Operation(summary = "Create a new subscription", description = "Creates a new subscription for a user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Subscription created successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request (e.g., user/membership/card not found, user already has subscription).")
    })
    public ResponseEntity<SubscriptionResource> createSubscription(@RequestBody CreateSubscriptionResource resource) {
        var command = CreateSubscriptionCommandFromResourceAssembler.toCommandFromResource(resource);

        var result = commandService.handle(command);
        return result.map(subscription ->
                new ResponseEntity<>(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(subscription), CREATED)
        ).orElse(ResponseEntity.badRequest().build());
    }

    /**
     * Retrieves the subscription details for a specific user by their ID.
     *
     * @param userId the ID of the user
     * @return ResponseEntity with the subscription or not found
     */
    @GetMapping("/user/{userId}")
    @Operation(summary = "Get subscription by user ID", description = "Retrieves the subscription details for a specific user.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subscription found and returned."),
            @ApiResponse(responseCode = "404", description = "Subscription not found for the given user ID.")
    })
    public ResponseEntity<SubscriptionResource> getSubscriptionByUserId(@PathVariable Long userId) {
        var query = new GetSubscriptionByUserIdQuery(userId);
        var result = queryService.handle(query);

        return result.map(subscription ->
                ResponseEntity.ok(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(subscription))
        ).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Updates an existing subscription with new membership or payment card data.
     *
     * @param subscriptionId the ID of the subscription to update
     * @param resource       the resource containing updated subscription data
     * @return ResponseEntity with the updated subscription or bad request
     */
    @PutMapping("/{subscriptionId}")
    @Operation(summary = "Update an existing subscription", description = "Updates the membership or payment card of an existing subscription.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Subscription updated successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request (e.g., subscription/membership/card not found).")
    })
    public ResponseEntity<SubscriptionResource> updateSubscription(@PathVariable Long subscriptionId,
                                                                   @RequestBody UpdateSubscriptionResource resource) {
        var command = UpdateSubscriptionCommandFromResourceAssembler.toCommandFromResource(subscriptionId,resource);

        var result = commandService.handle(command);
        return result.map(subscription ->
                ResponseEntity.ok(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(subscription))
        ).orElse(ResponseEntity.badRequest().build());
    }
}