package xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket;

import xyz.zhangyi.ddd.eas.trainingcontext.domain.candidate.Candidate;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.exceptions.TicketException;

import java.util.Optional;

public class TicketService {
    private TicketRepository tickRepo;

    public void setTicketRepository(TicketRepository tickRepo) {
        this.tickRepo = tickRepo;
    }

    public void nominate(TicketId ticketId, Candidate candidate, Nominator nominator) {
        Optional<Ticket> optionalTicket = tickRepo.ticketOf(ticketId, TicketStatus.Available);
        if (!optionalTicket.isPresent()) {
            throw new TicketException(String.format("available ticket by id {%s} is not found.", ticketId));
        }
    }
}