package com.uploadBRL.jhonatanTeles.exeptions;

public class KafkaException extends RuntimeException{
    public KafkaException(String message) {
        super(message);
    }
    public KafkaException(String message, Throwable cause) {super(message, cause);}
}
