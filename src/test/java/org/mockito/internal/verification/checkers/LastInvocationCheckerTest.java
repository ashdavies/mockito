package org.mockito.internal.verification.checkers;

import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.mockito.exceptions.base.MockitoAssertionError;
import org.mockito.exceptions.verification.TooFewActualInvocations;
import org.mockito.internal.invocation.InvocationBuilder;
import org.mockito.internal.invocation.InvocationMatcher;
import org.mockito.invocation.Invocation;

import java.util.Collections;

import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.internal.verification.checkers.LastInvocationChecker.checkLastInvocation;

public class LastInvocationCheckerTest {

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Test
    public void shouldMarkTooFewForLastInvocation() {
        Invocation invocation = new InvocationBuilder().simpleMethod().toInvocation();

        exception.expect(TooFewActualInvocations.class);
        exception.expectMessage("Wanted *at least* 1 time");
        exception.expectMessage("But was 0 times");

        checkLastInvocation(Collections.<Invocation>emptyList(), new InvocationMatcher(invocation));
    }

    @Test
    public void shouldReportUnmatchedLastInvocation() {
        Invocation first = new InvocationBuilder().simpleMethod().toInvocation();
        Invocation second = new InvocationBuilder().differentMethod().toInvocation();

        exception.expect(MockitoAssertionError.class);
        exception.expectMessage("Last invocation did not match!");
        exception.expectMessage("Wanted: simpleMethod");
        exception.expectMessage("But got: differentMethod");

        checkLastInvocation(asList(first, second), new InvocationMatcher(first));
    }

    @Test
    public void shouldMarkAsVerifiedForLastInvocation() {
        Invocation first = new InvocationBuilder().simpleMethod().toInvocation();
        Invocation second = new InvocationBuilder().differentMethod().toInvocation();

        checkLastInvocation(asList(first, second), new InvocationMatcher(second));

        assertThat(second.isVerified()).isTrue();
    }
}
