package com.example.adminpanelbot.repositories;

import com.example.adminpanelbot.model.Node;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NodeRepository extends JpaRepository<Node, Long>
{
    @Query(value = "select n from Node n where n.parent.id = :parentId order by n.id")
    public List<Node> getNodesByParent(Long parentId);
    @Query(value = "select * from node where parent_node_id is null", nativeQuery = true)
    public Optional<Node> getRootNode();
}