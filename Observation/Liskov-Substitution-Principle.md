### Analogy:

A **Remote Control** that works for any kind of **TV** — Sony, Samsung, LG.
If the remote says *“Volume Up”*, it should work the same way on any TV.
If suddenly **Sony TV** works fine, but **LG TV** crashes when pressing “Volume Up” — this violates LSP.
→ The remote (parent type) must work with all child TVs (subtypes) properly.

---

### Technical Terms:

* If `class B extends class A`, then in all places where code uses an object of `A`, if we replace `A` with `B`, the program must **still work correctly**.
* `B` should not **narrow or break** the expected behavior of `A`.

---

### What happens if we break LSP:

 If followed → Flexible code, safe inheritance, less bugs
If broken → Surprising behavior, crashes, difficult to test

---

> *“class B is subtype of A, should replace A without breaking program, and B must not narrow down, it should extend A”*

The only addition: **"not narrowing down behavior"** — meaning the methods of B should do what A promised (or more), not less.

Let’s do it in 3 steps:

---

### Example:

Assume a **Spring Boot application** with this requirement:

 App handles **Payments**:

* `CreditCardPayment`
* `PaypalPayment`
* `CashOnDeliveryPayment`

All payments must:

* Validate payment
* Execute payment

---

### BAD Example (violates LSP):

```java
public abstract class Payment {
    public abstract void validate();
    public abstract void execute();
}

public class CreditCardPayment extends Payment {
    @Override
    public void validate() {
        System.out.println("Validating credit card...");
    }

    @Override
    public void execute() {
        System.out.println("Executing credit card payment...");
    }
}

public class CashOnDeliveryPayment extends Payment {
    @Override
    public void validate() {
        throw new UnsupportedOperationException("No validation for Cash on Delivery!"); // this is Narrowing down behavior
    }

    @Override
    public void execute() {
        System.out.println("Processing COD...");
    }
}
```

### Why BAD?

If some service is written as:

```java
@Autowired
Payment payment;

public void processOrder() {
    payment.validate();  //  This will CRASH if payment = CashOnDeliveryPayment
    payment.execute();
}
```

→ Now, replacing `CreditCardPayment` with `CashOnDeliveryPayment` will **break the program**.
This violates **Liskov Substitution Principle**!

---

### GOOD Example (LSP respected):

Fix → Split Interface:

```java
public interface Payment {
    void execute();
}

public interface Validatable {
    void validate();
}

public class CreditCardPayment implements Payment, Validatable {
    @Override
    public void validate() {
        System.out.println("Validating credit card...");
    }

    @Override
    public void execute() {
        System.out.println("Executing credit card payment...");
    }
}

public class CashOnDeliveryPayment implements Payment {
    @Override
    public void execute() {
        System.out.println("Processing COD...");
    }
}
```

Then in the service:

```java
@Autowired
Payment payment;

public void processOrder() {
    if (payment instanceof Validatable validatablePayment) {
        validatablePayment.validate();  // No risk of crash
    }

    payment.execute();
}
```

---

### Summary for Backend / Spring Boot:

*How LSP helps in real world:*

✅ Payment services
✅ Notification services
✅ File upload services (S3, local disk, FTP)
✅ Report generators (PDF, CSV, XLS)
✅ User authentication (Google, GitHub, EmailPassword)

---

**When LSP is broken** → We write "if-else" checks all over the code
**When LSP is followed** → Code works seamlessly, testable, no surprises

---