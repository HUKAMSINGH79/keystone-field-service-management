package com.zidio.keystone.service;

public interface EmailService {

    void sendPasswordResetEmail(String toEmail, String resetLink);

}