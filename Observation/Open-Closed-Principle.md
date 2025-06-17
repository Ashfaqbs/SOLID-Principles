##  O — Open/Closed Principle (OCP)

> **“Software entities (classes, modules, functions, etc.) should be open for extension but closed for modification.”**
> — Bertrand Meyer

###  What it means:

* We **shouldn’t have to modify existing code** to add new behavior.
* Instead, we should be able to **extend** it (typically via **inheritance** or **composition** or **strategy** pattern).

---

## example:

> I have a class `Invoice` with a method `saveToDB()`
> If tomorrow I add `saveToFile()`, I'm modifying the same class — which **violates OCP**.

### 🎯 Why?

Because adding `saveToFile()` means:

* We're modifying existing code
* Risk of breaking something that’s already tested
* Harder to maintain/test over time

---

## Better Approach

Let’s refactor using **OCP** — by introducing an interface:

```java
public interface InvoiceSaver {
    void save(Invoice invoice);
}
```

Then:

### DB Save Implementation

```java
public class DBInvoiceSaver implements InvoiceSaver {
    public void save(Invoice invoice) {
        // logic to save to DB
    }
}
```

### File Save Implementation

```java
public class FileInvoiceSaver implements InvoiceSaver {
    public void save(Invoice invoice) {
        // logic to save to file
    }
}
```

### Tomorrow — Save to S3?

Just create:

```java
public class S3InvoiceSaver implements InvoiceSaver {
    public void save(Invoice invoice) {
        // logic to save to S3
    }
}
```

Now our original `Invoice` or main workflow **doesn’t change at all** — we just plug in a new saver.

---

##  Benefits:

* **Testability** — each implementation can be unit tested independently
* **Extensibility** — we can add new ways to save invoices without touching old code
* **No regression risk** — old, tested code remains untouched

---

##  Optional — Strategy Pattern for runtime flexibility

If we want to dynamically switch strategies (e.g., based on config or input), we can inject the right saver at runtime.

```java
public class InvoiceService {
    private final InvoiceSaver saver;

    public InvoiceService(InvoiceSaver saver) {
        this.saver = saver;
    }

    public void saveInvoice(Invoice invoice) {
        saver.save(invoice);
    }
}
```

Now use different implementations as needed:

```java
InvoiceService service = new InvoiceService(new DBInvoiceSaver());
service.saveInvoice(invoice);
```

Or file, or S3, etc.

---

## Summary

> "Don't add new functionality to existing, tested classes — extend them instead."

| ❌ Violation                          | ✅ OCP-Compliant                              |
| ------------------------------------ | -------------------------------------------- |
| Add `saveToFile()` to existing class | Create new class implementing `InvoiceSaver` |
| Modify old code = risk               | Add new class = safe                         |
