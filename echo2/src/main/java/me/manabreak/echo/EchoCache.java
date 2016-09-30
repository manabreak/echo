package me.manabreak.echo;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class EchoCache {

    private final List<EchoedInvocation> cache = new ArrayList<>();

    public boolean contains(EchoedInvocation ei) {
        for (EchoedInvocation invocation : cache) {
            if (invocation.method.equals(ei.method)) return true;
        }
        return false;
    }

    public EchoedInvocation get(EchoedInvocation ei) {
        for (EchoedInvocation invocation : cache) {
            if (invocation.method.equals(ei.method)) return invocation;
        }
        return null;
    }

    public void add(EchoedInvocation ei) {
        cache.add(ei);
    }

    public void clear(Method method) {
        for (int i = 0; i < cache.size(); i++) {
            EchoedInvocation invocation = cache.get(i);
            if (invocation.method.equals(method)) {
                cache.remove(invocation);
                i--;
            }
        }
    }
}
