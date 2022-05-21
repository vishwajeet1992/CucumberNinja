package dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder

public class ContactDetailsDTO {

    private String name;
    private String phoneNumber;
    private String email;
    private String address1;
    private String address2;
    private String country;
    private String postcode;

    public ContactDetailsDTO() {
    }

}
