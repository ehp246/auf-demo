package me.ehp246.aufdemo.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import me.ehp246.aufdemo.inbox.proxy.Send;

/**
 * @author Lei Yang
 *
 */
@RestController
public class Controller {
    @Autowired
    private Send send;

    @PostMapping("/sum")
    public void sum(@RequestBody final List<Integer> ops) {
        send.sum(ops);
    }
}
