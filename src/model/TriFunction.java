package model;

/**
 * This is a functional interface representing a tri-function that takes three arguments
 * of types A, B, and C, and produces a result of type R.
 *
 * @param <A> The type of the first argument.
 * @param <B> The type of the second argument.
 * @param <C> The type of the third argument.
 * @param <R> The type of the result.
 */
@FunctionalInterface
interface TriFunction<A, B, C, R> {
  /**
   * Applies this tri-function to the given arguments.
   *
   * @param a The first argument.
   * @param b The second argument.
   * @param c The third argument.
   * @return The result of applying the tri-function to the given arguments.
   */
  R apply(A a, B b, C c);
}
