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

    // 게시글 등록 폼 요청
    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("board", new Board());
        return "boards/writeForm";
    }

    // 게시글 저장
    @PostMapping("/add")
    public String addBoard(@ModelAttribute Board board){
        boardService.addBoard(board);
        return "redirect:/boards/list";
    }

    // 상세페이지
    @GetMapping("/{id}")
    public String viewBoard(@PathVariable(name="id") Long id, Model model){
        model.addAttribute("board", boardService.findBoardById(id));
        return "boards/view";
    }

    // 게시글 삭제 폼 요청
    @GetMapping("/delete/{id}")
    public String deleteForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("board", boardService.findBoardById(id));
        return "boards/deleteForm";
    }

    // 게시글 삭제
    @PostMapping("/delete/{id}")
    public String deleteBoard(@PathVariable("id") Long id, @ModelAttribute Board board, Model model){
        // 비밀번호 검증을 위한 Board
        Board existBoard = boardService.findBoardById(id);

        // 1. 비밀번호 일치하지 않음 -> 삭제 폼 재이동
        if(!existBoard.getPassword().equals(board.getPassword())){
            model.addAttribute("board", existBoard);
            model.addAttribute("error", "비밀번호가 일치하지 않습니다");
            return "boards/deleteForm";
        }

        // 2. 비밀번호 일치 -> 게시글 삭제
        boardService.deleteBoardById(id);
        return "redirect:/boards/list";
    }

    // 게시글 수정 폼 요청
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("board", boardService.findBoardById(id));
        return "boards/updateForm";
    }

    // 게시글 수정
    @PostMapping("/update/{id}")
    public String updateBoard(@PathVariable("id") Long id, @ModelAttribute Board board, Model model){
        // 비밀번호 검증을 위한 Board
        Board existBoard = boardService.findBoardById(id);

        // 1. 비밀번호 일치하지 않음 -> 수정 폼 재이동
        if(!existBoard.getPassword().equals(board.getPassword())){
            model.addAttribute("board", existBoard);
            model.addAttribute("error", "비밀번호가 일치하지 않습니다");
            return "boards/updateForm";
        }

        // 2. 비밀번호 일치 -> 게시글 수정
        board.setId(id);
        boardService.updateBoard(board);
        return "redirect:/boards/list";
    }
}
