package com.eljabiry.movieService.services.abstracts;

import com.eljabiry.movieService.dto.TicketInformationDto;

public interface PaymentService {

    void sendTicketDetail(TicketInformationDto ticketInformationDto);
}
