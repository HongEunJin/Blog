package GDG.Blog.Users.Dto;

import GDG.Blog.Users.User;
import lombok.Getter;

@Getter
public class CreateUserDto {
    private String userName;
    private String password;
    private String email;
    private User.Auth auth;
}
