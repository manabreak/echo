package me.manabreak.echo;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class Echo {

    private static final EchoCache echoCache = new EchoCache();

    public static <T> T create(final T actual) {
        Class<?> actualClass = actual.getClass().getInterfaces()[0];
        //noinspection unchecked
        return (T) Proxy.newProxyInstance(actualClass.getClassLoader(), new Class[]{actualClass}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {

                EchoedInvocation ei = new EchoedInvocation(method, args);
                if (echoCache.contains(ei)) {
                    EchoedInvocation invocation = echoCache.get(ei);
                    return invocation.returnValue;
                }

                ei.returnValue = method.invoke(actual, args);
                echoCache.add(ei);
                return ei.returnValue;
            }
        });
    }

    public static <T> void clear(Class<T> c) {
        for (Method method : c.getMethods()) {
            echoCache.clear(method);
        }
    }
}
