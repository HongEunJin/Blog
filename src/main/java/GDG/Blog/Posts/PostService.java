package GDG.Blog.Posts;

import GDG.Blog.Posts.Dto.*;
import GDG.Blog.Users.User;
import GDG.Blog.Users.UserRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PostService {

    private final UserRepository userRepository;
    private final PostRepository postRepository;

    public Post createPost(CreatePostDto createPostDto) {
        User user = getCurrentUser();

        Post post = Post.builder()
                .title(createPostDto.getTitle())
                .content(createPostDto.getContent())
                .user(user)
                .build();

        return postRepository.save(post);
    }

    private User getCurrentUser() {
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails) principal).getUsername();
            return userRepository.findByUsername(username)
                    .orElseThrow(() -> new IllegalArgumentException("로그인된 사용자 정보를 찾을 수 없습니다."));
        } else {
            throw new IllegalStateException("로그인된 사용자 정보를 가져올 수 없습니다.");
        }
    }

    @Transactional
    public UpdatePostDto updatePost(Long postId, UpdatePostDto updatePostDto) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException(("Post를 찾을 수 없습니다.")));
        post.updatePost(updatePostDto);
        return updatePostDto;
    }

    public List<GetAllPostDto> getAllPosts() {
        return postRepository.findAll().stream()
                .map(post -> {
                    int commentCount = post.getComments().size();
                    int likeCount = post.getLikes().size();
                    String userName = post.getUser().getUserName();

                    return new GetAllPostDto(
                            post.getId(),
                            post.getTitle(),
                            post.getContent(),
                            userName,
                            commentCount,
                            likeCount
                    );
                })
                .collect(Collectors.toList());
    }
    
    public GetDetailPostDto getPost(Long postId) {
        Post post = postRepository.findById(postId)
                .orElseThrow(() -> new IllegalArgumentException("게시글을 찾을 수 없습니다."));

        List<CommentDto> comments = post.getComments().stream()
                .map(comment -> new CommentDto(
                        comment.getId(),
                        comment.getContent(),
                        comment.getUser().getUserName()
                ))
                .collect(Collectors.toList());

        List<String> likes = post.getLikes().stream()
                .map(like -> like.getUser().getUserName())
                .collect(Collectors.toList());

        int likeCount = likes.size();

        return new GetDetailPostDto(
                post.getId(),
                post.getTitle(),
                post.getContent(),
                post.getUser().getUserName(),
                comments,
                likes,
                likeCount
        );
    }


    @Transactional
    public void deletePost(Long postId) { postRepository.deleteById(postId); }

}
