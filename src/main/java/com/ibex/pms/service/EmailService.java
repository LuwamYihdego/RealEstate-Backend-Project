package com.ibex.pms.service;

public interface EmailService {
    public boolean sendEmail (String to, String subject, String message);
}
