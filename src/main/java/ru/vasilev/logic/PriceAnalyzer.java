package ru.vasilev.logic;

import ru.vasilev.model.Ticket;

import java.util.List;

public class PriceAnalyzer {
    public double calculatePriceDifference(List<Ticket> tickets) {
        if (tickets.isEmpty()) {
            return 0.0;
        }

        List<Double> prices = tickets.stream()
                .map(Ticket::getPrice)
                .sorted()
                .toList();

        int n = prices.size();
        double median = (n % 2 == 0)
                ? (prices.get(n / 2 - 1) + prices.get(n / 2)) / 2.0
                : prices.get(n / 2);

        double average = prices.stream()
                .mapToDouble(Double::doubleValue)
                .average()
                .orElse(0.0);

        return average - median;
    }
}
