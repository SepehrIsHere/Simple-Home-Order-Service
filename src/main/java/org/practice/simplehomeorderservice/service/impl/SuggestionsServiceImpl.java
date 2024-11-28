package org.practice.simplehomeorderservice.service.impl;


import lombok.RequiredArgsConstructor;
import org.practice.simplehomeorderservice.entities.Customer;
import org.practice.simplehomeorderservice.entities.Order;
import org.practice.simplehomeorderservice.entities.Specialist;
import org.practice.simplehomeorderservice.entities.Suggestions;
import org.practice.simplehomeorderservice.exception.SuggestionOperationException;
import org.practice.simplehomeorderservice.repository.SpecialistRepository;
import org.practice.simplehomeorderservice.repository.SuggestionsRepository;
import org.practice.simplehomeorderservice.service.OrderService;
import org.practice.simplehomeorderservice.service.SpecialistService;
import org.practice.simplehomeorderservice.service.SuggestionsService;
import org.practice.simplehomeorderservice.util.MapperUtil;
import org.practice.simplehomeorderservice.util.ValidationUtil;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SuggestionsServiceImpl implements SuggestionsService {
    private final SuggestionsRepository suggestionsRepository;
    private final SpecialistService specialistService;
    private final OrderService orderService;
    private final ValidationUtil validationUtil;
    private final MapperUtil mapperUtil;
    private final SpecialistRepository specialistRepository;

    @Override
    public void add(Suggestions suggestion) throws SuggestionOperationException {
        try {
            if (validationUtil.isValid(suggestion) &&
                    !suggestion.getSuggestedPrice().equals(suggestion.getOrder().getSubTask().getBasePrice())) {
                suggestionsRepository.save(suggestion);
            } else {
                validationUtil.displayViolations(suggestion);
            }
        } catch (Exception e) {
            throw new SuggestionOperationException("An error occured while adding suggestion", e);
        }
    }

    @Override
    public void update(Suggestions suggestion) throws SuggestionOperationException {
        try {
            suggestionsRepository.save(suggestion);
        } catch (Exception e) {
            throw new SuggestionOperationException("An error occured while updating suggestion", e);
        }
    }

    @Override
    public void delete(Suggestions suggestion) throws SuggestionOperationException {
        try {
            suggestionsRepository.delete(suggestion);
        } catch (Exception e) {
            throw new SuggestionOperationException("An error occured while deleting suggestion", e);
        }
    }

    @Override
    public Suggestions findById(int id) throws SuggestionOperationException {
        try {
            return suggestionsRepository.findSuggestionsById(id);
        } catch (Exception e) {
            throw new SuggestionOperationException("An error occured while finding suggestion", e);
        }
    }

    @Override
    public List<Suggestions> findAll() throws SuggestionOperationException {
        try {
            return suggestionsRepository.findAll();
        } catch (Exception e) {
            throw new SuggestionOperationException("An error occured while finding all suggestions ", e);
        }
    }

    @Override
    public List<Suggestions> findByOrder(Order order) throws SuggestionOperationException {
        try {
            return suggestionsRepository.findByOrder(order);
        } catch (Exception e) {
            throw new SuggestionOperationException("An error occured while finding sugesstions by order ", e);
        }
    }

    @Override
    public List<Suggestions> findByOrderOrderBySpecialistScoreDesc(Order order) throws SuggestionOperationException {
        try {
            return suggestionsRepository.findByOrderOrderBySpecialistScoreDesc(order);
        } catch (Exception e) {
            throw new SuggestionOperationException("Did not find any related suggestion",e);
        }
    }

    @Override
    public List<Suggestions> findByOrderOrderBySuggestedPriceDesc(Order order) throws SuggestionOperationException {
        try {
            return suggestionsRepository.findByOrderOrderBySuggestedPriceDesc(order);
        } catch (Exception e) {
            throw new SuggestionOperationException("Did not find any related suggestion",e);
        }
    }

    @Override
    public Suggestions findSuggestionsByNameOfOrderAndSpecialist(String nameOfOrder, String specialistFirstName,String specialistLastName) throws SuggestionOperationException {
        try{
            Specialist specialist = specialistService.findByFirstNameAndLastName(specialistFirstName,specialistLastName);
            return suggestionsRepository.findSuggestionsByNameOfOrderAndSpecialist(nameOfOrder, specialist);
        }catch (Exception e){
            throw new SuggestionOperationException("An error occured while finding specialist",e);
        }
    }

    @Override
    public Suggestions findSuggestionByCustomerAndNameOfOrder(Customer customer, String nameOfOrder) throws SuggestionOperationException {
        try{
            return suggestionsRepository.findSuggestionByCustomerAndNameOfOrder(customer,nameOfOrder);
        }catch (Exception e){
            throw new SuggestionOperationException("An error occured while trying to find suggestion",e);
        }
    }

}
