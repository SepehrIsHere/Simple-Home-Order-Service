package org.practice.simplehomeorderservice.service;


import org.practice.simplehomeorderservice.dto.OrderDto;
import org.practice.simplehomeorderservice.dto.SuggestionDto;
import org.practice.simplehomeorderservice.exception.InvalidFieldValueException;
import org.practice.simplehomeorderservice.exception.OrderOperationException;
import org.practice.simplehomeorderservice.exception.SuggestionOperationException;

import java.util.List;

public interface CustomerSuggestionService {
    List<SuggestionDto> displaySuggestionsForOrder(OrderDto orderDto);

    List<SuggestionDto> displaySuggestionBaseOnSpecialistRating(OrderDto orderDto);

    List<SuggestionDto> displaySuggestionBasedOnSuggestedPrice(OrderDto orderDto);

    void changeStatusToFinished(SuggestionDto suggestionDto) throws InvalidFieldValueException, OrderOperationException, SuggestionOperationException;

    void changeStatusToPaid(SuggestionDto suggestionDto) throws SuggestionOperationException, OrderOperationException;

}
