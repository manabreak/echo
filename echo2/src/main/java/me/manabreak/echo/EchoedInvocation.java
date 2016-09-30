package me.manabreak.echo;

import java.lang.reflect.Method;

class EchoedInvocation {
    Method method;
    private final Object[] args;
    Object returnValue;

    public EchoedInvocation(Method method, Object[] args) {
        this.method = method;
        this.args = args;
    }
}
