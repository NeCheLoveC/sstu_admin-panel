package com.example.adminpanelbot.model;

import jakarta.persistence.*;
import org.hibernate.annotations.CreationTimestamp;

import javax.xml.crypto.Data;
import java.sql.Timestamp;

@Entity
@Table(name = "\"message\"")
public class Message
{
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    protected Long id;
    @Column(name = "text", length = 1000)
    protected String text;
    @Column(name = "created", updatable = false)
    @Temporal(TemporalType.TIMESTAMP)
    @CreationTimestamp
    protected Timestamp created;
    public Message(){}

    public Message(String text) {
        this.text = text;
    }

    public Long getId() {
        return id;
    }

    public String getText() {
        return text;
    }

    public Timestamp getCreated() {
        return created;
    }
}
