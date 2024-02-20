package com.example.springexample.services;

import com.example.springexample.dto.SubscriptionDto;
import com.example.springexample.entity.Author;
import com.example.springexample.entity.Subscription;
import com.example.springexample.repository.AuthorRepository;
import com.example.springexample.repository.SubscriptionRepository;
import com.example.springexample.services.lib.CRUDService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;


@Service
public class SubscriptionCRUDService implements CRUDService<SubscriptionDto> {

    private final SubscriptionRepository repository;
    private final AuthorRepository authorRepository;

    @Autowired
    public SubscriptionCRUDService(SubscriptionRepository subscriptionRepository, AuthorRepository authorRepository) {
        this.repository = subscriptionRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public SubscriptionDto getById(Integer id) {
        Subscription subscription = repository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return mapToDto(subscription);
    }

    public List<SubscriptionDto> getTop(Integer item, Integer count) {
        Pageable top10 = PageRequest.of(0, count, Sort.by("id").descending());
        Author author = authorRepository.findById(item).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        return repository.findByAuthorOrderByIdDesc(author, top10).stream()
                .map(SubscriptionCRUDService::mapToDto)
                .toList();
    }

    @Override
    public void create(SubscriptionDto item) {
        item.setId(null);
        Subscription subscription = mapToEntity(item);
        repository.save(subscription);
    }

    @Override
    public void update(SubscriptionDto item) {
        if (!repository.existsById(item.getId())) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Subscription subscription = mapToEntity(item);
    }

    @Override
    public void delete(Integer id) {
        if (!repository.existsById(id)) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        repository.deleteById(id);
    }

    static SubscriptionDto mapToDto(Subscription entity) {
        return SubscriptionDto.builder()
                .id(entity.getId())
                .author(entity.getAuthor().getId())
                .subscription(entity.getSubscription().getId())
                .build();
    }

    Subscription mapToEntity(SubscriptionDto dto) {
        return Subscription.builder()
                .id(dto.getId())
                .author(authorRepository.findById(dto.getAuthor()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .subscription(authorRepository.findById(dto.getSubscription()).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND)))
                .build();
    }
}
