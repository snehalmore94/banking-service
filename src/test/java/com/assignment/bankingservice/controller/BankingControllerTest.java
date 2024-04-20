//package com.assignment.bankingservice.controller;
//
//import com.assignment.bankingservice.service.BankingService;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoExtension;
//import org.springframework.http.MediaType;
//import org.springframework.test.web.servlet.MockMvc;
//import org.springframework.test.web.servlet.setup.MockMvcBuilders;
//
//import java.math.BigDecimal;
//
//import static org.mockito.ArgumentMatchers.any;
//import static org.mockito.Mockito.doNothing;
//import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
//import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
//
//@ExtendWith(MockitoExtension.class)
//public class BankingControllerTest {
//
//    private MockMvc mockMvc;
//
//    @Mock
//    private BankingService bankingService;
//
//    @InjectMocks
//    private BankingController bankingController;
//
//    @BeforeEach
//    public void setup() {
//        mockMvc = MockMvcBuilders.standaloneSetup(bankingController).build();
//    }
//
//    @Test
//    public void createAccount_WithValidParameters_ShouldReturnCreated() throws Exception {
//        // Arrange
//        Long customerId = 1L;
//        BigDecimal initialCredit = new BigDecimal("100.00");
//        doNothing().when(bankingService).createAccount(any(Long.class), any(BigDecimal.class));
//
//        // Act & Assert
//        mockMvc.perform(post("/api/accounts")
//                        .param("customerId", customerId.toString())
//                        .param("initialCredit", initialCredit.toString())
//                        .contentType(MediaType.APPLICATION_JSON))
//                .andExpect(status().isCreated());
//    }
//}