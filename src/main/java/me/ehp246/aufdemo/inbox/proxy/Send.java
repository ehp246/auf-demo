package me.ehp246.aufdemo.inbox.proxy;

import java.util.List;

import me.ehp246.aufjms.api.annotation.ByJms;
import me.ehp246.aufjms.api.annotation.ByJms.To;

/**
 * @author Lei Yang
 *
 */
@ByJms(@To("auf-demo.inbox"))
public interface Send {
    void sum(List<Integer> add);
}
