package me.ehp246.aufdemo.rs;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import me.ehp246.aufdemo.inbox.proxy.Send;
import me.ehp246.aufdemo.proxy.SendHeartbeat;

/**
 * @author Lei Yang
 *
 */
@RestController
public class Controller {
    private final static Logger LOGGER = LogManager.getLogger();

    @Autowired
    private Send send;

    @PostMapping("/sum")
    public void sum(@RequestBody final List<Integer> ops) {
        send.sum(ops);
    }

    @PostMapping("/heartbeat")
    public void heartbeat(@RequestBody final SendHeartbeat.Payload payload) {
        LOGGER.atInfo().log("Heartbeat on {}", payload::instant);
    }
}
