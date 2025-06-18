package com.acme.keeplo.platform.subscription.application.internal.commandservices;

import com.acme.keeplo.platform.subscription.domain.model.aggregates.Subscription;
import com.acme.keeplo.platform.subscription.domain.model.commands.subscriptions.CreateSubscriptionCommand;
import com.acme.keeplo.platform.subscription.domain.model.commands.subscriptions.UpdateSubscriptionCommand;
import com.acme.keeplo.platform.subscription.domain.model.entity.PaymentCard;
import com.acme.keeplo.platform.subscription.domain.services.SubscriptionCommandService;
import com.acme.keeplo.platform.subscription.infrastructure.persistence.jpa.MembershipRepository;
import com.acme.keeplo.platform.subscription.infrastructure.persistence.jpa.PaymentCardRepository;
import com.acme.keeplo.platform.subscription.infrastructure.persistence.jpa.SubscriptionRepository;
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
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionCommandServiceImpl(
            UsersRepository userRepository,
            MembershipRepository membershipRepository,
            PaymentCardRepository paymentCardRepository,
            SubscriptionRepository subscriptionRepository
    ) {
        this.userRepository = userRepository;
        this.membershipRepository = membershipRepository;
        this.paymentCardRepository = paymentCardRepository;
        this.subscriptionRepository = subscriptionRepository;
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

        var subscription = new Subscription(membership, paymentCard);
        subscriptionRepository.save(subscription); // ✅ primero guardas la suscripción

        user.setSubscription(subscription); // ✅ luego la vinculas al usuario
        userRepository.save(user); // ✅ y finalmente actualizas el usuario

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

        var updatedSubscription = new Subscription(membership, paymentCard);
        subscriptionRepository.save(updatedSubscription); // ✅ guardar actualización

        user.setSubscription(updatedSubscription); // ✅ actualizar relación
        userRepository.save(user);

        return Optional.of(updatedSubscription);
    }
}