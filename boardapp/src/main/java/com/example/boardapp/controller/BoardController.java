package com.example.boardapp.controller;

import com.example.boardapp.domain.Board;
import com.example.boardapp.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequiredArgsConstructor
@RequestMapping("/boards")
public class BoardController {
    private final BoardService boardService;

    @GetMapping("/list")
    public String list(Model model, @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                       @RequestParam(name = "size", required = false, defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page-1, size);

        model.addAttribute("boards", boardService.findAllBoard(pageable));
        model.addAttribute("currentPage", page);
        return "boards/list";
    }

    // 게시물 등록 폼 요청
    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("board", new Board());
        return "boards/form";
    }

    // 게시물 저장
    @PostMapping("/add")
    public String addBoard(@ModelAttribute Board board){
        boardService.addBoard(board);
        return "redirect:/boards/list";
    }
}
