package exercise.controller;

import exercise.model.Comment;
import exercise.repository.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import java.util.List;

import exercise.model.Post;
import exercise.repository.PostRepository;
import exercise.exception.ResourceNotFoundException;
import exercise.dto.PostDTO;
import exercise.dto.CommentDTO;

// BEGIN
@RestController
public class PostsController {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private PostRepository postRepository;

    @GetMapping("/posts")
    public List<PostDTO> index() {
        var posts = postRepository.findAll();
        var resuls = posts.stream()
                .map(this::postToDto)
                .toList();
        return resuls;
    }

    @GetMapping("/posts/{id}")
    public PostDTO show(@PathVariable Long id) {
        var post = postRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Post with id " + id + " not found"));
        return this.postToDto(post);
    }

    private PostDTO postToDto(Post post) {
        var dto = new PostDTO();
        dto.setId(post.getId());
        dto.setTitle(post.getTitle());
        dto.setBody(post.getBody());
        var dtoCommentList = commentRepository.findByPostId(post.getId()).stream()
                .map(this::commentToDTO)
                .toList();
        dto.setComments(dtoCommentList);
        return dto;
    }

    private CommentDTO commentToDTO(Comment comment) {
        var dto = new CommentDTO();
        dto.setId(comment.getId());
        dto.setBody(comment.getBody());
        return dto;
    }
}
// END
