package org.practice.simplehomeorderservice.service;


import org.practice.simplehomeorderservice.dto.SuggestionDto;
import org.practice.simplehomeorderservice.exception.InvalidFieldValueException;
import org.practice.simplehomeorderservice.exception.SpecialistOperationException;
import org.practice.simplehomeorderservice.exception.SubTaskOperationException;
import org.practice.simplehomeorderservice.exception.SuggestionOperationException;

public interface SpecialistSuggestionService {
    SuggestionDto createSuggestionForOrder(SuggestionDto suggestionDto) throws SuggestionOperationException, InvalidFieldValueException, SpecialistOperationException, SubTaskOperationException;

}
