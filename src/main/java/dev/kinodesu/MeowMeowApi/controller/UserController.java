package dev.kinodesu.MeowMeowApi.controller;

import dev.kinodesu.MeowMeowApi.model.user.MeowUser;
import dev.kinodesu.MeowMeowApi.service.UserService;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.Map;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    
    @GetMapping("{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        MeowUser user = userService.getUserById(id);
    
        return ResponseEntity.ok.body(user);
    }

    @GetMapping()
    public ResponseEntity<?> getUserList() {
        List<MeowUser> userList = userService.getUserList();
        return ResponseEntity.ok().body(userList);
    }

    @GetMapping("page")
    public ResponseEntity<?> getPagedUserList(@PageableDefault(value = 10) Pageable pageable){
        Page<User> page = userService.getUserPage(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("filter")
    public ResponseEntity<?> getUserByDetail(@PageableDefault(value = 10) Pageable pageable,
            @RequestParam Map<String,String> filters){
        Page<User> page = userService.getFilteredUserPage(pageable, filters);

        return ResponseEntity.ok().body(page);
    }
}
