package GDG.Blog.Posts.Dto;

import lombok.Getter;

@Getter
public class GetAllPostDto {
    private Long id;
    private String title;
    private String content;
    private String userName;
    private int commentCount;
    private int likeCount;

    public GetAllPostDto(Long id, String title, String content, String userName, int commentCount, int likeCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userName = userName;
        this.commentCount = commentCount;
        this.likeCount = likeCount;
    }
}
