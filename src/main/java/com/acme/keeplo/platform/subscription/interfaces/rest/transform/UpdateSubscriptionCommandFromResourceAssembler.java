package com.acme.keeplo.platform.subscription.interfaces.rest.transform;
import com.acme.keeplo.platform.subscription.domain.model.commands.subscriptions.UpdateSubscriptionCommand;
import com.acme.keeplo.platform.subscription.interfaces.rest.resources.UpdateSubscriptionResource;

public class UpdateSubscriptionCommandFromResourceAssembler {
    /**
     * Converts a UpdateSubscriptionResource to a UpdateSubscriptionCommand.
     *
     * @param resource UpdateSubscriptionResource to convert
     * @return UpdateSubscriptionCommand Updated from the resource
     */
    public static UpdateSubscriptionCommand toCommandFromResource(Long subscriptionId ,UpdateSubscriptionResource resource) {
        return new UpdateSubscriptionCommand(
                subscriptionId,
                resource.userId(),
                resource.membershipId(),
                resource.paymentCardId()
        );
    }
}