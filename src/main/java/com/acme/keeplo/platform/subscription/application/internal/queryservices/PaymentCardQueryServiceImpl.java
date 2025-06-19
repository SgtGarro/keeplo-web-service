package com.acme.keeplo.platform.subscription.application.internal.queryservices;

import com.acme.keeplo.platform.subscription.domain.model.entity.PaymentCard; // Import the correct entity
import com.acme.keeplo.platform.subscription.domain.model.queries.GetPaymentCardByIdQuery;
import com.acme.keeplo.platform.subscription.domain.services.PaymentCardQueryService;
import com.acme.keeplo.platform.subscription.infrastructure.persistence.jpa.PaymentCardRepository;
import org.springframework.stereotype.Service;

import java.util.Optional; // Ensure this import is present

@Service // Ensure this annotation is present for Spring to detect it
public class PaymentCardQueryServiceImpl implements PaymentCardQueryService {

    private final PaymentCardRepository paymentCardRepository;

    public PaymentCardQueryServiceImpl(PaymentCardRepository paymentCardRepository) {
        this.paymentCardRepository = paymentCardRepository;
    }

    @Override
    public Optional<PaymentCard> handle(GetPaymentCardByIdQuery query) {
        // The repository's findById naturally returns Optional<PaymentCard>
        return paymentCardRepository.findById(query.cardId());
    }
}