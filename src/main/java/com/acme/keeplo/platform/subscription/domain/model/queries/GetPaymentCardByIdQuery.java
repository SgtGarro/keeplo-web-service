package com.acme.keeplo.platform.subscription.domain.model.queries;
/**
 * Query to retrieve a payment card by its ID.
 *
 * This query is used to fetch a specific payment card based on the provided ID.
 *
 * @param cardId the unique identifier of the payment card. Must be a positive value.
 * @throws IllegalArgumentException if the provided cardId is not positive.
 */
public record GetPaymentCardByIdQuery(Long cardId) {
    public GetPaymentCardByIdQuery {
        if (cardId <= 0)
            throw new IllegalArgumentException("cardId must be positive.");
    }
}