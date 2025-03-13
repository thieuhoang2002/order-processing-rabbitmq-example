package rabbit.test2.consumer;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

@Component
public class EmailSender {

    @Autowired
    private JavaMailSender mailSender;

    @RabbitListener(queues = "email-queue")
    public void sendEmail(String email) {
        long startTime = System.currentTimeMillis(); // Ghi lại thời gian bắt đầu

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom("hoangthieu006@gmail.com");
        message.setTo(email);
        message.setSubject("Xác nhận đơn hàng");
        message.setText("Đơn hàng của bạn đã được đặt thành công. Cảm ơn bạn đã mua sắm!");
        
        try {
            mailSender.send(message);
            long endTime = System.currentTimeMillis(); // Ghi lại thời gian kết thúc
            long duration = endTime - startTime; // Tính thời gian thực hiện
            System.out.println("Đã gửi email xác nhận đến: " + email + " trong " + duration + " ms");
        } catch (Exception e) {
            long endTime = System.currentTimeMillis();
            long duration = endTime - startTime;
            System.err.println("Gửi email thất bại đến " + email + " trong " + duration + " ms: " + e.getMessage());
        }
    }
}