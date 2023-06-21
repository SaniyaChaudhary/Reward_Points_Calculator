package com.rewards.rewardscalulatorBackend.Services.Implementation;
import com.rewards.rewardscalulatorBackend.Repositories.TransactionRepository;
import com.rewards.rewardscalulatorBackend.Services.RewardPointsCalculatorService;
import com.rewards.rewardscalulatorBackend.entities.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RewardPointsCalculationServiceImpl implements RewardPointsCalculatorService {



   @Autowired
   private TransactionRepository transactionRepository;


        @Override
        public List<Transaction> getAllTransactions() {

            return transactionRepository.findAll();

        }

        @Override
        public List<Transaction> getTransactionsByCustomerId(Integer customerId) {

            return transactionRepository.findAllByCustomerId(customerId);
        }

        private int calculateRewardPoints(double amount) {

          int rewardPoints = 0;

          if (amount > 100) {

            rewardPoints += (int) ((amount - 100) * 2);
            amount = 100;

            }

          if (amount > 50) {
            rewardPoints += (int) (amount - 50);

           }

        return rewardPoints;

        }

        @Override
        public Map<String, Integer> calculateRewardPointsByMonth(Integer customerId) {

            List<Transaction> transactions = transactionRepository.findAllByCustomerId(customerId);

            Map<String, Integer> rewardPointsByMonth = new HashMap<>();

            for (Transaction transaction : transactions) {
                String month = transaction.getTransactionDate().getMonth().name();
                int rewardPoints = calculateRewardPoints(transaction.getAmount());
                rewardPointsByMonth.put(month, rewardPointsByMonth.getOrDefault(month, 0) + rewardPoints);
            }

            return rewardPointsByMonth;
        }

        @Override
        public int calculateTotalRewardPoints(Integer customerId) {

            List<Transaction> transactions = transactionRepository.findAllByCustomerId(customerId);

            return transactions.stream()
                    .mapToInt(transaction -> calculateRewardPoints(transaction.getAmount()))
                    .sum();
        }


    }