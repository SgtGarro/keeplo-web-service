package com.acme.keeplo.platform.subscription.application.internal.queryservices;

import com.acme.keeplo.platform.subscription.domain.model.aggregates.Subscription;
import com.acme.keeplo.platform.subscription.domain.model.queries.GetSubscriptionByUserIdQuery;
import com.acme.keeplo.platform.subscription.domain.services.SubscriptionQueryService;
import com.acme.keeplo.platform.subscription.infrastructure.persistence.jpa.SubscriptionRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptionQueryServiceImpl implements SubscriptionQueryService {

    private final SubscriptionRepository subscriptionRepository; // Inyecta SubscriptionRepository
    // private final UsersRepository userRepository; // Ya no necesitas inyectar UsersRepository aquí directamente si lo buscas por ID de suscripción

    public SubscriptionQueryServiceImpl(SubscriptionRepository subscriptionRepository) { // Solo inyecta el repositorio que necesitas
        this.subscriptionRepository = subscriptionRepository;
    }

    @Override
    public Optional<Subscription> handle(GetSubscriptionByUserIdQuery query) {
        // Busca directamente la suscripción por el userId en el repositorio de suscripciones
        // Asumimos que SubscriptionRepository tiene un método findByUserId
        return subscriptionRepository.findByUserId(query.userId());
    }
}
