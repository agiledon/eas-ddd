package xyz.zhangyi.ddd.eas.trainingcontext.domain.ticket;

import xyz.zhangyi.ddd.eas.trainingcontext.domain.candidate.Candidate;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.candidate.CandidateRepository;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.exceptions.TicketException;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.tickethistory.TicketHistory;
import xyz.zhangyi.ddd.eas.trainingcontext.domain.tickethistory.TicketHistoryRepository;

import java.util.Optional;

public class TicketService {
    private TicketRepository tickRepo;
    private TicketHistoryRepository ticketHistoryRepo;
    private CandidateRepository candidateRepo;

    public void nominate(TicketId ticketId, Candidate candidate, Nominator nominator) {
        Optional<Ticket> optionalTicket = tickRepo.ticketOf(ticketId, TicketStatus.Available);
        Ticket ticket = optionalTicket.orElseThrow(() -> availableTicketNotFound(ticketId));

        TicketHistory ticketHistory = ticket.nominate(candidate, nominator);

        tickRepo.update(ticket);
        ticketHistoryRepo.add(ticketHistory);
        candidateRepo.remove(candidate);
    }

    private TicketException availableTicketNotFound(TicketId ticketId) {
        return new TicketException(String.format("available ticket by id {%s} is not found.", ticketId));
    }

    public void setTicketRepository(TicketRepository tickRepo) {
        this.tickRepo = tickRepo;
    }

    public void setTicketHistoryRepository(TicketHistoryRepository ticketHistoryRepository) {
        this.ticketHistoryRepo = ticketHistoryRepository;
    }

    public void setCandidateRepository(CandidateRepository candidateRepository) {
        this.candidateRepo = candidateRepository;
    }
}