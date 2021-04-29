package eu.ideell.api.util;

import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;

public class Monad<T> {

  private T value;

  private Monad(T value) {
    this.value = value;
  }

  public T get() {
    return value;
  }

  public <S> Monad<S> map(Function<T, S> transformer) {
    return new Monad<S>(transformer.apply(value));
  }

  public void accept(Consumer<T> consumer) {
    consumer.accept(value);
  }

  public boolean test(Predicate<T> tester) {
    return tester.test(value);
  }

  public static <T> Monad<T> monad(T t) {
    return new Monad<T>(t);
  }
}
