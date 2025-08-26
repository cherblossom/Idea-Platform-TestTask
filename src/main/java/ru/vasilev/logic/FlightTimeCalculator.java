package ru.vasilev.logic;

import ru.vasilev.model.Ticket;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class FlightTimeCalculator {
    public Map<String, Long> calculateMinFlightTimes(List<Ticket> tickets) {
        if (tickets.isEmpty()) {
            return Collections.emptyMap();
        }
        return tickets.stream()
                .collect(Collectors.groupingBy(
                        Ticket::getCarrier,
                        Collectors.minBy(Comparator.comparingLong(Ticket::getFlightMinutes))
                ))
                .entrySet().stream()
                .collect(Collectors.toMap(
                        java.util.Map.Entry::getKey,
                        entry -> entry.getValue().orElseThrow().getFlightMinutes()
                ));
    }
}