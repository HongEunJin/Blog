package GDG.Blog.Posts.Dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class UpdatePostDto {
    private String title;
    private String content;
    private LocalDateTime updateAt;
}
