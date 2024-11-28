package org.practice.simplehomeorderservice.service;


import org.practice.simplehomeorderservice.dto.ReceiptDto;
import org.practice.simplehomeorderservice.entities.Receipt;

import java.util.List;

public interface ReceiptService {

    void add(Receipt receipt);

    void update(Receipt receipt);

    void delete(Receipt receipt);

    List<ReceiptDto> findAll();

    Receipt findById(int id);

    List<ReceiptDto> findByCustomer(String firstName, String lastName);

    List<ReceiptDto> findBySpecialist(String firstName, String lastName);

    ReceiptDto findByNameOfOrder(String nameOfOrder);

    ReceiptDto createReceipt(String nameOfOrder,String specialistFirstName,String specialistLastName);
}
