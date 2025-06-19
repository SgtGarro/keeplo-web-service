package com.acme.keeplo.platform.subscription.domain.services;

import com.acme.keeplo.platform.subscription.domain.model.aggregates.Subscription;
import com.acme.keeplo.platform.subscription.domain.model.commands.paymentCards.CreatePaymentCardCommand;
import com.acme.keeplo.platform.subscription.domain.model.commands.paymentCards.UpdatePaymentCardCommand;
import com.acme.keeplo.platform.subscription.domain.model.entity.PaymentCard;

import java.util.Optional;

public interface PaymentCardCommandService {
    Optional<PaymentCard> handle(CreatePaymentCardCommand command); // Cambiado a PaymentCard
    Optional<PaymentCard> handle(UpdatePaymentCardCommand command); // Cambiado a PaymentCard
}