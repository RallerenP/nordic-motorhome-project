package com.nordicmotorhome.motorhomerentals.data;

import com.nordicmotorhome.motorhomerentals.domain.MessageType;

// AUTHOR: RAP
// Message is a data type we return when converting from entities to models in Services

/**
 * The idea is to return a message between the domain and the UI layer.
 * The message is completely immutable, lacking setters and any non-final attributes.
 */
public class Message {
    private final MessageType type;
    private final Object content;

    public Message(MessageType type, Object content) {
        this.type = type;
        this.content = content;
    }

    public MessageType getType() {
        return type;
    }

    public Object getContent() {
        return content;
    }
}
