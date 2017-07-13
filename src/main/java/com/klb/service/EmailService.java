package com.klb.service;

/**
 * Created by fmkam on 08.07.2017.
 */
public interface EmailService  {

    void sendEmail(String fromAddress, String toAddress, String subject, String body);

}
