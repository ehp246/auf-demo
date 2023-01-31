package me.ehp246.aufdemo.proxy;

import java.time.Instant;

import org.springframework.scheduling.annotation.Scheduled;

import me.ehp246.aufjms.api.annotation.ByJms;
import me.ehp246.aufjms.api.annotation.ByJms.To;
import me.ehp246.aufrest.api.annotation.ByRest;
import me.ehp246.aufrest.api.annotation.OfRequest;

/**
 * @author Lei Yang
 *
 */
@ByRest(value = "http://localhost:${server.port:8080}/heartbeat", name = "sendHeartbeatByRest")
@ByJms(value = @To("auf-demo.bulletin"), name = "sendHeartbeatByJms")
public interface SendHeartbeat {
    @OfRequest(method = "POST")
    void heartbeat(Payload payload);

    @Scheduled(fixedRateString = "${auf-demo.heartbeat.delay:PT10S}")
    default void onSchedule() {
        this.heartbeat(new Payload(Instant.now()));
    }

    record Payload(Instant instant) {
    }
}
