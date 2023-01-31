package me.ehp246.aufdemo.rs;

import java.time.Instant;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import me.ehp246.aufdemo.proxy.SendHeartbeat;

/**
 * @author Lei Yang
 *
 */
@RestController
public class Controller {
    private final static Logger LOGGER = LogManager.getLogger();
    @Autowired
    @Qualifier("sendHeartbeatByRest")
    private SendHeartbeat sendHeartbeatByRest;

    @Autowired
    @Qualifier("sendHeartbeatByJms")
    private SendHeartbeat sendHeartbeatByJms;

    @PostMapping("/heartbeat/rest")
    public void byRest() {
        this.sendHeartbeatByRest.heartbeat(new SendHeartbeat.Payload(Instant.now()));
    }

    @PostMapping("/heartbeat/jms")
    public void byJms() {
        this.sendHeartbeatByJms.heartbeat(new SendHeartbeat.Payload(Instant.now()));
    }

    @PostMapping("/heartbeat")
    public void heartbeat(@RequestBody final SendHeartbeat.Payload payload) {
        LOGGER.atInfo().log("Heartbeat on {}", payload::instant);
    }
}
