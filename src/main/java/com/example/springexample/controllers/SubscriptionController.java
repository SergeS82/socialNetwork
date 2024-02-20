package com.example.springexample.controllers;

import com.example.springexample.dto.SubscriptionDto;
import com.example.springexample.services.SubscriptionCRUDService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/subscription")
public class SubscriptionController {
    private final SubscriptionCRUDService service;
    @PostMapping
    void create(@RequestBody SubscriptionDto subscriptionDto) {
        service.create(subscriptionDto);
    }
    @GetMapping("/{id}")
    SubscriptionDto getById(@PathVariable Integer id) {
         return service.getById(id);
    }
    @GetMapping()
    List<SubscriptionDto> getTopByAuthor(@RequestParam Integer author, @RequestParam(required = false, defaultValue = "10") Integer count) {
        return service.getTop(author, count) ;
    }
    @DeleteMapping("/{id}")
    void delete(@PathVariable Integer id) {
        service.delete(id);
    }
}
