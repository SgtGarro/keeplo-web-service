package com.acme.keeplo.platform.subscription.interfaces.rest.controllers;

import com.acme.keeplo.platform.subscription.domain.model.commands.paymentCards.CreatePaymentCardCommand;
import com.acme.keeplo.platform.subscription.domain.model.commands.paymentCards.UpdatePaymentCardCommand;
import com.acme.keeplo.platform.subscription.domain.model.commands.subscriptions.CreateSubscriptionCommand;
import com.acme.keeplo.platform.subscription.domain.services.PaymentCardCommandService;
import com.acme.keeplo.platform.subscription.interfaces.rest.resources.CreatePaymentCardResource;
import com.acme.keeplo.platform.subscription.interfaces.rest.resources.PaymentCardResource;
import com.acme.keeplo.platform.subscription.interfaces.rest.resources.UpdatePaymentCardResource;
import com.acme.keeplo.platform.subscription.interfaces.rest.transform.PaymentCardResourceFromEntityAssembler;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static org.springframework.http.HttpStatus.CREATED;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/api/v1/payment-cards", produces = APPLICATION_JSON_VALUE)
@Tag(name = "Payment Cards", description = "Endpoints for managing payment cards")
public class PaymentCardController {

    private final PaymentCardCommandService paymentCardCommandService;

    public PaymentCardController(PaymentCardCommandService paymentCardCommandService) {
        this.paymentCardCommandService = paymentCardCommandService;
    }

    /**
     * Create a new payment card
     */
    @PostMapping
    @Operation(summary = "Create a new payment card", description = "Creates a new payment card.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Payment card created successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request (e.g., invalid data).")
    })
    public ResponseEntity<PaymentCardResource> createPaymentCard(@RequestBody CreatePaymentCardResource resource) {
        var command = new CreatePaymentCardCommand(
                resource.cardNumber(),
                resource.holderName(),
                resource.expirationDate(),
                resource.cvv()
        );

        var result = paymentCardCommandService.handle(command);

        return result.map(card ->
                new ResponseEntity<>(PaymentCardResourceFromEntityAssembler.toResourceFromEntity(card), CREATED)
        ).orElse(ResponseEntity.badRequest().build());
    }

    @PutMapping("/{paymentCardId}")
    @Operation(summary = "Update an existing payment card", description = "Updates an existing payment card.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Payment card updated successfully."),
            @ApiResponse(responseCode = "400", description = "Bad request (e.g., card not found).")
    })
    public ResponseEntity<PaymentCardResource> updatePaymentCard(@PathVariable Long paymentCardId, @RequestBody UpdatePaymentCardResource resource) {
        var command = new UpdatePaymentCardCommand(
                paymentCardId,
                resource.cardNumber(),
                resource.holderName(),
                resource.expirationDate(),
                resource.cvv()
        );

        var result = paymentCardCommandService.handle(command);
        return result.map(card ->
                ResponseEntity.ok(PaymentCardResourceFromEntityAssembler.toResourceFromEntity(card))
        ).orElse(ResponseEntity.badRequest().build());
    }
}