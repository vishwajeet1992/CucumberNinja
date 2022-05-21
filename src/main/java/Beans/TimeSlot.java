package Beans;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TimeSlot {

    private String startTime;
    private String endTime;
    private String timeZone;


}
