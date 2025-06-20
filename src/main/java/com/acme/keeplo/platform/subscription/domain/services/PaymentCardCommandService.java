package com.acme.keeplo.platform.subscription.domain.services;

import com.acme.keeplo.platform.subscription.domain.model.commands.paymentCards.CreatePaymentCardCommand;
import com.acme.keeplo.platform.subscription.domain.model.commands.paymentCards.UpdatePaymentCardCommand;
import com.acme.keeplo.platform.subscription.domain.model.entity.PaymentCard;

import java.util.Optional;

/**
 * Service interface for handling commands related to payment cards.
 *
 * Provides methods to create and update PaymentCard entities.
 */
public interface PaymentCardCommandService {

    /**
     * Handles the creation of a new payment card.
     *
     * @param command the command containing the information to create the payment card
     * @return an Optional containing the created PaymentCard entity
     */
    Optional<PaymentCard> handle(CreatePaymentCardCommand command);

    /**
     * Handles the update of an existing payment card.
     *
     * @param command the command containing the updated information for the payment card
     * @return an Optional containing the updated PaymentCard entity if found, otherwise empty
     */
    Optional<PaymentCard> handle(UpdatePaymentCardCommand command);
}