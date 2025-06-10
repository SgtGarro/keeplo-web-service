package com.acme.keeplo.platform.subscription.domain.model.queries;

public record GetPaymentCardByIdQuery(Long cardId) {
    public GetPaymentCardByIdQuery {
        if (cardId <= 0)
            throw new IllegalArgumentException("cardId must be positive.");
    }
}