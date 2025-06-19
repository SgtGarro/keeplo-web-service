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
// Añade @Getter aquí para generar getters para TODOS los campos, incluyendo 'id'
// Si solo quieres 'id', puedes poner @Getter sobre private Long id;
@Getter
@Setter // Puedes mantener @Setter si lo necesitas para otros fines, aunque el Aggregate Root a menudo evita setters directos para control del dominio.
public class Subscription extends AbstractAggregateRoot<Subscription> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // Este campo ahora tendrá un getter gracias al @Getter a nivel de clase

    @ManyToOne // Una membresía puede tener muchas suscripciones
    @JoinColumn(name = "membership_id", nullable = false) // Asegúrate de que no sea nula
    private Memberships membership;

    @OneToOne(cascade = CascadeType.ALL, orphanRemoval = true) // Si la tarjeta de pago es exclusiva de esta suscripción
    @JoinColumn(name = "payment_card_id", unique = true)
    private PaymentCard paymentCard;

    @OneToOne // Una suscripción le pertenece a un solo usuario
    @JoinColumn(name = "user_id", unique = true, nullable = false) // Cada usuario tiene una única suscripción activa (o ninguna)
    private User user; // Referencia a tu entidad User del módulo IAM

    // Constructor vacío para JPA
    protected Subscription() { // Cambiado a 'protected' para JPA
    }

    // Constructor para crear una nueva suscripción
    public Subscription(Memberships membership, PaymentCard paymentCard, User user) {
        if (membership == null) throw new IllegalArgumentException("Membership cannot be null");
        if (!membership.isFree() && paymentCard == null)
            throw new IllegalArgumentException("Non-free membership requires a payment card");
        if (user == null) throw new IllegalArgumentException("User cannot be null for a subscription");

        this.membership = membership;
        this.paymentCard = paymentCard;
        this.user = user;
    }

    // Métodos para actualizar la membresía o la tarjeta de pago
    public void updateMembership(Memberships newMembership) {
        if (newMembership == null) throw new IllegalArgumentException("New membership cannot be null");
        this.membership = newMembership;
    }

    public void updatePaymentCard(PaymentCard newPaymentCard) {
        // Validación para asegurarse de que la tarjeta de pago sea nula si la membresía es gratuita
        if (this.membership.isFree() && newPaymentCard != null) {
            throw new IllegalArgumentException("Free membership cannot have a payment card.");
        }
        // Validación para asegurarse de que la tarjeta de pago no sea nula si la membresía NO es gratuita
        if (!this.membership.isFree() && newPaymentCard == null) {
            throw new IllegalArgumentException("Non-free membership requires a payment card.");
        }
        this.paymentCard = newPaymentCard;
    }

    // Método de validación de estado (útil antes de guardar)
    public void validateState() {
        if (membership == null) throw new IllegalStateException("Subscription must have a membership");
        if (!membership.isFree() && paymentCard == null)
            throw new IllegalStateException("Non-free membership requires a payment card");
        if (user == null) throw new IllegalStateException("Subscription must be associated with a user");
    }
}