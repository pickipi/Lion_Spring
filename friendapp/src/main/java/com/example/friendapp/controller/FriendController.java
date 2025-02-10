package com.example.friendapp.controller;

import com.example.friendapp.domain.Friend;
import com.example.friendapp.service.FriendService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/friends")// 리스트를 보여달라고 요청했을때 url이 어떻게 들어와야될지
@RequiredArgsConstructor
public class FriendController {
    private final FriendService friendService;

    // 기존 : 바로 리스트를 보여줌
//    @GetMapping("/list")
//    public String list(Model model){
//        // 해야할 일
//        model.addAttribute("friends", friendService.findAllFriend());
//
//        return "friends/list";
//    }

    // 수정 : 페이지로 리스트를 보여주도록 처리
    @GetMapping("/list")
    public String list(Model model, @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                       @RequestParam(name = "size", required = false, defaultValue = "5") int size){
        Pageable pageable = PageRequest.of(page-1, size);

        // 해야할 일
        model.addAttribute("friends", friendService.findAllFriend(pageable));
        model.addAttribute("currentPage", page);
        return "friends/list";
    }

    // 친구추가 폼 보여주기
    @GetMapping("/add")
    public String addForm(Model model){
        model.addAttribute("friend", new Friend());
        return "friends/form";
    }

    // 친구를 저장 - 저장할 정보를 가져와야함
    @PostMapping("/add")
    public String addFriend(@ModelAttribute Friend friend){
        // 여기서는 Service의 친구저장하는 메소드를 사용하면됨
        friendService.addFriend(friend);
        return "redirect:/friends/list";
    }

    // 친구의 이름을 누르면 상세페이지로 이동하도록 구현
    @GetMapping("/{id}")
    public String detailFriend(@PathVariable(name="id") Long id, Model model){ // URL에서 값을 꺼내기위해 @PathVariable사용하여 가져오고, Long타입으로 받음, 그 후 Model로 정보를 넘겨보내야하므로 Model 선언
        // Service로부터 id에 해당하는 친구정보를 가져와야함
        model.addAttribute("friend", friendService.findFriendById(id));

        return "friends/detail";
    }

//    // 친구수정 폼 보여주기
//    @GetMapping("/update/{id}")
//    public String updateForm(@PathVariable(name="id") Long id, Model model){
//        model.addAttribute("friend", friendService.findFriendById(id));
//        return "friends/updateForm";
//    }
//
//    // 친구를 수정 - 수정할 정보를 가져와야함
//    @PostMapping("/update/{id}")
//    public String updateFriend(@PathVariable(name="id") Long id, @ModelAttribute Friend friend){
//        // 여기서는 Service의 친구수정하는 메소드를 사용하면됨
//        friend.setId(id);
//        friendService.updateFriend(friend);
//        return "redirect:/friends/list";
//    }

    // 친구수정 폼 보여주기
    @GetMapping("/update/{id}")
    public String updateForm(@PathVariable("id") Long id, Model model){
        model.addAttribute("friend", friendService.findFriendById(id));
        return "friends/updateForm";
    }

    // 친구를 수정 - 수정할 정보를 가져와야함
    @PostMapping("/update/{id}")
    public String updateFriend(@PathVariable("id") Long id, @ModelAttribute Friend friend){
        friend.setId(id);
        friendService.updateFriend(friend);
        return "redirect:/friends/list";
    }

    // 친구 삭제
    @GetMapping("/delete/{id}")
    public String delete(@PathVariable("id") Long id){
        // 실제 삭제작업이 일어나려면 Service가 필요할 것이니
        // Service에 삭제하는 메소드를 구현해놓고 가져와야함
        friendService.deleteFriendById(id);

        return "redirect:/friends/list";
    }
}
