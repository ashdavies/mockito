package org.mockito.internal.verification.checkers;

import org.mockito.exceptions.base.MockitoAssertionError;
import org.mockito.invocation.Invocation;
import org.mockito.invocation.MatchableInvocation;

import java.util.List;

import static org.mockito.internal.invocation.InvocationMarker.markVerified;
import static org.mockito.internal.verification.checkers.AtLeastXNumberOfInvocationsChecker.checkAtLeastNumberOfInvocations;

public class LastInvocationChecker {

    public static void checkLastInvocation(List<Invocation> invocations, MatchableInvocation wanted) {
        checkAtLeastNumberOfInvocations(invocations, wanted, 1);

        Invocation last = invocations.get(invocations.size() - 1);
        if (!wanted.matches(last)) {
            throw new MockitoAssertionError("Last invocation did not match!\nWanted: " + wanted + "\n But got: " + last);
        }

        markVerified(invocations, wanted);
    }
}
