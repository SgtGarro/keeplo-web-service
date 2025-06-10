package com.acme.keeplo.platform.subscription.domain.services;

import com.acme.keeplo.platform.subscription.domain.model.aggregates.Subscription;
import com.acme.keeplo.platform.subscription.domain.model.commands.paymentCards.CreatePaymentCardCommand;
import com.acme.keeplo.platform.subscription.domain.model.commands.paymentCards.UpdatePaymentCardCommand;

import java.util.Optional;

public interface PaymentCardCommandService {
    Optional<Subscription> handle(CreatePaymentCardCommand command);

    Optional<Subscription> handle(UpdatePaymentCardCommand command);
}
