package com.acme.keeplo.platform.subscription.application.internal.commandservices;

import com.acme.keeplo.platform.iam.infrastructure.persistence.jpa.repositories.UserRepository;
import com.acme.keeplo.platform.subscription.domain.model.aggregates.Subscription;
import com.acme.keeplo.platform.subscription.domain.model.commands.subscriptions.CreateSubscriptionCommand;
import com.acme.keeplo.platform.subscription.domain.model.commands.subscriptions.UpdateSubscriptionCommand;
import com.acme.keeplo.platform.subscription.domain.model.entity.PaymentCard;
import com.acme.keeplo.platform.subscription.domain.services.SubscriptionCommandService;
import com.acme.keeplo.platform.subscription.infrastructure.persistence.jpa.MembershipRepository;
import com.acme.keeplo.platform.subscription.infrastructure.persistence.jpa.PaymentCardRepository;
import com.acme.keeplo.platform.subscription.infrastructure.persistence.jpa.SubscriptionRepository;

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

    private final UserRepository userRepository; // Ahora es tu nuevo UserRepository del IAM
    private final MembershipRepository membershipRepository;
    private final PaymentCardRepository paymentCardRepository;
    private final SubscriptionRepository subscriptionRepository;

    public SubscriptionCommandServiceImpl(
            UserRepository userRepository, // Cambiado UsersRepository por UserRepository
            MembershipRepository membershipRepository,
            PaymentCardRepository paymentCardRepository,
            SubscriptionRepository subscriptionRepository
    ) {
        this.userRepository = userRepository;
        this.membershipRepository = membershipRepository;
        this.paymentCardRepository = paymentCardRepository;
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public Optional<Subscription> handle(CreateSubscriptionCommand command) {
        // 1. Validar y obtener el usuario (de IAM)
        var user = userRepository.findById(command.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found with ID: " + command.userId()));

        // 2. Validar y obtener la membresía
        var membership = membershipRepository.findById(command.membershipId())
                .orElseThrow(() -> new IllegalArgumentException("Membership not found with ID: " + command.membershipId()));

        // 3. Validar y obtener la tarjeta de pago (si aplica)
        PaymentCard paymentCard = null;
        if (!membership.isFree()) {
            if (command.paymentCardId() == null) {
                throw new IllegalArgumentException("Payment card is required for non-free membership.");
            }
            paymentCard = paymentCardRepository.findById(command.paymentCardId())
                    .orElseThrow(() -> new IllegalArgumentException("Payment card not found with ID: " + command.paymentCardId()));
        }

        // 4. Verificar si el usuario ya tiene una suscripción activa (opcional, pero buena práctica)
        if (subscriptionRepository.findByUserId(user.getId()).isPresent()) {
            throw new IllegalStateException("User with ID " + user.getId() + " already has an active subscription.");
        }

        // 5. Crear la suscripción
        var subscription = new Subscription(membership, paymentCard, user);
        subscription.validateState(); // Asegura la consistencia antes de guardar

        // 6. Guardar la suscripción
        subscriptionRepository.save(subscription);

        // 7. Asociar la suscripción con el usuario (Bidireccional si es necesario)
        // Esto depende de cómo manejes la relación. Si User no tiene setSubscription(), no es necesario.
        // user.setSubscription(subscription); // Si User tiene una referencia a Subscription
        // userRepository.save(user); // Si cambiaste el usuario, guárdalo

        return Optional.of(subscription);
    }

    @Override
    public Optional<Subscription> handle(UpdateSubscriptionCommand command) {
        // 1. Obtener la suscripción existente
        var existingSubscription = subscriptionRepository.findById(command.subscriptionId())
                .orElseThrow(() -> new IllegalArgumentException("Subscription not found with ID: " + command.subscriptionId()));

        // 2. Obtener la nueva membresía (puede ser la misma)
        var newMembership = membershipRepository.findById(command.membershipId())
                .orElseThrow(() -> new IllegalArgumentException("Membership not found with ID: " + command.membershipId()));

        // 3. Obtener la nueva tarjeta de pago (si aplica y si ha cambiado)
        PaymentCard newPaymentCard = null;
        if (!newMembership.isFree()) {
            if (command.paymentCardId() == null) {
                throw new IllegalArgumentException("Payment card is required for non-free membership.");
            }
            newPaymentCard = paymentCardRepository.findById(command.paymentCardId())
                    .orElseThrow(() -> new IllegalArgumentException("Payment card not found with ID: " + command.paymentCardId()));
        } else {
            // Si la nueva membresía es gratuita, la tarjeta de pago debe ser nula
            newPaymentCard = null;
        }

        // 4. Actualizar los campos de la suscripción existente
        existingSubscription.updateMembership(newMembership);
        existingSubscription.updatePaymentCard(newPaymentCard);
        existingSubscription.validateState();

        // 5. Guardar la suscripción actualizada
        subscriptionRepository.save(existingSubscription);

        return Optional.of(existingSubscription);
    }
}

