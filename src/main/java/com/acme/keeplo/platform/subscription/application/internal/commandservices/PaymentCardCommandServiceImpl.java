package com.acme.keeplo.platform.subscription.application.internal.commandservices;

import com.acme.keeplo.platform.subscription.domain.model.commands.paymentCards.CreatePaymentCardCommand;
import com.acme.keeplo.platform.subscription.domain.model.commands.paymentCards.UpdatePaymentCardCommand;
import com.acme.keeplo.platform.subscription.domain.model.entity.PaymentCard; // Import the correct entity
import com.acme.keeplo.platform.subscription.domain.model.valueObjects.CardNumber;
import com.acme.keeplo.platform.subscription.domain.services.PaymentCardCommandService;
import com.acme.keeplo.platform.subscription.infrastructure.persistence.jpa.PaymentCardRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class PaymentCardCommandServiceImpl implements PaymentCardCommandService {

    private final PaymentCardRepository paymentCardRepository;

    public PaymentCardCommandServiceImpl(PaymentCardRepository paymentCardRepository) {
        this.paymentCardRepository = paymentCardRepository;
    }

    @Override
    public Optional<PaymentCard> handle(CreatePaymentCardCommand command) {
        // Create a new PaymentCard instance using the constructor we added
        var newPaymentCard = new PaymentCard(
                new CardNumber(command.cardNumber()),
                command.holderName(),
                command.expirationDate(),
                command.cvv()
        );

        paymentCardRepository.save(newPaymentCard);
        return Optional.of(newPaymentCard);
    }

    @Override
    public Optional<PaymentCard> handle(UpdatePaymentCardCommand command) {
        var existingCard = paymentCardRepository.findById(command.cardId());
        if (existingCard.isEmpty()) {
            return Optional.empty(); // Return empty if the payment card is not found
        }
        var cardToUpdate = existingCard.get();

        // Update the existing card using the update method we added
        cardToUpdate.update(
                new CardNumber(command.cardNumber()),
                command.holderName(),
                command.expirationDate(),
                command.cvv()
        );

        paymentCardRepository.save(cardToUpdate);
        return Optional.of(cardToUpdate);
    }
}