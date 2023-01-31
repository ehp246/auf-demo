package me.ehp246.aufdemo.proxy;

import java.time.Instant;

import org.springframework.scheduling.annotation.Scheduled;

import me.ehp246.aufjms.api.annotation.ByJms;
import me.ehp246.aufjms.api.annotation.ByJms.To;

/**
 * @author Lei Yang
 *
 */
@ByJms(@To("auf-demo.bulletin"))
public interface SendHeartbeat {
    void heartbeat(Payload payload);

    @Scheduled(fixedRateString = "${auf-demo.heartbeat.delay:PT10S}")
    default void onSchedule() {
        this.heartbeat(new Payload(Instant.now()));
    }

    record Payload(Instant instant) {
    }
}
