package Beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Parcel {

    @JsonProperty("pickup_date")
    private String pickupDate;
    @JsonProperty("pickup_address")
    private Address pickupAddress;
    @JsonProperty("pickup_timeslot")
    private TimeSlot pickupTimeSlot;
    @JsonProperty("delivery_timeslot")
    private TimeSlot deliveryTimeSlot;
}
