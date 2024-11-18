package GDG.Blog.Posts.Dto;

import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class CreatePostDto {
    private String title;
    private String content;
    private LocalDateTime createdAt;
}