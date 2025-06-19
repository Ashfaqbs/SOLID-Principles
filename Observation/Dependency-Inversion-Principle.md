

**D in SOLID — Dependency Inversion Principle (DIP)**

> "Classes should depend on abstractions (interfaces), not on concrete implementations."

---

Example:

Suppose there is a class `Macbook`, and inside it, two fields:

```java
WiredKeyboard keyboard;
WiredMouse mouse;
```

Now, this creates **tight coupling** between `Macbook` and these specific implementations (`WiredKeyboard`, `WiredMouse`).

To improve the design:

 Create interfaces:

```java
interface Keyboard { ... }
interface Mouse { ... }
```

Let `WiredKeyboard`, `WirelessKeyboard` implement `Keyboard`.
Let `WiredMouse`, `WirelessMouse` implement `Mouse`.

Now in `Macbook`, depend only on **interfaces**:

```java
Keyboard keyboard;
Mouse mouse;
```

At runtime, any type of keyboard or mouse can be injected — **wired** or **wireless**.
This makes the design **flexible** and **loosely coupled**.

---

**Summary**:

DIP helps avoid hard dependencies on specific classes.
It allows plugging different implementations without modifying the dependent class.
This improves maintainability, testability, and flexibility of the code.

---

