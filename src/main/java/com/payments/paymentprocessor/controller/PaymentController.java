package com.payments.paymentprocessor.controller;

import com.payments.paymentprocessor.dto.PaymentDTO;
import com.payments.paymentprocessor.entity.Payment;
import com.payments.paymentprocessor.iban.CountyCodeChecker;
import com.payments.paymentprocessor.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;

@Controller
@RequestMapping(path = "/payments")
public class PaymentController{

    private final PaymentService paymentService;

    @Autowired
    public PaymentController(PaymentService paymentService) {
        this.paymentService = paymentService;
    }

    @GetMapping
    public String showAddPaymentsForm(@ModelAttribute PaymentDTO paymentDTO, Model model) {
        model.addAttribute("paymentDTO", paymentDTO);
        model.addAttribute("paymentList", paymentService.getPayments());

        return "payments";
    }

    @PostMapping
    public String addPayment(@Valid PaymentDTO paymentDTO, BindingResult bindingResult, RedirectAttributes redirectAttributes) {

        // Check for amount greater than 0.0
        if(paymentDTO.getAmount() <= 0.0) {
            bindingResult.addError(new FieldError(
                    "paymentDTO", "amount", "Amount cannot be less or equal to 0.0"
            ));
        }

        // Check for IBAN country code
        if(!CountyCodeChecker.validCode(paymentDTO.getDebtorIban())) {
            bindingResult.addError(new FieldError(
                    "paymentDTO", "debtorIban", "Only Baltic accounts supported"
            ));
        }

        if (bindingResult.hasErrors()) {
            return "payments";
        }

        paymentService.addNewPayment(paymentDTO);
        redirectAttributes.addFlashAttribute("message", "Payment successfully added!");

        return "redirect:/payments";
    }

    @GetMapping("/payments/success")
    public String showSuccessPage() {

        return "success";
    }
}
