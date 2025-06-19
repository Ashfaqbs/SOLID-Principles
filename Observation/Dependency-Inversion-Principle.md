

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


### Real Scenario: Email & SMS Notification Service

---

👉 We are building a **Order App with Springboot**
👉 When order is placed → notify user by:

* Email
* SMS
* (In future: WhatsApp, Push Notification)

---

###  BAD DESIGN (Tight Coupling — violates DIP)

```java
@Service
public class OrderService {

    private final EmailService emailService = new EmailService();
    private final SmsService smsService = new SmsService();

    public void placeOrder() {
        // business logic for placing order

        emailService.sendEmail("Order placed!");
        smsService.sendSms("Order placed!");
    }
}
```

Problems:
Hardcoded dependency → Cannot test easily
Cannot switch EmailService implementation
If we add WhatsAppService → must modify OrderService → Violates OCP + DIP

---

###  GOOD DESIGN (DIP applied)

---

**Step 1 — Create abstraction (interface):**

```java
public interface NotificationService {
    void sendNotification(String message);
}
```

**Step 2 — Implement concrete classes:**

```java
@Service
public class EmailNotificationService implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending EMAIL: " + message);
    }
}

@Service
public class SmsNotificationService implements NotificationService {
    @Override
    public void sendNotification(String message) {
        System.out.println("Sending SMS: " + message);
    }
}
```

**Step 3 — Use interface in OrderService (DIP):**

```java
@Service
public class OrderService {

    private final NotificationService notificationService;

    // Constructor injection (Spring injects Email or SMS service here!)
    public OrderService(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    public void placeOrder() {
        // business logic for placing order

        notificationService.sendNotification("Order placed!");
    }
}
```

---

### What happens now?

 OrderService depends on **NotificationService interface** (abstraction)
 Loosely coupled — no direct dependency on Email or SMS
 Want to add WhatsAppNotificationService? → Just implement NotificationService
 No change in OrderService → Follows DIP + OCP

---

### BONUS — How Spring helps DIP (Auto DI):

```java
@Configuration
public class AppConfig {

    @Bean
    public NotificationService notificationService() {
        return new EmailNotificationService();  // can easily switch to SMS or WhatsApp
    }
}
```

Or using `@Primary` in Service — Spring will inject the right implementation.

---

### Summary:

**Dependency Inversion Principle = Depend on abstractions, not concrete classes**

→ In Spring Boot:

* Use `interface` for services
* Inject interface into higher-level services
* Use `@Service` or `@Component` with Spring DI
