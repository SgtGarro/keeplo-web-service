package com.acme.keeplo.platform.subscription.interfaces.rest.controllers;

import com.acme.keeplo.platform.subscription.domain.model.aggregates.Subscription;
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
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/subscriptions", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Subscription", description = "Endpoints for subscriptions")
public class SubscriptionController {

    private final SubscriptionCommandService commandService;
    private final SubscriptionQueryService queryService;

    public SubscriptionController(
            SubscriptionCommandService commandService,
            SubscriptionQueryService queryService
    ) {
        this.commandService = commandService;
        this.queryService = queryService;
    }

    /**
     * Create a new subscription
     */
    @PostMapping
    public ResponseEntity<?> createSubscription(@RequestBody CreateSubscriptionResource resource) {
        var command = new CreateSubscriptionCommand(
                resource.membershipId(),
                resource.paymentCardId(),
                resource.userId()
        );

        var result = commandService.handle(command);
        return result.map(subscription ->
                ResponseEntity.ok(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(subscription))
        ).orElse(ResponseEntity.badRequest().build());
    }

    /**
     * Get subscription by userId
     */
    @GetMapping("/{userId}")
    public ResponseEntity<?> getSubscriptionByUserId(@PathVariable Long userId) {
        var query = new GetSubscriptionByUserIdQuery(userId);
        var result = queryService.handle(query);

        return result.map(subscription ->
                ResponseEntity.ok(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(subscription))
        ).orElse(ResponseEntity.notFound().build());
    }

    /**
     * Update an existing subscription
     */
    @PutMapping("/{subscriptionId}")
    public ResponseEntity<?> updateSubscription(@PathVariable Long subscriptionId,
                                                @RequestBody UpdateSubscriptionResource resource) {
        var command = new UpdateSubscriptionCommand(
                subscriptionId,
                resource.userId(),
                resource.membershipId(),
                resource.paymentCardId()
        );

        var result = commandService.handle(command);
        return result.map(subscription ->
                ResponseEntity.ok(SubscriptionResourceFromEntityAssembler.toResourceFromEntity(subscription))
        ).orElse(ResponseEntity.badRequest().build());
    }

}