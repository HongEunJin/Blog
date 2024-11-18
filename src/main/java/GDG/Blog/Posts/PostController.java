package GDG.Blog.Posts;

import GDG.Blog.Posts.Dto.CreatePostDto;
import GDG.Blog.Posts.Dto.GetAllPostDto;
import GDG.Blog.Posts.Dto.GetDetailPostDto;
import GDG.Blog.Posts.Dto.UpdatePostDto;
import GDG.Blog.Users.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class PostController {

    private final UserRepository userRepository;
    private final PostService postService;

    // 게시글 작성
    @PostMapping("/post")
    public ResponseEntity createPost(@RequestBody CreatePostDto createPostDto) {
        Post post = postService.createPost(createPostDto);
        return ResponseEntity.ok(post);
    }

    // 게시글 수정
    @PutMapping("/post/{postId}")
    public ResponseEntity<UpdatePostDto> updatePost(@PathVariable("postId") Long postId, @RequestBody UpdatePostDto updatePostDto) {
        return ResponseEntity.ok()
                .body(postService.updatePost(postId, updatePostDto));
    }

    // 모든 게시글 조회
    @GetMapping("/posts")
    public ResponseEntity<List<GetAllPostDto>> getAllPosts() {
        List<GetAllPostDto> posts = postService.getAllPosts();
        return ResponseEntity.ok(posts);
    }

    // 게시글 하나 조회
    @GetMapping("/post/{postId")
    public ResponseEntity<GetDetailPostDto> getPost(@PathVariable("postId") Long postId) {
        GetDetailPostDto postDetail = postService.getPost(postId);
        return ResponseEntity.ok(postDetail);
    }

    // 게시글 삭제
    @DeleteMapping("/post/{postId}")
    public ResponseEntity deletePost(@PathVariable("postId") Long postId) {
        postService.deletePost(postId);
        return ResponseEntity.ok().build();
    }
}
