package exercise.controller.users;

import java.util.List;
import java.util.stream.Collectors;

import jakarta.websocket.server.PathParam;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import exercise.model.Post;
import exercise.Data;

// BEGIN
@RestController
@RequestMapping("/api")
public class PostsController {

    public static List<Post> posts = Data.getPosts();

    @GetMapping("/users/{id}/posts")
    public List<Post> index(@PathVariable Integer id) {
        return posts.stream()
                .filter(post -> post.getUserId() == id)  // `getUserId()` returns `int`, so `==` is fine
                .toList();  // Convert the stream to a list
    }



    @PostMapping("/users/{id}/posts")
    @ResponseStatus(HttpStatus.CREATED)
    public Post create(@PathVariable Integer id,
                                       @RequestBody Post postRequest) {
        postRequest.setUserId(id);
        posts.add(postRequest);
        return post;
    }
}
// END
