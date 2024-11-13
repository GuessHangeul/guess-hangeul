// src/main/java/com/estsoft/guesshangeul/post/controller/GeneralPostViewController.java
package com.estsoft.guesshangeul.post.controller;

import com.estsoft.guesshangeul.post.dto.AddGeneralPostRequest;
import com.estsoft.guesshangeul.post.dto.GeneralPostResponse;
import com.estsoft.guesshangeul.post.dto.UpdateGeneralPostRequest;
import com.estsoft.guesshangeul.post.service.GeneralPostService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/generalBoard/{generalBoardId}")
public class GeneralPostViewController {

    private final GeneralPostService generalPostService;

    @GetMapping("/create")
    public String showCreateForm(@PathVariable Long generalBoardId, Model model) {
        model.addAttribute("generalBoardId", generalBoardId);
        return "generalPostCreate";
    }

    @PostMapping("/generalPost")
    public String createPost(@PathVariable Long generalBoardId, @ModelAttribute AddGeneralPostRequest request) {
        generalPostService.createGeneralPost(request, generalBoardId);
        return "redirect:/generalBoard/" + generalBoardId;
    }

    @GetMapping("/newPost")
    public String newPost(@PathVariable Long generalBoardId, Model model) {
        model.addAttribute("generalBoardId", generalBoardId);
        return "generalPostCreate";
    }

    @GetMapping("/generalPost/{id}")
    public String viewGeneralPost(@PathVariable Long generalBoardId, @PathVariable Long id, Model model) {
        GeneralPostResponse post = generalPostService.getGeneralPostById(generalBoardId, id);
        model.addAttribute("post", post);
        return "generalPostPage";
    }

    @GetMapping("/updatePost/{id}")
    public String showUpdateForm(@PathVariable Long generalBoardId, @PathVariable Long id, Model model) {
        GeneralPostResponse post = generalPostService.getGeneralPostById(generalBoardId, id);
        model.addAttribute("generalBoardId", generalBoardId);
        model.addAttribute("post", post);
        return "generalPostUpdate";
    }

    @PutMapping("/updatePost/{id}")
    public String updatePost(@PathVariable Long generalBoardId, @PathVariable Long id, @ModelAttribute UpdateGeneralPostRequest request) {
        generalPostService.updateGeneralPost(generalBoardId, id, request);
        return "redirect:/generalBoard/" + generalBoardId;
    }
}