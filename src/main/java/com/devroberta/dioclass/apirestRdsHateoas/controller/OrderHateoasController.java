package com.devroberta.dioclass.apirestRdsHateoas.controller;

import com.devroberta.dioclass.apirestRdsHateoas.entity.OrderHateoas;
import com.devroberta.dioclass.apirestRdsHateoas.entity.Status;
import com.devroberta.dioclass.apirestRdsHateoas.exception.OrderHateoasNotFoundException;
import com.devroberta.dioclass.apirestRdsHateoas.repository.OrderHateosRepository;
import org.springframework.hateoas.Link;
import org.springframework.hateoas.MediaTypes;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
@RequestMapping("/order")
public class OrderHateoasController {

    private final OrderHateosRepository orderHateosRepository;


    public OrderHateoasController(OrderHateosRepository orderHateosRepository) {
        this.orderHateosRepository = orderHateosRepository;
    }

    @GetMapping
    public ResponseEntity<List<OrderHateoas>> findAll() {
        long idOrder;
        Link linkUri;
        List<OrderHateoas> orderList = orderHateosRepository.findAll();
        if (orderList.isEmpty())
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        for (OrderHateoas orderHateoas:orderList){
            idOrder = orderHateoas.getId();
            linkUri = linkTo(methodOn(OrderHateoasController.class).findById(idOrder)).withSelfRel();
            orderHateoas.add(linkUri);
            linkUri = linkTo(methodOn(OrderHateoasController.class).findAll()).withRel("Custumer order list");
            orderHateoas.add(linkUri);
        }
        return new ResponseEntity<List<OrderHateoas>>(orderList, HttpStatus.OK);
    }

    @GetMapping("/{id}")
    ResponseEntity<OrderHateoas> findById(@PathVariable Long id) {
        Optional<OrderHateoas> orderPointer = orderHateosRepository.findById(id);
        if (orderPointer.isPresent()) {
            OrderHateoas order = orderPointer.get();
            order.add(linkTo(methodOn(OrderHateoasController.class).findAll()).withRel("All orders"));
            return new ResponseEntity<>(order, HttpStatus.OK);
        } else return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping
    OrderHateoas create(@RequestBody OrderHateoas newOrder) {
        return orderHateosRepository.save(newOrder);
    }

    @PutMapping("/{id}")
    OrderHateoas update(@PathVariable Long id, @RequestBody OrderHateoas newOrder) {
        return orderHateosRepository.findById(id).map(order -> {
            order.setDescription(newOrder.getDescription());
            order.setStatus(newOrder.getStatus());
            return orderHateosRepository.save(newOrder);
        }).orElseGet(() -> {
            newOrder.setId(id);
            return orderHateosRepository.save(newOrder);
        });
    }

    @DeleteMapping("/{id}")
    void deleteById(@PathVariable long id) {
        orderHateosRepository.deleteById(id);
    }

    @PutMapping("/{id}/cancel")
    public ResponseEntity<?> cancelOrderById(@PathVariable long id){
        OrderHateoas cancelledOrder = orderHateosRepository.findById(id).orElseThrow(
                () -> new OrderHateoasNotFoundException(id));
        if (cancelledOrder.getStatus() == Status.IN_PROGRESS){
            cancelledOrder.setStatus(Status.CANCELLED);
            cancelledOrder.add(linkTo(methodOn(OrderHateoasController.class).findById(id)).withSelfRel());
            cancelledOrder.add(linkTo(methodOn(OrderHateoasController.class).findAll())
                    .withRel("Complete order list"));
            orderHateosRepository.save(cancelledOrder);
            return new ResponseEntity<>(cancelledOrder,HttpStatus.OK);
        }

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED) //
                .header(HttpHeaders.CONTENT_TYPE,
                        MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
                .body("You can't complete the task, the order has a " +
                        cancelledOrder.getStatus() + " status");
    }

    @PutMapping("/{id}/complete")
    public ResponseEntity<?> completeOrderById(@PathVariable long id){
        OrderHateoas cancelledOrder = orderHateosRepository.findById(id).orElseThrow(
                () -> new OrderHateoasNotFoundException(id));
        if (cancelledOrder.getStatus() == Status.IN_PROGRESS){
            cancelledOrder.setStatus(Status.COMPLETED);
            cancelledOrder.add(linkTo(methodOn(OrderHateoasController.class).findById(id)).withSelfRel());
            cancelledOrder.add(linkTo(methodOn(OrderHateoasController.class).findAll())
                    .withRel("Complete order list"));
            orderHateosRepository.save(cancelledOrder);
            return new ResponseEntity<>(cancelledOrder,HttpStatus.OK);
        }

        return ResponseEntity.status(HttpStatus.METHOD_NOT_ALLOWED) //
                .header(HttpHeaders.CONTENT_TYPE,
                        MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
                .body("You can't complete the task, the order has a " +
                        cancelledOrder.getStatus() + " status");
    }
}
