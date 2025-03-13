package rabbit.test2.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class StatsUpdater {

    @RabbitListener(queues = "stats-queue")
    public void updateStats(double amount) {
        long startTime = System.currentTimeMillis(); // Ghi lại thời gian bắt đầu

        // Giả lập cập nhật thống kê (có thể thay bằng logic lưu vào database)
        System.out.println("Cập nhật thống kê với số tiền: " + amount);

        long endTime = System.currentTimeMillis(); // Ghi lại thời gian kết thúc
        long duration = endTime - startTime; // Tính thời gian thực hiện
        System.out.println("Đã cập nhật thống kê trong " + duration + " ms");
    }
}