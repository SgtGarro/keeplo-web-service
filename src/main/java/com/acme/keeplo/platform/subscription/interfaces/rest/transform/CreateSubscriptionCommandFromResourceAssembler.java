package com.acme.keeplo.platform.subscription.interfaces.rest.transform;

import com.acme.keeplo.platform.subscription.domain.model.commands.subscriptions.CreateSubscriptionCommand;
import com.acme.keeplo.platform.subscription.interfaces.rest.resources.CreateSubscriptionResource;

public class CreateSubscriptionCommandFromResourceAssembler {
    /**
     * Converts a CreateSubscriptionResource to a CreateSubscriptionCommand.
     * @param resource CreateSubscriptionResource to convert
     * @return CreateSubscriptionCommand created from the resource
     */
    public static CreateSubscriptionCommand toCommandFromResource(CreateSubscriptionResource resource) {
        return new CreateSubscriptionCommand(
                resource.membershipId(),
                resource.paymentCardId(),
                resource.userId()
        );
    }
}
