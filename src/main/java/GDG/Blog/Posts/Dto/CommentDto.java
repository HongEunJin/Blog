package GDG.Blog.Posts.Dto;

import lombok.Getter;

@Getter
public class CommentDto {
    private Long id;
    private String content;
    private String userName;

    public CommentDto(Long id, String content, String userName) {
        this.id = id;
        this.content = content;
        this.userName = userName;
    }
}
