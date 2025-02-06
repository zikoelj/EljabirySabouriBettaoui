package com.eljabiry.movieService.controller;

import com.eljabiry.movieService.services.abstracts.PaymentService;
import com.eljabiry.movieService.dto.TicketInformationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/api/movie/payments/")
@RequiredArgsConstructor
//@CrossOrigin
public class PaymentController {

    private final PaymentService paymentService;

    @PostMapping("sendTicketDetail")
    public void sendTicketDetail(@RequestBody TicketInformationDto ticketInformationDto) {
        paymentService.sendTicketDetail(ticketInformationDto);
    }
}
