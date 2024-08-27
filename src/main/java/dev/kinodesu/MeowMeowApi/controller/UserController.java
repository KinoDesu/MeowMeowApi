package dev.kinodesu.MeowMeowApi.controller;

import dev.kinodesu.MeowMeowApi.model.user.DTOMeowUser;
import dev.kinodesu.MeowMeowApi.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("{id}")
    public ResponseEntity<Object> getUserById(@PathVariable Long id) {
        DTOMeowUser user = userService.getUserById(id);

        return ResponseEntity.ok().body(user);
    }

    @GetMapping()
    public ResponseEntity<Object> getUserList() {
        List<DTOMeowUser> userList = userService.getUserList();
        return ResponseEntity.ok().body(userList);
    }

    @GetMapping("page")
    public ResponseEntity<Object> getPagedUserList(@PageableDefault(value = 10) Pageable pageable) {
        Page<DTOMeowUser> page = userService.getUserPage(pageable);
        return ResponseEntity.ok().body(page);
    }

    @GetMapping("filter")
    public ResponseEntity<Object> getUserByDetail(@PageableDefault(value = 10) Pageable pageable,
                                             @RequestParam Map<String, String> filters) {
        Page<DTOMeowUser> page = userService.getFilteredUserPage(pageable, filters);

        return ResponseEntity.ok().body(page);
    }

    @PostMapping()
    @Transactional
    public ResponseEntity<Object> postUser(@RequestBody DTOMeowUser newMeowUser) {
        DTOMeowUser responseUser = userService.postUser(newMeowUser);

        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(responseUser.id())
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @PutMapping("{id}")
    @Transactional
    public ResponseEntity<Object> putUserById(@PathVariable Long id, @RequestBody DTOMeowUser newMeowUser) {
        userService.putUserById(id, newMeowUser);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    @Transactional
    public ResponseEntity<Object> deleteUserById(@PathVariable Long id) {
        userService.changeUserStatusById(id);
        return ResponseEntity.noContent().build();
    }
}
