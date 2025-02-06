package com.eljabiry.movieService.services.concretes;

import com.eljabiry.movieService.dto.EmailMessageKafkaDto;
import com.eljabiry.movieService.services.abstracts.PaymentService;
import com.eljabiry.movieService.dto.TicketInformationDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {


    @Override
    public void sendTicketDetail(TicketInformationDto ticketInformationDto) {

        EmailMessageKafkaDto emailMessage = EmailMessageKafkaDto.builder()
                .sender("python3.testmail@gmail.com")
                .recipient(ticketInformationDto.getEmail())
                .subtitle("Cinema IDLD Détails du billet")
                .fullName(ticketInformationDto.getFullName())
                .movieName(ticketInformationDto.getMovieName())
                .movieDay(ticketInformationDto.getMovieDay())
                .movieStartTime(ticketInformationDto.getMovieStartTime())
                .saloonName(ticketInformationDto.getSaloonName())
                .chairNumbers(ticketInformationDto.getChairNumbers())
                .build();

    }
}
