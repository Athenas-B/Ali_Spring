/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import org.springframework.stereotype.Component;

/**
 *
 * @author olda9
 */
@Component
public class Message {
    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Message() {
    }

    public Message(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "Message{" + "message=" + message + '}';
    }
    
    
}
