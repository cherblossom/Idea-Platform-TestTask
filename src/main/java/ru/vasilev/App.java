package ru.vasilev;

import ru.vasilev.logic.TicketAnalyzer;

public class App {
    public static void main(String[] args) {
        TicketAnalyzer analyzer = new TicketAnalyzer();
        analyzer.analyzeTickets();
    }
}
