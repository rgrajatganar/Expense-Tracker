package com.tracker.expensetracker.controller;


import com.tracker.expensetracker.model.Transaction;
import com.tracker.expensetracker.service.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;


import java.io.IOException;
import java.time.LocalDate;

@Controller
public class MainController {

    @Autowired
    private TransactionService service;

    @GetMapping("/")
    public String home(Model model) {
        model.addAttribute("transaction", new Transaction());
        model.addAttribute("transactions", service.getAll());
        return "index";
    }

    @PostMapping("/add")
    public String addTransaction(@ModelAttribute Transaction transaction) {
        if (transaction.getDate() == null) {
            transaction.setDate(LocalDate.now());
        }
        service.addTransaction(transaction);
        return "redirect:/";
    }

    @PostMapping("/upload")
    public String uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        service.loadFromFile(file.getInputStream());
        return "redirect:/";
    }

    @GetMapping("/summary")
    public String summary(@RequestParam int month, Model model) {
        model.addAttribute("summary", service.getMonthlySummary(month));
        model.addAttribute("month", month);
        return "summary";
    }
}
