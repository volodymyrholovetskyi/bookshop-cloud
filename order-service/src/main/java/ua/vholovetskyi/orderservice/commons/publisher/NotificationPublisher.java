package ua.vholovetskyi.orderservice.commons.publisher;

public interface NotificationPublisher {

    void publishNotification(NotificationMessageDto message);
}
