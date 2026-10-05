package restaurant.domain.entity;

import restaurant.domain.enums.SupportRequestStatus;

import java.time.LocalDateTime;

public class SupportRequest {
    int requestId;
    int tableId;
    int robotId;
    String message;
    LocalDateTime createdAt;
    SupportRequestStatus status;

    public SupportRequest(int requestId, int tableId, int robotId, String message, LocalDateTime createdAt, SupportRequestStatus status) {
        this.requestId = requestId;
        this.tableId = tableId;
        this.robotId = robotId;
        this.message = message;
        this.createdAt = createdAt;
        this.status = status;
    }

    public int getRequestId() {
        return requestId;
    }

    public int getTableId() {
        return tableId;
    }

    public int getRobotId() {
        return robotId;
    }

    public String getMessage() {
        return message;
    }
    public void setMessage(String message) {
        this.message = message;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public SupportRequestStatus getStatus() {
        return status;
    }
    public void setStatus(SupportRequestStatus status) {
        this.status = status;
    }
}
