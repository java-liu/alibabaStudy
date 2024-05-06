`Optional.of(T value)`：如果 value 非 null，则创建一个包含该值的 Optional 对象；否则，创建一个空的 Optional 对象。
> [IsPresent.java](IsPresent.java)
> value 不允许 null ,否则会抛出NullPointerException
`Optional.empty()`：创建一个空的 Optional 对象。

`Optional.ofNullable(T value)`：与 `Optional.of(T value)` 类似，但如果 value 为 null，则创建一个空的 Optional 对象。

`Optional.ofNullable(Supplier<T> supplier)`：如果 supplier 提供的值非 null，则创建一个包含该值的 Optional 对象；否则，创建一个空的 Optional 对象。\

`fromNullable(T value)`：静态方法，等同于 Optional.ofNullable(value)。

`from(Supplier<T> supplier)`：静态方法，等同于 Optional.ofNullable(supplier)