package kwan.org.webloginchatdemo.controller.api.request;

import lombok.Data;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
public class NotificationRequest {

    private String notifyDay;

    private String notifyBy;

    private String notifyTime;

    private String sendTo;

    private Boolean isRepeat;
}
