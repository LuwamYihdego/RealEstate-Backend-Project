package com.ibex.pms.util;

public interface EmailService {
    public boolean sendEmail (String to, String subject, String message);
}
