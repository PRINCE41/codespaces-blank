package main.java.bugs;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


//@SpringBootApplication
public class PaymentManagementApplication {

    public static void main(String[] args) {
        SpringApplication.run(PaymentManagementApplication.class, args);
    }
}


@RestController
@RequestMapping("/api/payments")
class PaymentController {

    private final PaymentService paymentService;

    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @PostMapping
    public ResponseEntity<PaymentResponse> create(@RequestBody CreatePaymentRequest request) {
        return ResponseEntity.ok(paymentService.create(request));
    }

    @GetMapping
    public ResponseEntity<List<PaymentResponse>> getAll() {
        return ResponseEntity.ok(paymentService.getAll());
    }

    @GetMapping("/summary")
    public ResponseEntity<PaymentSummary> getSummary() {
        return ResponseEntity.ok(paymentService.getSummary());
    }

    @GetMapping("/group-by-status")
    public ResponseEntity<Map<PaymentStatus, List<PaymentResponse>>> groupByStatus() {
        return ResponseEntity.ok(paymentService.groupByStatus());
    }

    @GetMapping("/top-customers")
    public ResponseEntity<List<CustomerAggregate>> topCustomers() {
        return ResponseEntity.ok(paymentService.topCustomers());
    }
}

@Service
class PaymentService {

    private final Map<Long, Payment> store = new ConcurrentHashMap<>();
    private final AtomicLong idGen = new AtomicLong(1);

    public PaymentResponse create(CreatePaymentRequest request) {
        Payment p = new Payment();
        p.setId(idGen.getAndIncrement());
        p.setCustomerName(request.getCustomerName());
        p.setAmount(request.getAmount());
        p.setCategory(request.getCategory());
        p.setStatus(request.getStatus());
        p.setCreatedAt(LocalDateTime.now());

        store.put(p.getId(), p);
        return toResponse(p);
    }

    public List<PaymentResponse> getAll() {
        return store.values()
                .stream()
                .map(this::toResponse)
                .sorted(Comparator.comparing(PaymentResponse::getAmount).reversed())
                .collect(Collectors.toList());
    }

    public PaymentSummary getSummary() {
        List<Payment> payments = new ArrayList<>(store.values());

        BigDecimal total = payments.stream()
                .map(Payment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        BigDecimal avg = payments.stream()
                .map(Payment::getAmount)
                .reduce(BigDecimal.ZERO, BigDecimal::add)
                .divide(BigDecimal.valueOf(payments.size()), 2, RoundingMode.HALF_UP);

        Map<PaymentStatus, Long> statusCount = payments.stream()
                .collect(Collectors.groupingBy(
                        Payment::getStatus,
                        Collectors.counting()
                ));

        Map<String, BigDecimal> categorySum = payments.stream()
                .collect(Collectors.groupingBy(
                        Payment::getCategory,
                        Collectors.mapping(Payment::getAmount,
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))
                ));

        PaymentSummary summary = new PaymentSummary();
        summary.setTotalAmount(total);
        summary.setAverageAmount(avg);
        summary.setStatusCount(statusCount);
        summary.setCategoryTotals(categorySum);

        return summary;
    }

    public Map<PaymentStatus, List<PaymentResponse>> groupByStatus() {
        return store.values()
                .stream()
                .collect(Collectors.groupingBy(
                        Payment::getStatus,
                        Collectors.mapping(this::toResponse, Collectors.toList())
                ));
    }

    public List<CustomerAggregate> topCustomers() {

        return store.values()
                .stream()
                .collect(Collectors.groupingBy(
                        Payment::getCustomerName,
                        Collectors.mapping(Payment::getAmount,
                                Collectors.reducing(BigDecimal.ZERO, BigDecimal::add))
                ))
                .entrySet()
                .stream()
                .map(e -> {
                    CustomerAggregate agg = new CustomerAggregate();
                    agg.setCustomerName(e.getKey());
                    agg.setTotalAmount(e.getValue());
                    return agg;
                })
                .sorted(Comparator.comparing(CustomerAggregate::getTotalAmount).reversed())
                .limit(5)
                .collect(Collectors.toList());
    }

    private PaymentResponse toResponse(Payment p) {
        PaymentResponse r = new PaymentResponse();
        r.setId(p.getId());
        r.setCustomerName(p.getCustomerName());
        r.setAmount(p.getAmount());
        r.setCategory(p.getCategory());
        r.setStatus(p.getStatus());
        r.setCreatedAt(p.getCreatedAt());
        return r;
    }
}

enum PaymentStatus {
    INITIATED,
    SUCCESS,
    FAILED
}

class Payment {
    private Long id;
    private String customerName;
    private BigDecimal amount;
    private String category;
    private PaymentStatus status;
    private LocalDateTime createdAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

class CreatePaymentRequest {
    private String customerName;
    private BigDecimal amount;
    private String category;
    private PaymentStatus status;

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }
}

class PaymentResponse {
    private Long id;
    private String customerName;
    private BigDecimal amount;
    private String category;
    private PaymentStatus status;
    private LocalDateTime createdAt;

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public BigDecimal getAmount() { return amount; }
    public void setAmount(BigDecimal amount) { this.amount = amount; }

    public String getCategory() { return category; }
    public void setCategory(String category) { this.category = category; }

    public PaymentStatus getStatus() { return status; }
    public void setStatus(PaymentStatus status) { this.status = status; }

    public LocalDateTime getCreatedAt() { return createdAt; }
    public void setCreatedAt(LocalDateTime createdAt) { this.createdAt = createdAt; }
}

class PaymentSummary {
    private BigDecimal totalAmount;
    private BigDecimal averageAmount;
    private Map<PaymentStatus, Long> statusCount;
    private Map<String, BigDecimal> categoryTotals;

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }

    public BigDecimal getAverageAmount() { return averageAmount; }
    public void setAverageAmount(BigDecimal averageAmount) { this.averageAmount = averageAmount; }

    public Map<PaymentStatus, Long> getStatusCount() { return statusCount; }
    public void setStatusCount(Map<PaymentStatus, Long> statusCount) { this.statusCount = statusCount; }

    public Map<String, BigDecimal> getCategoryTotals() { return categoryTotals; }
    public void setCategoryTotals(Map<String, BigDecimal> categoryTotals) { this.categoryTotals = categoryTotals; }
}

class CustomerAggregate {
    private String customerName;
    private BigDecimal totalAmount;

    public String getCustomerName() { return customerName; }
    public void setCustomerName(String customerName) { this.customerName = customerName; }

    public BigDecimal getTotalAmount() { return totalAmount; }
    public void setTotalAmount(BigDecimal totalAmount) { this.totalAmount = totalAmount; }
}
