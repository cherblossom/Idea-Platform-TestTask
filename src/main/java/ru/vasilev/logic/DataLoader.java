package ru.vasilev.logic;

import com.google.gson.Gson;
import ru.vasilev.model.Ticket;
import ru.vasilev.model.TicketWrapper;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class DataLoader {
    private static final Ticket[] EMPTY_TICKETS = new Ticket[0];
    private final Gson gson = new Gson();

    public Ticket[] loadTickets() throws IllegalStateException {
        try (InputStream stream = getClass().getClassLoader().getResourceAsStream("tickets.json")) {
            if (stream == null) {
                System.err.println("Файл tickets.json не найден в ресурсах.");
                throw new IllegalStateException("Файл tickets.json отсутствует.");
            }
            try (InputStreamReader reader = new InputStreamReader(stream, StandardCharsets.UTF_8)) {
                TicketWrapper data = gson.fromJson(reader, TicketWrapper.class);
                return data != null && data.getTickets() != null ? data.getTickets() : EMPTY_TICKETS;
            }
        } catch (Exception e) {
            System.err.printf("Ошибка при загрузке tickets.json: %s%n", e.getMessage());
            throw new IllegalStateException("Ошибка парсинга JSON", e);
        }
    }

    public List<Ticket> filterVvoToTlv(Ticket[] tickets) {
        return Arrays.stream(tickets)
                .filter(ticket -> "VVO".equals(ticket.getOrigin()) && "TLV".equals(ticket.getDestination()))
                .collect(Collectors.toList());
    }
}