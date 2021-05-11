package com.payments.paymentprocessor.controller;

import com.payments.paymentprocessor.service.CountryByIPService;
import com.payments.paymentprocessor.service.IPRequestService;
import com.payments.paymentprocessor.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

@Controller
@RequestMapping("/payment-files")
public class FileUploadController {

    private final PaymentService paymentService;
    private final IPRequestService ipRequestService;

    @Autowired
    public FileUploadController(PaymentService paymentService, IPRequestService ipRequestService) {
        this.paymentService = paymentService;
        this.ipRequestService = ipRequestService;
    }

    @GetMapping
    public String showUploadForm() {
        return "uploadForm";
    }

    @PostMapping
    public String uploadCSVFile(@RequestParam("file") MultipartFile file,
                                HttpServletRequest httpServletRequest) {

        // parse CSV file to create a list of `PaymentDTO` objects
        Reader reader = null;

        try {
            reader = new BufferedReader(new InputStreamReader(file.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

        String clientIp = ipRequestService.getClientIP(httpServletRequest);

        if(CountryByIPService.getRequestStatus(clientIp).equals("success")) {
            System.out.println(
                "IP: " + clientIp + "\n" +
                    "Request done from " + CountryByIPService.getRequestCountry(clientIp)
            );

        } else {
            System.out.println(
                "IP: " + clientIp + "\n" +
                    "Wasn't able to resolve the country by IP"
            );
        }

        paymentService.addNewPaymentsFromFile(reader);

        return "redirect:/payments";
    }
}
