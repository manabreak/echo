package me.manabreak;

import org.junit.Before;
import org.junit.Test;

import me.manabreak.echo.Echo;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class EchoTest {

    boolean actualFooInvoked = false;
    Foo actualFoo;

    @Before
    public void setUp() {
        actualFooInvoked = false;
        actualFoo = new Foo() {
            @Override
            public int bar() {
                actualFooInvoked = true;
                return 42;
            }
        };
    }

    @Test
    public void testEchoReturnValue() {
        Foo echoedFoo = Echo.create(actualFoo);
        assertNotNull(echoedFoo);

        int result = echoedFoo.bar();
        assertEquals(42, result);
        assertTrue(actualFooInvoked);

        actualFooInvoked = false;
        result = echoedFoo.bar();
        assertEquals(42, result);
        assertFalse(actualFooInvoked);
    }

    @Test
    public void testClearCacheByInterface() {
        Foo echoedFoo = Echo.create(actualFoo);

        echoedFoo.bar();
        assertTrue(actualFooInvoked);

        actualFooInvoked = false;
        echoedFoo.bar();
        assertFalse(actualFooInvoked);

        Echo.clear(Foo.class);

        echoedFoo.bar();
        assertTrue(actualFooInvoked);
    }

    @Test
    public void testClearCacheByMethod() {
        Foo echoedFoo = Echo.create(actualFoo);

        echoedFoo.bar();
        assertTrue(actualFooInvoked);

        actualFooInvoked = false;
        echoedFoo.bar();
        assertFalse(actualFooInvoked);

        Echo.clear(Foo.class);

        echoedFoo.bar();
        assertTrue(actualFooInvoked);
    }

    public interface Foo {
        int bar();
    }

}