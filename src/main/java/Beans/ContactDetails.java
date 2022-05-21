package Beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ContactDetails {

    private String name;
    @JsonProperty("phone_number")
    private String phoneNumber;
    private String email;
    private Address address;
}
