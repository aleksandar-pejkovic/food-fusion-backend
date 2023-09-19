package dev.alpey.foodfusionbackend.enums;

public enum OrderStatus {

    PENDING("Čeka se odobrenje"),
    PROCESSING("U toku..."),
    COMPLETED("Spremno"),
    CANCELLED("Otkazano");

    private final String status;

    OrderStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public static OrderStatus fromString(String status) {
        for (OrderStatus value : OrderStatus.values()) {
            if (value.status.equalsIgnoreCase(status)) {
                return value;
            }
        }
        throw new IllegalArgumentException("Invalid OrderStatus: " + status);
    }
}
