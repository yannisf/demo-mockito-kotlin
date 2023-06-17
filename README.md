# mockito-kotlin

Have you been using mockito with java? Would you be interest to use it in Kotlin? How about **idiomatic** kotlin?
mockito-kotlin is still evolving, you might encounter several issues that get solved in newer editions. 
Let's discuss some of them and possibly a little more using a simple Kotlin (gradle) library project. 

## Points

* `protected` in Java -> `internal` in Kotlin?
* When to `mock` or `spy`
* When `'when'` looks strange to you, use `whenever`
* reified matchers, because you do not have to say the same again
* `NPE` when using `any()` matchers
* `doReturn` to skip the implementation (uncomment the exception)
* Promote test robustness with captors 

## Resources

### Kotlin testing
* https://www.baeldung.com/kotlin/junit-5-kotlin

### AssertJ
* https://www.baeldung.com/introduction-to-assertj

### Reified
* https://www.baeldung.com/kotlin/reified-functions

### Kotlin Mockito
* https://github.com/mockito/mockito-kotlin
* https://github.com/mockito/mockito-kotlin/wiki/Mocking-and-verifying

### Magic Numbers
* https://news.mit.edu/2019/answer-life-universe-and-everything-sum-three-cubes-mathematics-0910
* https://www.businessinsider.com/most-important-numbers-2012-7#eulers-identity-eipi--1-10

