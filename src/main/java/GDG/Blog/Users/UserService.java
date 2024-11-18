package GDG.Blog.Users;

import GDG.Blog.Users.Dto.CreateUserDto;
import GDG.Blog.Users.Dto.LoginUserDto;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public User createUser(CreateUserDto createUserDto) {
        String encodedPassword = passwordEncoder.encode(createUserDto.getPassword());

        return User.builder()
                .username(createUserDto.getUserName())
                .password(encodedPassword)
                .email(createUserDto.getEmail())
                .auth(createUserDto.getAuth())
                .build();
    }

    public String loginUser(LoginUserDto loginUserDto) {
        User user = userRepository.findByEmail(loginUserDto.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("유저가 존재하지 않습니다."));
        if (Objects.equals(user.getPassword(), loginUserDto.getPassword())) {
            return "로그인 성공";
        } else {
            return "로그인 실패";
        }
    }
}

