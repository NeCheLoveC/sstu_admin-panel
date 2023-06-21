package com.example.adminpanelbot.controllers;

import com.example.adminpanelbot.model.Node;
import com.example.adminpanelbot.repositories.NodeRepository;
import com.example.adminpanelbot.services.NodeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class NodeController
{
    @Autowired
    protected NodeService nodeService;
    @Autowired
    private NodeRepository nodeRepository;

    @GetMapping
    public String showNode(@RequestParam(name = "currentNodeId",required = false) Long currentNodeId, Model model)
    {
        if(currentNodeId == null)
            currentNodeId = nodeService.getRootNode().getId();
        Optional<Node> currentNode = nodeService.findNodeById(currentNodeId);
        if(currentNode.isEmpty())
        {
            //Данный узел не найден 404 ошибка ,
            throw new ResourceNotFoundException();
        }
        Node rootNode = nodeService.getRootNode();
        List<Node> nodeList = nodeService.getAllNodesByParentId(currentNodeId);
        //Вывести элементы главного меню
        model.addAttribute("nodes", nodeList);
        model.addAttribute("current_node", currentNode.get());
        model.addAttribute("root_node", rootNode);
        return "hello";
    }

    @GetMapping("/node/add_form")
    public String showAddNodeForm(@RequestParam(name = "parentNode") Long parentNode, Model model)
    {
        //model.addAttribute("parent_node", parentNode);
        model.addAttribute("node",new Node());
        model.addAttribute("parent_node", parentNode);
        return "CreateNode";
    }

    @PostMapping("/node/delete")
    public RedirectView deleteNode(@RequestParam(name = "id") Long nodeId, RedirectAttributes attributes)
    {
        Optional<Node> node = nodeService.findNodeById(nodeId);
        Long parentNodeId = node.get().getParent().getId();
        boolean removalWasSuccessful = nodeService.remove(nodeId);
        if(removalWasSuccessful)
        {
            List<Node> nodeList = nodeService.getAllNodesByParentId(parentNodeId);

            attributes.addAttribute("currentNodeId",parentNodeId);
            return new RedirectView("/");
            //return "hello";
        }
        else
        {
            // TODO: 27.05.2023 ОШИБКА, данный не существует или уже был удален.
            throw new ResourceNotFoundException();
        }
    }

    @PostMapping("/node/add")
    public RedirectView addNode(@RequestParam(name = "parentNode") Long parentIdNode,
                                @RequestParam(name = "name") String name,
                                @RequestParam(name = "text") String text, RedirectAttributes attributes)
    {
        name = name.trim();
        Optional<Node> parent = nodeService.findNodeById(parentIdNode);
        if(name.isEmpty())
            throw new IllegalArgumentException("Имя узла не должно быть пустым");
        Node node = new Node(parent.get(),name,text);
        nodeService.addNode(node);
        //List<Node> nodeList = nodeService.getAllNodesByParentId(parentIdNode);
        attributes.addAttribute("currentNodeId", parentIdNode);
        return new RedirectView("/");
    }

    @GetMapping("/node/editform")
    public String showEditForm(@RequestParam(name = "node_id") Long nodeId, Model model)
    {
        Optional<Node> node = nodeService.findNodeById(nodeId);
        if(node.isEmpty())
            throw new ResourceNotFoundException();
        model.addAttribute("node", node.get());
        return "edit-form";
    }

    @PostMapping("/node/update")
    public RedirectView updateNode(@RequestParam("id") Long id,@RequestParam String name, @RequestParam String text,RedirectAttributes attributes)
    {
        Optional<Node> n = nodeService.findNodeById(id);
        if(n.isEmpty() || n.get().isRootNode())
            throw new ResourceNotFoundException();
        n.get().setName(name);
        n.get().setText(text);
        nodeService.addNode(n.get());
        attributes.addAttribute("currentNodeId",n.get().getParent().getId());
        return new RedirectView("/");
    }
}
