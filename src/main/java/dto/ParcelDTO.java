package dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class ParcelDTO {

    private String pickupDate;
    private String name;
    private String phoneNumber;
    private String email;
    private String address1;
    private String address2;
    private String country;
    private String postcode;
    private String pickupStartTime;
    private String pickupEndTime;
    private String pickupTimeZone;
    private String deliveryStartTime;
    private String deliveryEndTime;
    private String deliveryTimeZone;


}

