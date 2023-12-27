package com.cms.content_management_system.controller;

import com.cms.content_management_system.entity.Post;
import com.cms.content_management_system.entity.User;
import com.cms.content_management_system.repository.PostRepository;
import com.cms.content_management_system.repository.UserRepository;
import com.cms.content_management_system.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PostRepository postRepository;

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }
//    @GetMapping("/users/{uId}")
//    public User getUserById(@PathVariable Integer uId) {
//        return userService.getUserById(uId);
//    }

//    @GetMapping("/{uId}")
//    public User getUserById(@PathVariable Integer uId) {
//        return userService.getUserById(uId);
//    }

    @GetMapping("/{uId}")
    public ResponseEntity<User> getUserById(@PathVariable Integer uId) {
        Optional<User> userOptional = userService.getUserById(uId);
        return userOptional.map(user -> ResponseEntity.ok().body(user))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public void saveUser(@RequestBody User user) {
        userService.saveUser(user);
    }
//    @PostMapping("/{uId}/posts")
//    public Optional<Post> addPostByUser(@PathVariable Integer uId, @RequestBody Post post) {
//        return postRepository.findById(uId);
//    }

 //   @PostMapping("/{uId}/posts")
 //   public Optional<Post> addPostByUser(@PathVariable Integer uId, @RequestBody Post post) {
  //      Optional<User> optionalUser = userRepository.findById(uId);
  //      if (optionalUser.isPresent()) {
   //         User user = optionalUser.get();
    //        post.setUser(user);
     //       postRepository.save(post);
      //  }
    //    return postRepository.findById(post.getpId());
  //  }

    @PostMapping("/{uId}/posts")
    public ResponseEntity<String> addPostByUser(@PathVariable Integer uId, @RequestBody Post post) {
        try {
            Optional<User> optionalUser = userRepository.findById(uId);
            if (optionalUser.isPresent()) {
                User user = optionalUser.get();
                post.setUser(user);
                postRepository.save(post);
                return ResponseEntity.ok("Post added successfully");
            } else {
                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Internal Server Error");
        }
    }

    @DeleteMapping("/{uId}")
    public void deleteUser(@PathVariable Integer uId) {
        userService.deleteUserById(uId);
    }

}
