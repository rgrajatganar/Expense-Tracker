package com.tracker.expensetracker.service;

import com.tracker.expensetracker.model.Transaction;
import org.springframework.stereotype.Service;

import java.io.*;
import java.time.Month;
import java.util.*;

@Service
public class TransactionService {
	
	
    private List<Transaction> transactions = new ArrayList<>();

    public void addTransaction(Transaction t) {
        transactions.add(t);
    }

    public List<Transaction> getAll() {
        return transactions;
    }

    public Map<String, Double> getMonthlySummary(int month) {
        double income = 0, expense = 0;
        for (Transaction t : transactions) {
            if (t.getDate().getMonthValue() == month) {
                if (t.getType().equals("Income")) {
                    income += t.getAmount();
                } else {
                    expense += t.getAmount();
                }
            }
        }
        Map<String, Double> summary = new HashMap<>();
        summary.put("income", income);
        summary.put("expense", expense);
        summary.put("savings", income - expense);
        return summary;
    }

    public void loadFromFile(InputStream inputStream) throws IOException {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = reader.readLine()) != null) {
                transactions.add(Transaction.fromString(line));
            }
        }
    }
}
