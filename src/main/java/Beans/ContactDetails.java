package Beans;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactDetails {

    private String name;
    private String phoneNumber;
    private String email;
    private Address address;
}
