package com.acme.keeplo.platform.subscription.application.internal.commandservices;

import com.acme.keeplo.platform.subscription.domain.model.aggregates.Subscription;
import com.acme.keeplo.platform.subscription.domain.model.commands.subscriptions.CreateSubscriptionCommand;
import com.acme.keeplo.platform.subscription.domain.model.commands.subscriptions.UpdateSubscriptionCommand;
import com.acme.keeplo.platform.subscription.domain.model.entity.PaymentCard;
import com.acme.keeplo.platform.subscription.domain.services.SubscriptionCommandService;
import com.acme.keeplo.platform.subscription.infrastructure.persistence.jpa.MembershipRepository;
import com.acme.keeplo.platform.subscription.infrastructure.persistence.jpa.PaymentCardRepository;
import com.acme.keeplo.platform.users.infrastructure.persistence.jpa.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


/**
 * SubscriptionCommandService Implementation
 *
 * @summary
 * Implementation of the SubscriptionCommandService interface.
 * It is responsible for handling Subscription commands.
 *
 * @since 1.0
 */

@Service
public class SubscriptionCommandServiceImpl implements SubscriptionCommandService {

    private final UsersRepository userRepository;
    private final MembershipRepository membershipRepository;
    private final PaymentCardRepository paymentCardRepository;

    public SubscriptionCommandServiceImpl(
            UsersRepository userRepository,
            MembershipRepository membershipRepository,
            PaymentCardRepository paymentCardRepository
    ) {
        this.userRepository = userRepository;
        this.membershipRepository = membershipRepository;
        this.paymentCardRepository = paymentCardRepository;
    }
    /**
     * {@inheritDoc}
     */
    @Override
    public Optional<Subscription> handle(CreateSubscriptionCommand command) {
        var user = userRepository.findById(command.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        var membership = membershipRepository.findById(command.membershipId())
                .orElseThrow(() -> new IllegalArgumentException("Membership not found"));

        PaymentCard paymentCard = null;
        if (!membership.isFree()) {
            paymentCard = paymentCardRepository.findById(command.paymentCardId())
                    .orElseThrow(() -> new IllegalArgumentException("Payment card not found"));
        }

        user.setMembership(membership);
        user.setPaymentCard(paymentCard);

        userRepository.save(user);

        var subscription = new Subscription(membership, paymentCard, user.getId());

        return Optional.of(subscription);
    }

    @Override
    public Optional<Subscription> handle(UpdateSubscriptionCommand command) {
        var user = userRepository.findById(command.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));

        var membership = membershipRepository.findById(command.membershipId())
                .orElseThrow(() -> new IllegalArgumentException("Membership not found"));

        PaymentCard paymentCard = null;
        if (!membership.isFree()) {
            paymentCard = paymentCardRepository.findById(command.paymentCardId())
                    .orElseThrow(() -> new IllegalArgumentException("Payment card not found"));
        }

        user.setMembership(membership);
        user.setPaymentCard(paymentCard);

        userRepository.save(user);

        var updatedSubscription = new Subscription(membership, paymentCard, user.getId());

        return Optional.of(updatedSubscription);
    }
}
