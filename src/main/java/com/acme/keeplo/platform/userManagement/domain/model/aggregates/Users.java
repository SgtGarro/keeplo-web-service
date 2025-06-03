package com.acme.keeplo.platform.userManagement.domain.model.aggregates;
/**
 * Users Aggregate Root
 *
 * @summary
 * The Users class is an aggregate root that represents a favorite news source.
 * It is responsible for handling the CreateUsers command.
 */

import com.acme.keeplo.platform.userManagement.domain.model.commands.CreateUsersCommand;
import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.domain.AbstractAggregateRoot;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.util.Date;

@Entity
@EntityListeners(AuditingEntityListener.class)
public class Users extends AbstractAggregateRoot<Users>{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    private Long UserId;

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
