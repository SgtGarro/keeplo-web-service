package com.acme.keeplo.platform.subscription.application.internal.commandservices;

import com.acme.keeplo.platform.subscription.domain.model.aggregates.Subscription;
import com.acme.keeplo.platform.subscription.domain.model.commands.paymentCards.CreatePaymentCardCommand;
import com.acme.keeplo.platform.subscription.domain.model.commands.paymentCards.UpdatePaymentCardCommand;
import com.acme.keeplo.platform.subscription.domain.services.PaymentCardCommandService;

import java.util.Optional;

public class PaymentCardCommandServiceImpl implements PaymentCardCommandService {
    @Override
    public Optional<Subscription> handle(CreatePaymentCardCommand command) {
        return Optional.empty();
    }

    @Override
    public Optional<Subscription> handle(UpdatePaymentCardCommand command) {
        return Optional.empty();
    }
}
