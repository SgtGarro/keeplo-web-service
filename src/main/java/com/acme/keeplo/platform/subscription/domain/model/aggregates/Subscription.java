package com.acme.keeplo.platform.subscription.domain.model.aggregates;

/**
 * Subscriptions Aggregate Root
 *
 * @summary
 * The Subscriptions class is an aggregate root that represents Subscriptions.
 * It is responsible for handling the CreateSubscriptions command.
 */

import com.acme.keeplo.platform.iam.domain.model.aggregates.User; // Importa tu nueva entidad User
import com.acme.keeplo.platform.subscription.domain.model.entity.Memberships;
import com.acme.keeplo.platform.subscription.domain.model.entity.PaymentCard;
import jakarta.persistence.*;
import lombok.Getter; // Asegúrate de que esta importación esté presente
import lombok.Setter; // Asegúrate de que esta importación esté presente
import org.springframework.data.domain.AbstractAggregateRoot;


@Entity
@Getter
@Setter
public class Subscription extends AbstractAggregateRoot<Subscription> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "membership_id", nullable = false)
    private Memberships membership;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "payment_card_id", unique = true)
    private PaymentCard paymentCard;

    @OneToOne
    @JoinColumn(name = "user_id", unique = true, nullable = false)
    private User user;


    protected Subscription() {
    }


    public Subscription(Memberships membership, PaymentCard paymentCard, User user) {
        if (membership == null) throw new IllegalArgumentException("Membership cannot be null");
        if (!membership.isFree() && paymentCard == null)
            throw new IllegalArgumentException("Non-free membership requires a payment card");
        if (user == null) throw new IllegalArgumentException("User cannot be null for a subscription");

        this.membership = membership;
        this.paymentCard = paymentCard;
        this.user = user;
    }


    public void updateMembership(Memberships newMembership) {
        if (newMembership == null) throw new IllegalArgumentException("New membership cannot be null");
        this.membership = newMembership;
    }

    public void updatePaymentCard(PaymentCard newPaymentCard) {

        if (!this.membership.isFree() && newPaymentCard == null) {
            throw new IllegalArgumentException("Non-free membership requires a payment card.");
        }
        this.paymentCard = newPaymentCard;
    }

    public void validateState() {
        if (membership == null) throw new IllegalStateException("Subscription must have a membership");
        if (!membership.isFree() && paymentCard == null)
            throw new IllegalStateException("Non-free membership requires a payment card");
        if (user == null) throw new IllegalStateException("Subscription must be associated with a user");
    }
}