# Echo

Echo is a caching library for interfaces.

## What does it do?

Simply put, it remembers what methods were called and what the results were.
The inner workings of the methods are not of importance - Echo just remembers
that the method was called and it returned something.

The first time you call a method, the actual method is invoked. The calls after
that are intercepted by Echo and a cached result is returned.

## How do I use it?

To use Echo, you just wrap your object with `Echo.create()`:
```
Foo actualFoo = new Foo();
Foo echoedFoo = Echo.create(actualFoo);

// Now you use echoedFoo just as you would use actualFoo
int result = echoedFoo.doSomething();

// However, THIS time the result comes from the cached
int cachedResult = echoedFoo.doSomething();

// If you need to call the actual method again, you can clear the cache:
Echo.clear(Foo.class);
```

## Roadmap

Echo is not feature-complete yet. Upcoming features include:

- Cache by arguments (now the cached results are always used, even if the
    subsequent calls used different arguments)

- Support for callbacks

- Timestamped cache entries / Automatic cache invalidation after certain time

- Clear cache by method

## License

Echo is licensed under MIT license.

```
Copyright (c) 2016 Harri Pellikka

Permission is hereby granted, free of charge, to any person obtaining
a copy of this software and associated documentation files (the
"Software"), to deal in the Software without restriction, including
without limitation the rights to use, copy, modify, merge, publish,
distribute, sublicense, and/or sell copies of the Software, and to
permit persons to whom the Software is furnished to do so, subject to
the following conditions:

The above copyright notice and this permission notice shall be included
in all copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND,
EXPRESS OR IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF
MERCHANTABILITY, FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT.
IN NO EVENT SHALL THE AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY
CLAIM, DAMAGES OR OTHER LIABILITY, WHETHER IN AN ACTION OF CONTRACT,
TORT OR OTHERWISE, ARISING FROM, OUT OF OR IN CONNECTION WITH THE
SOFTWARE OR THE USE OR OTHER DEALINGS IN THE SOFTWARE.
```
