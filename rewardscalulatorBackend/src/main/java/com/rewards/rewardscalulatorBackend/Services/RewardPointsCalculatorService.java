package com.rewards.rewardscalulatorBackend.Services;
import com.rewards.rewardscalulatorBackend.entities.Transaction;


import java.util.List;
import java.util.Map;
import java.util.Set;

public interface RewardPointsCalculatorService  {


    List<Transaction> getAllTransactions();
    List<Transaction> getTransactionsByCustomerId(Integer customerId);
    Map<String, Integer> calculateRewardPointsByMonth(Integer customerId);
    int calculateTotalRewardPoints(Integer customerId);





}
