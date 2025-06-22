package com.acme.keeplo.platform.subscription.interfaces.rest.transform;

import com.acme.keeplo.platform.subscription.domain.model.commands.subscriptions.CreateSubscriptionCommand;
import com.acme.keeplo.platform.subscription.interfaces.rest.resources.CreateSubscriptionResource;

/**
 * Converts CreateSubscriptionResource to CreateSubscriptionCommand.
 */
public class CreateSubscriptionCommandFromResourceAssembler {

    /**
     * Converts a CreateSubscriptionResource to a CreateSubscriptionCommand.
     *
     * @param resource CreateSubscriptionResource to convert
     * @return CreateSubscriptionCommand created from the resource
     */
    public static CreateSubscriptionCommand toCommandFromResource(CreateSubscriptionResource resource) {
        return new CreateSubscriptionCommand(
                resource.userId(),
                resource.membershipId(),
                resource.paymentCardId()
        );
    }
}