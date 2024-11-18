package GDG.Blog.Users;

import GDG.Blog.Users.Dto.CreateUserDto;
import GDG.Blog.Users.Dto.LoginUserDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
//@Requestmapping("/user")
public class UserController {

    private UserService userService;

    // 회원가입
    @PostMapping("/user")
    public ResponseEntity<User>createMember(@RequestBody CreateUserDto createUserDto) {
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(userService.createUser(createUserDto));
    }

    // 로그인
    @PostMapping("/user/login")
    public ResponseEntity<String> loginMember(@RequestBody LoginUserDto loginUserDto) {
        return ResponseEntity.ok()
                .body(userService.loginUser(loginUserDto));
    }

}
