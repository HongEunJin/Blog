package GDG.Blog.Posts.Dto;

import lombok.Getter;

import java.util.List;

@Getter
public class GetDetailPostDto {
    private Long id;
    private String title;
    private String content;
    private String userName;
    private List<CommentDto> comments;
    private List<String> likes;
    private int likeCount;

    public GetDetailPostDto(Long id, String title, String content, String userName, List<CommentDto> comments, List<String> likes, int likeCount) {
        this.id = id;
        this.title = title;
        this.content = content;
        this.userName = userName;
        this.comments = comments;
        this.likes = likes;
        this.likeCount = likeCount;
    }
}
