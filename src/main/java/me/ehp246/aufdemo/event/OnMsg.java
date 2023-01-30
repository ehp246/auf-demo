package me.ehp246.aufdemo.event;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import me.ehp246.aufjms.api.annotation.ForJmsType;
import me.ehp246.aufjms.api.annotation.Invoking;
import me.ehp246.aufjms.api.jms.JmsMsg;

/**
 * @author Lei Yang
 *
 */
@ForJmsType(value = ".*")
public class OnMsg {
    private final static Logger LOGGER = LogManager.getLogger(OnMsg.class);

    @Invoking
    public void perform(final JmsMsg msg) {
        LOGGER.atInfo().log("Type: {}, id: {}", msg::type, msg::correlationId);
    }
}
