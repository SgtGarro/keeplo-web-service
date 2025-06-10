package com.acme.keeplo.platform.subscription.domain.model.aggregates;
/**
 * Users Aggregate Root
 *
 * @summary
 * The Users class is an aggregate root that represents a favorite news source.
 * It is responsible for handling the CreateUsers command.
 */

import com.acme.keeplo.platform.subscription.domain.model.entity.Memberships;
import com.acme.keeplo.platform.subscription.domain.model.entity.PaymentCard;
import com.acme.keeplo.platform.users.domain.model.aggregates.Users;
import lombok.Getter;
import org.springframework.data.domain.AbstractAggregateRoot;

import java.util.Date;

public class Subscription extends AbstractAggregateRoot<Subscription> {

    @Getter
    private Memberships membership;
    @Getter
    private PaymentCard paymentCard;
    @Getter
    private Long userId;

    public Subscription(Memberships membership, PaymentCard paymentCard, Long userId) {
        if (membership == null) throw new IllegalArgumentException("Membership cannot be null");
        if (!membership.isFree() && paymentCard == null)
            throw new IllegalArgumentException("Non-free membership requires a payment card");

        this.membership = membership;
        this.paymentCard = paymentCard;
        this.userId = userId;
    }

    public Subscription buildFromUser(Users user) {
        return new Subscription(user.getMembership(), user.getPaymentCard(),user.getId());
    }
}
