package com.acme.keeplo.platform.subscription.application.internal.queryservices;

import com.acme.keeplo.platform.subscription.domain.model.aggregates.Subscription;
import com.acme.keeplo.platform.subscription.domain.model.queries.GetSubscriptionByUserIdQuery;
import com.acme.keeplo.platform.subscription.domain.services.SubscriptionQueryService;
import com.acme.keeplo.platform.users.infrastructure.persistence.jpa.UsersRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class SubscriptionQueryServiceImpl implements SubscriptionQueryService {

    private final UsersRepository userRepository;

    public SubscriptionQueryServiceImpl(UsersRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public Optional<Subscription> handle(GetSubscriptionByUserIdQuery query) {
        var user = userRepository.findById(query.userId())
                .orElseThrow(() -> new IllegalArgumentException("User not found"));


        return Optional.of(new Subscription());
    }

}
