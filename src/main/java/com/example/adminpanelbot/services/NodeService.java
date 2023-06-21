package com.example.adminpanelbot.services;

import com.example.adminpanelbot.model.Node;
import com.example.adminpanelbot.repositories.NodeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class NodeService
{
    NodeRepository nodeRepository;
    @Autowired
    public NodeService(NodeRepository nodeRepository) {
        this.nodeRepository = nodeRepository;
    }

    public List<Node> getAllNodesByParentId(Long parentNodeId)
    {
        return nodeRepository.getNodesByParent(parentNodeId);
    }

    public void addNode(Node node)
    {
        //node.getParent().addChild(node);
        nodeRepository.save(node);
    }
    public Optional<Node> findNodeById(Long id)
    {
        return nodeRepository.findById(id);
    }

    public boolean remove(Long nodeId)
    {
        Optional<Node> node = nodeRepository.findById(nodeId);

        if(node.isPresent())
        {
            if(node.get().getParent() == null)
                throw new RuntimeException("Нельзя удалить коренной узел!");
            nodeRepository.delete(node.get());
            return true;
        }
        return false;
    }

    public void createRootNode()
    {
        if(!nodeRepository.getRootNode().isPresent())
        {
            Node rootNode = new Node(null,"Главное меню", "Главное меню");
            nodeRepository.save(rootNode);
        }
    }

    public Node getRootNode()
    {
        return this.nodeRepository.getRootNode().get();
    }
}
