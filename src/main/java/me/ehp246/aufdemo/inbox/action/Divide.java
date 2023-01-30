package me.ehp246.aufdemo.inbox.action;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.ehp246.aufjms.api.annotation.ForJmsType;
import me.ehp246.aufjms.api.annotation.Invoking;

/**
 * @author Lei Yang
 *
 */
@ForJmsType("Divide")
public class Divide {
    private static final Logger logger = LogManager.getLogger();

    @Invoking
    public float perform(final int a, final int b) {
        final var div = (float) (a / b);

        logger.info("Result: " + div);

        return div;
    }
}
