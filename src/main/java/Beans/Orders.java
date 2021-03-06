package Beans;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Orders {
    @JsonProperty("service_type")
    private String serviceType;
    @JsonProperty("service_level")
    private String serviceLevel;
    private ContactDetails from;
    private ContactDetails to;
    private Parcel parcelJob;
}
