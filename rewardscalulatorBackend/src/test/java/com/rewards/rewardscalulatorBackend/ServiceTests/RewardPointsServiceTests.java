package com.rewards.rewardscalulatorBackend.ServiceTests;
import com.rewards.rewardscalulatorBackend.Repositories.TransactionRepository;
import com.rewards.rewardscalulatorBackend.Services.RewardPointsCalculatorService;
import com.rewards.rewardscalulatorBackend.entities.Transaction;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import java.time.LocalDate;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@SpringBootTest
public class RewardPointsServiceTests {

    @Autowired
    private RewardPointsCalculatorService service;

    @MockBean
    private TransactionRepository transactionRepository;





    @Test
    void getAllTransactionsTest() {

        //preparing test data;
        Integer customerId1 = 1;
        Integer customerId2=2;

        List<Transaction> expectedTransactions = new ArrayList<>();

        Transaction transaction1 = new Transaction(1, customerId1, LocalDate.of(2023, 6, 1), 120.0);
        Transaction transaction2 = new Transaction(2, customerId1, LocalDate.of(2023, 6, 15), 75.0);
        Transaction transaction3 = new Transaction(3, customerId2, LocalDate.of(2023, 5, 1), 50.0);
        Transaction transaction4 = new Transaction(3, customerId2, LocalDate.of(2023, 5, 30), 100);
        expectedTransactions.add(transaction1);
        expectedTransactions.add(transaction2);
        expectedTransactions.add(transaction3);
        expectedTransactions.add(transaction4);

        // Mocking the behavior of the transactionRepository
        when(transactionRepository.findAll()).thenReturn( expectedTransactions);

        //calling and storing our actual transaction service
        List<Transaction> actualTransactions = service.getAllTransactions();

        //Asserting the result
        assertEquals( expectedTransactions, actualTransactions);

    }

    @Test
    public void GetTransactionsByCustomerIdTest() {

        LocalDate now = LocalDate.now();
        Transaction transaction1 = new Transaction();
        transaction1.setTransactionId(1022);
        transaction1.setCustomerId(123);
        transaction1.setTransactionDate(now);


        Transaction transaction2 = new Transaction();
        transaction2.setTransactionId(1023);
        transaction2.setCustomerId(123);
        transaction1.setTransactionDate(now);


        List<Transaction> expectedTransactions = Arrays.asList(transaction1, transaction2);

        //mocking the expected transactions
        when(transactionRepository.findAllByCustomerId(123))
                .thenReturn(expectedTransactions);


        // Calling the method in rewardPointsCalculatorService
        List<Transaction> actualTransactions = service.getTransactionsByCustomerId(123);

        // Asserting the results
        assertEquals(expectedTransactions, actualTransactions);

    }

    @Test
    void testCalculateRewardPointsByMonth() {

        // Prepare test data
        Integer customerId = 1;
        List<Transaction> mockTransactions = new ArrayList<>();

        Transaction transaction1 = new Transaction(1, customerId, LocalDate.of(2023, 6, 1), 120.0);
        Transaction transaction2 = new Transaction(2, customerId, LocalDate.of(2023, 6, 15), 75.0);
        Transaction transaction3 = new Transaction(3, customerId, LocalDate.of(2023, 5, 1), 50.0);
        Transaction transaction4 = new Transaction(3, customerId, LocalDate.of(2023, 5, 30), 100);
        mockTransactions.add(transaction1);
        mockTransactions.add(transaction2);
        mockTransactions.add(transaction3);
        mockTransactions.add(transaction4);

        // Mocking the behavior of the transactionRepository
        when(transactionRepository.findAllByCustomerId(customerId)).thenReturn(mockTransactions);

        // Calling the method under test
        Map<String, Integer> result = service.calculateRewardPointsByMonth(customerId);

        // Assertions
        assertEquals(115, result.get("JUNE")); // (120 purchase = 2x20 + 1x50 = 90 points,75 purchase = 1*25 = 25 points, total in JUNE=90+25=115)
        assertEquals(50, result.get("MAY")); // 50 purchase = 0 points, 100 purchase = 50*1 = 50 points in MAY)
    }



    @Test
    void testCalculateTotalRewardPoints() {
        // Prepare test data
        Integer customerId = 1;
        List<Transaction> mockTransactions = new ArrayList<>();
        LocalDate now = LocalDate.now();

        Transaction transaction1 = new Transaction(1, customerId, now, 120.0);
        Transaction transaction2 = new Transaction(2, customerId, now, 80.0);
        mockTransactions.add(transaction1);
        mockTransactions.add(transaction2);

        // Mock the behavior of the transactionRepository
        when(transactionRepository.findAllByCustomerId(customerId)).thenReturn(mockTransactions);

        // Call the method under test
        int result = service.calculateTotalRewardPoints(customerId);

        // Assertion
        assertEquals(120, result);
    }


    }


