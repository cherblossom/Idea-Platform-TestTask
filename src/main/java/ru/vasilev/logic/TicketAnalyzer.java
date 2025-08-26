package ru.vasilev.logic;

import ru.vasilev.model.Ticket;

import java.util.List;
import java.util.Map;

public class TicketAnalyzer {
    private final DataLoader dataLoader = new DataLoader();
    private final FlightTimeCalculator flightTimeCalculator = new FlightTimeCalculator();
    private final PriceAnalyzer priceAnalyzer = new PriceAnalyzer();

    public void analyzeTickets() {
            Ticket[] tickets = dataLoader.loadTickets();
            if (tickets.length == 0) {
                System.out.println("Нет данных о билетах.");
                return;
            }

            List<Ticket> filteredTickets = dataLoader.filterVvoToTlv(tickets);
            if (filteredTickets.isEmpty()) {
                System.out.println("Нет подходящих рейсов.");
                return;
            }

            Map<String, Long> minTimes = flightTimeCalculator.calculateMinFlightTimes(filteredTickets);
            printFlightTimes(minTimes);

            double difference = priceAnalyzer.calculatePriceDifference(filteredTickets);
            System.out.printf("Разница между средней ценой и медианой: %.0f\n", difference);
    }

    private void printFlightTimes(Map<String, Long> minTimes) {
        System.out.println("Минимальное время полета для каждого авиаперевозчика:");
        minTimes.forEach((carrier, minutes) -> {
            long hours = minutes / 60;
            long mins = minutes % 60;
            System.out.printf("%s: %d:%02d\n", carrier, hours, mins);
        });
    }
}