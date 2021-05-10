package com.payments.paymentprocessor.controller;

import com.payments.paymentprocessor.entity.Payment;
import com.payments.paymentprocessor.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping
public class PaymentController{

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping("/")
    public String showAddPaymentsForm(Payment payment, Model model) {
        model.addAttribute("payment", new Payment());
        model.addAttribute("paymentList", paymentService.getPayments());

        return "form";
    }

    @PostMapping(path = "/")
    public String addPayment(@ModelAttribute Payment payment, BindingResult bindingResult) {

        if (bindingResult.hasErrors()) {
            return "form";
        }

        System.out.println("IBAN: " + payment.getDebtorIban());
        System.out.println("Amount: " + payment.getAmount());

        paymentService.addNewPayment(payment);

        return "redirect:/results";
    }
}
