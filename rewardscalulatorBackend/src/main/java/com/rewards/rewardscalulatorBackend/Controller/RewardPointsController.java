package com.rewards.rewardscalulatorBackend.Controller;
import com.rewards.rewardscalulatorBackend.Services.RewardPointsCalculatorService;
import com.rewards.rewardscalulatorBackend.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/transactions")
@CrossOrigin("*")
public class RewardPointsController {

    @Autowired
    private RewardPointsCalculatorService service;


    @GetMapping("/")
    public List<Transaction> getAllTransactions() {
        return service.getAllTransactions();
    }

    @GetMapping("/{customerId}")
    public List<Transaction> getTransactionsByCustomerId(@PathVariable Integer customerId) {
        return service.getTransactionsByCustomerId(customerId);
    }

    @GetMapping("/{customerId}/reward-points")
    public Map<String, Integer> calculateRewardPointsByMonth(@PathVariable Integer customerId) {
        return service.calculateRewardPointsByMonth(customerId);
    }

    @GetMapping("/{customerId}/reward-points/total")
    public int calculateTotalRewardPoints(@PathVariable Integer customerId) {
        return service.calculateTotalRewardPoints(customerId);
    }
}