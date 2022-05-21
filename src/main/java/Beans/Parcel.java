package Beans;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Parcel {

    private String pickupDate;
    private Address pickupAddress;
    private TimeSlot pickupTimeSlot;
    private TimeSlot deliveryTimeSlot;
}
