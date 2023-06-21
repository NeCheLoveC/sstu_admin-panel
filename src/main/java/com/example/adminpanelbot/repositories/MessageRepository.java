package com.example.adminpanelbot.repositories;

import com.example.adminpanelbot.model.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long>
{
    @Query("select m from Message m order by m.created desc limit 5")
    public List<Message> getLastFiveMessages();
}
