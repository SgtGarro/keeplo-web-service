package com.acme.keeplo.platform.users.domain.model.aggregates;
/**
 * Users Aggregate Root
 *
 * @summary
 * The Users class is an aggregate root that represents a favorite news source.
 * It is responsible for handling the CreateUsers command.
 */

import com.acme.keeplo.platform.subscription.domain.model.entity.Memberships;
import com.acme.keeplo.platform.subscription.domain.model.entity.PaymentCard;
import com.acme.keeplo.platform.users.domain.model.commands.CreateUsersCommand;
import com.acme.keeplo.platform.users.domain.model.valueObjects.UserId;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Users extends AbstractAggregateRoot<Users>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long id;

    @Column(nullable = false)
    @Getter
    private String email;

    @Column(nullable = false)
    @Getter
    private String password;

    @Column(nullable = false)
    @Getter
    private String name;

    @Column(nullable = false)
    @Getter
    private String profilePicture;

    @Setter
    @Getter
    @ManyToOne
    @JoinColumn(name = "membership_id")
    private Memberships membership;

    @Setter
    @ManyToOne
    @Getter
    @JoinColumn(name = "payment_card_id")
    private PaymentCard paymentCard;

    protected Users() {}

    /**
     * @summary Constructor.
     * It creates a new User instance based on the CreateUsersCommand command.
     * @param command - the CreateUsersCommand command
     */
    public Users(CreateUsersCommand command) {
        this.email = command.email();
        this.password = command.password();
        this.name = command.name();
        this.profilePicture = command.profilePicture();
    }

}
