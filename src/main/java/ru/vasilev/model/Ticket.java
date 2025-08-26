package ru.vasilev.model;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

@Data
@NoArgsConstructor
@ToString
public class Ticket {
    private String origin;
    @SerializedName("origin_name")
    private String originName;
    private String destination;
    @SerializedName("destination_name")
    private String destinationName;
    @SerializedName("departure_date")
    private String departureDate;
    @SerializedName("departure_time")
    private String departureTime;
    @SerializedName("arrival_date")
    private String arrivalDate;
    @SerializedName("arrival_time")
    private String arrivalTime;
    private String carrier;
    private int stops;
    private double price;

    public long getFlightMinutes() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("H:mm");
        LocalTime depTime = departureTime != null ? LocalTime.parse(departureTime, formatter) : LocalTime.of(0, 0);
        LocalTime arrTime = arrivalTime != null ? LocalTime.parse(arrivalTime, formatter) : LocalTime.of(0, 0);
        return ChronoUnit.MINUTES.between(depTime, arrTime);
    }
}
