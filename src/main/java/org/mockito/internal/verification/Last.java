/*
 * Copyright (c) 2007 Mockito contributors
 * This program is made available under the terms of the MIT License.
 */
package org.mockito.internal.verification;

import org.mockito.internal.verification.api.VerificationDataInOrder;
import org.mockito.internal.verification.api.VerificationInOrderMode;
import org.mockito.internal.verification.api.VerificationData;
import org.mockito.verification.VerificationMode;

import static org.mockito.internal.verification.checkers.LastInvocationChecker.checkLastInvocation;

public class Last implements VerificationInOrderMode, VerificationMode {

    @Override
    public void verify(VerificationData data) {
        checkLastInvocation(data.getAllInvocations(), data.getTarget());
    }

    @Override
    public void verifyInOrder(VerificationDataInOrder data) {
        checkLastInvocation(data.getAllInvocations(), data.getWanted());
    }

    @Override
    public String toString() {
        return "Wanted last invocation";
    }

    public VerificationMode description(String description) {
        return VerificationModeFactory.description(this, description);
    }
}
