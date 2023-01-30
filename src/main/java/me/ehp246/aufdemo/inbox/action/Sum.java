package me.ehp246.aufdemo.inbox.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.ehp246.aufjms.api.annotation.ForJmsType;
import me.ehp246.aufjms.api.annotation.Invoking;

/**
 * @author Lei Yang
 *
 */
@ForJmsType("Sum")
public class Sum {
    private static final Logger logger = LogManager.getLogger();

    @Invoking
    public int sum(final int a, final int b) {
        final var sum = a + b;
        logger.info("Sum: " + sum);
        return sum;
    }
}
