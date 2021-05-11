package com.payments.paymentprocessor.controller;

import com.payments.paymentprocessor.dto.PaymentDTO;
import com.payments.paymentprocessor.iban.CountyCodeChecker;
import com.payments.paymentprocessor.service.CountryByIPService;
import com.payments.paymentprocessor.service.IPRequestService;
import com.payments.paymentprocessor.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping
public class PaymentController{

    private final PaymentService paymentService;
    private final IPRequestService ipRequestService;

    @Autowired
    public PaymentController(PaymentService paymentService,
                             IPRequestService ipRequestService) {
        this.paymentService = paymentService;
        this.ipRequestService = ipRequestService;
    }

    @GetMapping(value = {"/payments", "/payments/{debtorIban}"})
    public String showAddPaymentsForm(@PathVariable(required = false) String debtorIban,
                                      @ModelAttribute PaymentDTO paymentDTO,
                                      Model model) {
        if (debtorIban != null) {
            model.addAttribute("paymentDTO", paymentDTO);
            model.addAttribute("paymentList", paymentService.getAllPaymentsByIBAN(debtorIban));
        } else {
            model.addAttribute("paymentDTO", paymentDTO);
            model.addAttribute("paymentList", paymentService.getPayments());
        }

        return "payments";
    }

    @PostMapping("/payments")
    public String addPayment(@Valid PaymentDTO paymentDTO,
                             BindingResult bindingResult,
                             RedirectAttributes redirectAttributes,
                             HttpServletRequest httpServletRequest) {

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

        String clientIp = ipRequestService.getClientIP(httpServletRequest);

        if(CountryByIPService.getRequestStatus(clientIp).equals("success")) {
            redirectAttributes.addFlashAttribute(
                    "ipMessage",
                    "IP: " + clientIp + "\n" +
                            "Request done from " + CountryByIPService.getRequestCountry(clientIp)
            );

        } else {
            redirectAttributes.addFlashAttribute(
                    "ipMessage",
                    "IP: " + clientIp + "\n" +
                            "Wasn't able to resolve the country by IP"
            );
        }

        paymentService.addNewPayment(paymentDTO);
        redirectAttributes.addFlashAttribute("successMessage", "Payment successfully added!");

        return "redirect:/payments";
    }
}
