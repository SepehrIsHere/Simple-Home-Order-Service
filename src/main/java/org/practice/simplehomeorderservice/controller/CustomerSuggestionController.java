package org.practice.simplehomeorderservice.controller;

import lombok.RequiredArgsConstructor;
import org.practice.simplehomeorderservice.dto.SuggestionDto;
import org.practice.simplehomeorderservice.entities.Order;
import org.practice.simplehomeorderservice.exception.SuggestionOperationException;
import org.practice.simplehomeorderservice.service.CustomerSuggestionService;
import org.practice.simplehomeorderservice.service.OrderService;
import org.practice.simplehomeorderservice.service.SuggestionsService;
import org.practice.simplehomeorderservice.util.MapperUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class CustomerSuggestionController {
    private final CustomerSuggestionService customerSuggestionService;
    private final OrderService orderService;
    private final SuggestionsService suggestionsService;
    private final MapperUtil mapperUtil;

    @GetMapping("customer/suggestions/{nameOfOrder}")
    List<SuggestionDto> displaySuggestionForOrder(@PathVariable String nameOfOrder) throws SuggestionOperationException {
        Order order = orderService.findByNameOfOrder(nameOfOrder);
        return customerSuggestionService.displaySuggestionsForOrder(mapperUtil.convertToDto(order));
    }

    @GetMapping("customer/suggestions/by-specialist-score/{nameOfOrder}")
    List<SuggestionDto> displaySuggestionSpecialistFilter(@PathVariable String nameOfOrder) {
        Order order = orderService.findByNameOfOrder(nameOfOrder);
        return customerSuggestionService.displaySuggestionBaseOnSpecialistRating(mapperUtil.convertToDto(order));
    }

    @GetMapping("customer/suggestions/by-price/{nameOfOrder}")
    @PreAuthorize("hasRole('CUSTOMER')")
    List<SuggestionDto> displaySuggestionPriceFilter(@PathVariable String nameOfOrder) {
        Order order = orderService.findByNameOfOrder(nameOfOrder);
        return customerSuggestionService.displaySuggestionBasedOnSuggestedPrice(mapperUtil.convertToDto(order));
    }
}
