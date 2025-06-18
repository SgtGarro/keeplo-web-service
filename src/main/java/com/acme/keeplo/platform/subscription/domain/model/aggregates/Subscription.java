package com.acme.keeplo.platform.subscription.domain.model.aggregates;
/**
 * Subscriptions Aggregate Root
 *
 * @summary
 * The Subscriptions class is an aggregate root that represents Subscriptions.
 * It is responsible for handling the CreateSubscriptions command.
 */

import com.acme.keeplo.platform.subscription.domain.model.entity.Memberships;
import com.acme.keeplo.platform.subscription.domain.model.entity.PaymentCard;
import com.acme.keeplo.platform.users.domain.model.aggregates.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;

@Entity
public class Subscription extends AbstractAggregateRoot<Subscription> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Getter
    @Setter
    @ManyToOne

    private Memberships membership;

    @Getter
    @Setter
    @OneToOne
    @JoinColumn(name = "payment_card_id", unique = true)
    private PaymentCard paymentCard;


    public Subscription() {
    }
    public Subscription(Memberships membership, PaymentCard paymentCard) {
        if (membership == null) throw new IllegalArgumentException("Membership cannot be null");
        if (!membership.isFree() && paymentCard == null)
            throw new IllegalArgumentException("Non-free membership requires a payment card");

        this.membership = membership;
        this.paymentCard = paymentCard;
    }

    public void validate() {
        if (membership == null) throw new IllegalStateException("Membership cannot be null");
        if (!membership.isFree() && paymentCard == null)
            throw new IllegalStateException("Non-free membership requires a payment card");
    }
}
