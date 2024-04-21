package com.assignment.bankingservice.controller;

import com.assignment.bankingservice.dto.CustomerInfomationDTO;
import com.assignment.bankingservice.exception.CustomServiceException;
import com.assignment.bankingservice.service.BankingService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.math.BigDecimal;

/**
 * Unit tests for the BankingController class.
 */
@WebMvcTest(BankingController.class)
public class BankingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BankingService bankingService;

    /**
     * Tests the successful creation of a new account via the POST /api/accounts endpoint.
     * @throws Exception
     */
    @Test
    public void testWhenAccountCreationIsSuccessful() throws Exception {
        Long customerId = 1L;
        BigDecimal initialCredit = BigDecimal.valueOf(100);
        String expectedResponse = "Account created successfully";

        Mockito.when(bankingService.createAccount(customerId, initialCredit)).thenReturn(expectedResponse);

        mockMvc.perform(MockMvcRequestBuilders.post("/api/accounts")
                .param("customerId", customerId.toString())
                .param("initialCredit", initialCredit.toString()))
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.content().string(expectedResponse));
    }

    /**
     * Tests if Customer ID is Null.
     * @throws Exception
     */
    @Test
    public void testWhenCustomerIdIsNull() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/accounts")
                .param("initialCredit", "100"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("customerId parameter is missing"));
    }

    /**
     * Tests if Initial Credit is null.
     * @throws Exception
     */
    @Test
    public void testWhenInitialCreditIsNull() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/accounts")
                .param("customerId", "1"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.content().string("initialCredit parameter is missing"));
    }

    /**
     * Test if existing customer id is not found.
     * @throws Exception
     */
    @Test
    public void testWhenExcistingCustomerIdIsNotFound() throws Exception {
        Long customerId = 2L;
        BigDecimal initialCredit = BigDecimal.valueOf(100);

        String errorMessage = "Customer not found with ID: " + customerId;

        Mockito.when(bankingService.createAccount(customerId, initialCredit)).thenThrow(new CustomServiceException(errorMessage));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/accounts")
                .param("customerId", customerId.toString())
                .param("initialCredit", initialCredit.toString()))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string(errorMessage));
    }

    /**
     * Test when any unexpected error occures.
     * @throws Exception
     */
    @Test
    public void testWhenUnexpectedErrorOccurs() throws Exception {
        Long customerId = 3L;
        BigDecimal initialCredit = BigDecimal.valueOf(100);
        String errorMessage = "Unexpected error occurred.";

        Mockito.when(bankingService.createAccount(customerId, initialCredit)).thenThrow(new RuntimeException());

        mockMvc.perform(MockMvcRequestBuilders.post("/api/accounts")
                .param("customerId", customerId.toString())
                .param("initialCredit", initialCredit.toString()))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().string(errorMessage));
    }

    /**
     * Tests the GetCustomerInformation when customer id is not found via the POST /api/accounts/customer-info.
     * @throws Exception
     */
    @Test
    public void testGetCustomerInfoWhenCustomerIdIsNotFound() throws Exception {
        Long customerId = 4L;
        String errorMessage = "Customer not found with ID: " + customerId;

        Mockito.when(bankingService.getCustomerInfo(customerId)).thenThrow(new CustomServiceException(errorMessage));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/accounts/customer-info")
                .param("customerId", customerId.toString()))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.content().string(errorMessage));
    }

    /**
     * Test getCustomerInfo method when unexpected error occurs.
     * @throws Exception
     */
    @Test
    public void testGetCustomerInfoWhenUnexpectedErrorOccurs() throws Exception {
        Long customerId = 5L;
        String errorMessage = "Unexpected error occurred.";

        Mockito.when(bankingService.getCustomerInfo(customerId)).thenThrow(new RuntimeException());

        mockMvc.perform(MockMvcRequestBuilders.get("/api/accounts/customer-info")
                .param("customerId", customerId.toString()))
                .andExpect(MockMvcResultMatchers.status().isInternalServerError())
                .andExpect(MockMvcResultMatchers.content().string(errorMessage));
    }

    /**
     * Test getcustomerInfo when data is retrieve.
     * @throws Exception
     */
    @Test
    public void testGetCustomerInfoWhenCustomerInfoIsRetrieved() throws Exception {
        Long customerId = 6L;
        CustomerInfomationDTO customerInfoDTO = new CustomerInfomationDTO("John", "Doe", BigDecimal.valueOf(200), null);

        Mockito.when(bankingService.getCustomerInfo(customerId)).thenReturn(customerInfoDTO);

        mockMvc.perform(MockMvcRequestBuilders.get("/api/accounts/customer-info")
                .param("customerId", customerId.toString()))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().contentType(MediaType.APPLICATION_JSON));
    }
}
