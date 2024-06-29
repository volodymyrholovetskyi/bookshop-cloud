package ua.vholovetskyi.orderservice.commons.publisher;

import lombok.Builder;
import lombok.Data;

import java.util.Map;

@Data
@Builder
public class NotificationMessageDto {
    private String to;
    private String subject;
    private Map<String, Object> body;
    private String templateName;
}
