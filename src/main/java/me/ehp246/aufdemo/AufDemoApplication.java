package me.ehp246.aufdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.module.mrbean.MrBeanModule;
import com.fasterxml.jackson.module.paramnames.ParameterNamesModule;

import me.ehp246.aufdemo.event.OnMsg;
import me.ehp246.aufjms.api.annotation.EnableByJms;
import me.ehp246.aufjms.api.annotation.EnableForJms;
import me.ehp246.aufjms.api.annotation.EnableForJms.Inbound;
import me.ehp246.aufjms.api.annotation.EnableForJms.Inbound.From;
import me.ehp246.aufjms.api.annotation.EnableForJms.Inbound.From.Sub;
import me.ehp246.aufjms.api.jms.DestinationType;
import me.ehp246.aufrest.api.annotation.EnableByRest;

/**
 * @author Lei Yang
 *
 */
@EnableByRest
@EnableByJms
@EnableForJms({ @Inbound(name = "auf-demo.inbox", value = @From("auf-demo.inbox")),
        @Inbound(name = "auf-demo.event", value = @From(value = "auf-demo.event", type = DestinationType.TOPIC, sub = @Sub(shared = false, durable = false)), autoStartup = "false", scan = OnMsg.class),
        @Inbound(name = "auf-demo.event/s-d$$D",
                value = @From(value = "auf-demo.event", type = DestinationType.TOPIC, sub = @Sub(value = "s-d$$D", shared = true, durable = true)), autoStartup = "false", scan = OnMsg.class),
        @Inbound(name = "auf-demo.event/s-nd", value = @From(value = "auf-demo.event", type = DestinationType.TOPIC, sub = @Sub(value = "s-nd", shared = true, durable = false)), autoStartup = "true", scan = OnMsg.class) })
@SpringBootApplication
public class AufDemoApplication {
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().setSerializationInclusion(Include.NON_NULL)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).registerModule(new MrBeanModule())
            .registerModule(new ParameterNamesModule());

    public static void main(final String[] args) {
        SpringApplication.run(AufDemoApplication.class, args);
    }
}
