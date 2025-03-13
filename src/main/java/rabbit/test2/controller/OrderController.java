package rabbit.test2.controller;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
public class OrderController {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @PostMapping("/order")
    public String placeOrder(@RequestBody Order order) {
        // Ghi lại thời gian bắt đầu
        long startTime = System.currentTimeMillis();

        // Giả lập kiểm tra tồn kho và thanh toán
        System.out.println("Kiểm tra tồn kho cho đơn hàng: " + order.getOrderId());
        System.out.println("Xử lý thanh toán cho đơn hàng: " + order.getOrderId());

        // Đẩy thông tin vào Queue để xử lý bất đồng bộ
        rabbitTemplate.convertAndSend("email-queue", order.getEmail());
        rabbitTemplate.convertAndSend("stats-queue", order.getTotalAmount());

        // Ghi lại thời gian kết thúc và tính toán thời gian xử lý
        long endTime = System.currentTimeMillis();
        long duration = endTime - startTime;

        // In thời gian xử lý ra console (tùy chọn)
        System.out.println("Thời gian xử lý API: " + duration + " ms");

        // Trả về phản hồi với thông tin thời gian
        return "Đơn hàng đã được đặt thành công. Email xác nhận sẽ được gửi ngay. Thời gian xử lý: " + duration + " ms";
    }
}

// Class Order đơn giản
class Order {
    private String orderId;
    private String email;
    private double totalAmount;

    // Getter và Setter
    public String getOrderId() { return orderId; }
    public void setOrderId(String orderId) { this.orderId = orderId; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public double getTotalAmount() { return totalAmount; }
    public void setTotalAmount(double totalAmount) { this.totalAmount = totalAmount; }
}