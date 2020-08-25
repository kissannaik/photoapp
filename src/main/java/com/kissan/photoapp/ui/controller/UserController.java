package com.kissan.photoapp.ui.controller;


import com.kissan.photoapp.exception.UserException;
import com.kissan.photoapp.service.UserService;
import com.kissan.photoapp.ui.model.request.UserRequest;
import com.kissan.photoapp.ui.model.response.UserResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;


@RestController
@RequestMapping("/users")
public class UserController {
    //private Map<String, UserResponse> users;
    @Autowired
    private UserService userService;

    @GetMapping
    public ResponseEntity<UserResponse[]> getUsers(@RequestParam(value = "page", defaultValue = "1") int page,
                           @RequestParam(value = "limit", defaultValue = "50") int limit,
                           @RequestParam(value = "sort", defaultValue = "desc", required = false) String sort){
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping(value = "/{userId}",
                produces = {MediaType.APPLICATION_XML_VALUE,
                            MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponse> getUser(@PathVariable("userId") String userId){
        System.out.println("userId in getUser = " + userId);
        UserResponse userResponse = userService.getUser(userId);
        System.out.println("userResponse in getUser = " + userResponse);
        if(userResponse !=null){
            return new ResponseEntity<>(userResponse, HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @PostMapping(
            consumes = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE}
            )
    public ResponseEntity<UserResponse> addUser(@RequestBody UserRequest userRequest){
        System.out.println("userRequest in adduser = " + userRequest);
        UserResponse userResponse = userService.createUser(userRequest);
        System.out.println("userRequest in adduser = " + userRequest);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @PutMapping(value = "/{userId}",
            consumes = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_XML_VALUE,
                    MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<UserResponse> updateUser(@PathVariable String userId, @RequestBody UserRequest userRequest){

        UserResponse storedUser = userService.updateUser(userId, userRequest);

        if(storedUser != null)
            return ResponseEntity.ok().body(storedUser);

        return ResponseEntity.notFound().build();
    }

    @DeleteMapping(value = "/{userId}")
    public ResponseEntity<Void> deleteUser(@PathVariable String userId){
        if(userService.deleteUser(userId))
            return ResponseEntity.ok().build();

        return ResponseEntity.noContent().build();
    }
}
