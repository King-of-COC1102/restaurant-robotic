package restaurant.infrastructure.event;

public class EventListener {

    // Phương thức chung để tiếp nhận và kích hoạt sự kiện
    public void onEventReceived(Event event) {
        if (event != null) {
            System.out.println("--------------------------------------------------");
            System.out.println("[THÔNG BÁO] Phát hiện sự kiện mới trong hệ thống nhà hàng...");
            event.fireEvent(); // Kích hoạt hành động bên trong Event
            System.out.println("--------------------------------------------------");
        }
    }
}