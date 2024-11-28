package org.practice.simplehomeorderservice.service;



import org.practice.simplehomeorderservice.entities.Customer;
import org.practice.simplehomeorderservice.entities.Order;
import org.practice.simplehomeorderservice.entities.Suggestions;
import org.practice.simplehomeorderservice.exception.SuggestionOperationException;

import java.util.List;

public interface SuggestionsService {
    void add(Suggestions suggestion) throws SuggestionOperationException;

    void update(Suggestions suggestion) throws SuggestionOperationException;

    void delete(Suggestions suggestion) throws SuggestionOperationException;

    Suggestions findById(int id) throws SuggestionOperationException;

    List<Suggestions> findAll() throws SuggestionOperationException;

    List<Suggestions> findByOrder(Order order) throws SuggestionOperationException;

    List<Suggestions> findByOrderOrderBySpecialistScoreDesc(Order order) throws SuggestionOperationException;

    List<Suggestions> findByOrderOrderBySuggestedPriceDesc(Order order) throws SuggestionOperationException;

    Suggestions findSuggestionsByNameOfOrderAndSpecialist(String nameOfOrder, String specialistFirstName, String specialistLastName) throws SuggestionOperationException;

    Suggestions findSuggestionByCustomerAndNameOfOrder(Customer customer, String nameOfOrder) throws SuggestionOperationException;

}
