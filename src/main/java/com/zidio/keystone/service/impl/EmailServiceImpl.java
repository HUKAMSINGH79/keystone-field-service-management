package com.zidio.keystone.service.impl;

import com.zidio.keystone.service.EmailService;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Override
    public void sendPasswordResetEmail(String toEmail, String resetLink) {

        try {

            MimeMessage message = mailSender.createMimeMessage();

            MimeMessageHelper helper =
                    new MimeMessageHelper(message, true);

            helper.setTo(toEmail);

            helper.setSubject("Keystone Password Reset");

            String html = """
                    <html>
                    <body style="font-family:Arial,sans-serif">
                    
                    <h2>Keystone Field Service Management</h2>

                    <p>Hello,</p>

                    <p>We received a request to reset your password.</p>

                    <p>
                        <a href="%s"
                           style="
                           background:#1976d2;
                           color:white;
                           padding:12px 20px;
                           text-decoration:none;
                           border-radius:6px;">
                           Reset Password
                        </a>
                    </p>

                    <p>This link will expire in <b>30 minutes</b>.</p>

                    <p>If you didn't request this, you can safely ignore this email.</p>

                    <br>

                    <p>Regards,</p>
                    <b>Keystone Team</b>

                    </body>
                    </html>
                    """.formatted(resetLink);

            helper.setText(html, true);

            mailSender.send(message);

        } catch (MessagingException e) {

            throw new RuntimeException("Unable to send email.");

        }
    }
}