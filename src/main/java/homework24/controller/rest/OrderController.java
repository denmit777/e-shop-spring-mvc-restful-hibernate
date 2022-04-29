package homework24.controller.rest;

import homework24.model.Order;
import homework24.service.OrderService;

import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping(value = "/orders")
public class OrderController {

    private final OrderService orderService;

    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public ResponseEntity<Order> save(@Valid @RequestBody Order order) {
        Order savedOrder = orderService.save(order);

        String currentUri = ServletUriComponentsBuilder.fromCurrentRequestUri().toUriString();
        String savedOrderLocation = currentUri + "/" + savedOrder.getId();

        return ResponseEntity.status(CREATED)
                .header(HttpHeaders.LOCATION, savedOrderLocation)
                .body(savedOrder);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable("id") Long id) {
        Order order = orderService.getById(id);

        return ResponseEntity.ok(order);
    }

    @GetMapping(value = "/users/{userId}")
    public ResponseEntity<Order> getByUserId(@PathVariable("userId") Long userId) {
        Order order = orderService.getByUserId(userId);

        return ResponseEntity.ok(order);
    }

    @GetMapping
    public ResponseEntity<List<Order>> getAll() {
        List<Order> orders = orderService.getAll();

        return ResponseEntity.ok(orders);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> update(
            @PathVariable("id") Long id,
            @Valid @RequestBody Order order
    ) {
        Order updatedOrder = orderService.update(id, order);

        return ResponseEntity.ok(updatedOrder);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        orderService.deleteById(id);

        return ResponseEntity.ok().build();
    }
}