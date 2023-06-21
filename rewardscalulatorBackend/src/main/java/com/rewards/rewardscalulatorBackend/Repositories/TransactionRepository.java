package com.rewards.rewardscalulatorBackend.Repositories;
import com.rewards.rewardscalulatorBackend.entities.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TransactionRepository extends JpaRepository<Transaction, Integer> {
    public List<Transaction> findAllByCustomerId(Integer customerId);
}