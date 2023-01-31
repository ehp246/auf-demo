package me.ehp246.aufdemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

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
        @Inbound(value = @From(value = "auf-demo.bulletin", type = DestinationType.TOPIC), autoStartup = "true", scan = OnMsg.class),
        @Inbound(value = @From(value = "auf-demo.event", type = DestinationType.TOPIC, sub = @Sub(name = "auf-demo", shared = true, durable = true)), autoStartup = "true", scan = OnMsg.class) })
@SpringBootApplication
@EnableScheduling
public class AufDemoApplication {
    public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper().setSerializationInclusion(Include.NON_NULL)
            .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false).registerModule(new JavaTimeModule())
            .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS).registerModule(new MrBeanModule())
            .registerModule(new ParameterNamesModule());

    public static void main(final String[] args) {
        SpringApplication.run(AufDemoApplication.class, args);
    }
}
