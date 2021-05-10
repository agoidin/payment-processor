package com.payments.paymentprocessor.controller;

import com.payments.paymentprocessor.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

@Controller
@RequestMapping("/payment-files")
public class FileUploadController {

    private final PaymentService paymentService;

    @Autowired
    public FileUploadController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public String showUploadForm() {
        return "uploadForm";
    }

    @PostMapping
    public String uploadCSVFile(@RequestParam("file") MultipartFile file, Model model) {

        // parse CSV file to create a list of `PaymentDTO` objects
        Reader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        paymentService.addNewPaymentsFromFile(reader);
        model.addAttribute("message", "An error occurred while processing the CSV file.");

        return "redirect:/payments";
    }
}
