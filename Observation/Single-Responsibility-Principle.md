
###  **S** — Single Responsibility Principle (SRP)

**Definition:**

> *"A class should have only one reason to change."* — Robert C. Martin (Uncle Bob)

---

###  What does it *really* mean?

* **“Single responsibility”** doesn’t just mean “only one function/method.”
* It means **a class should do one thing** — or more precisely — **have one and only one *reason* to change.**

---

### 🔍 Think in terms of "Responsibility"

A **responsibility** is a **stakeholder’s concern** or a **reason to change**.
For example:

```java
class InvoicePrinter {
    public void calculateTotal() {...}   // Business logic
    public void printInvoice() {...}     // Printing logic
}
```

This class has **two responsibilities**:

1. **Business logic** — calculating totals
2. **Presentation** — formatting/printing the invoice

Now imagine:

* The **business rules** change → We need to update `calculateTotal()`
* The **print format** changes → We need to update `printInvoice()`

#### This class now has **two reasons to change** — which violates SRP.

---

### SRP Fix (Separation of Concerns)

Split the responsibilities into different classes:

```java
class Invoice {
    public double calculateTotal() {...}
}

class InvoicePrinter {
    public void print(Invoice invoice) {...}
}
```

Now:

* If **business rules** change → Modify `Invoice`
* If **printing format** changes → Modify `InvoicePrinter`

Each class now has **only one reason to change.**

---

###  Summary

> “SRP means a class should have **one functionality** (responsibility), so that **any change** in that functionality becomes the **only reason** for the class to change.”
