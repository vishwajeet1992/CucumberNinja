package Beans;

import cucumber.deps.com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class TimeSlot {

    @XStreamAlias("start_time")
    private String startTime;
    @XStreamAlias("end_time")
    private String endTime;
    private String timezone;


}
