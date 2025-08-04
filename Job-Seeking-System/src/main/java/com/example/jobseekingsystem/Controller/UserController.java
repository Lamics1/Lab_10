package com.example.jobseekingsystem.Controller;

import com.example.jobseekingsystem.Api.ApiResponse;
import com.example.jobseekingsystem.Model.User;
import com.example.jobseekingsystem.Service.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/user")
public class UserController {

private final UserService userService;

    @GetMapping("/get")
    public ResponseEntity<?> GetAllUsers() {
        return ResponseEntity.status(200).body(userService.getAllUsers());
    }

    @PostMapping("add")
    public ResponseEntity<?> AddUser(@RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(404).body(errors.getFieldError().getDefaultMessage());
        }
        userService.AddUser(user);
        return ResponseEntity.status(200).body(new ApiResponse("User added successfully"));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateUser(@PathVariable Integer id, @RequestBody @Valid User user, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(404).body(errors.getFieldError().getDefaultMessage());
        }
        Boolean isUpdate = userService.updateUser(id,user);
        if (isUpdate) {
            return ResponseEntity.status(200).body(new ApiResponse("User updated successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("User not updated"));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?>DeleteUser(@PathVariable Integer id){
        Boolean isDelete =userService.DeleteUser(id);
        if (isDelete){
            return ResponseEntity.status(200).body(new ApiResponse("User Deleted successfully"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("User not Delete"));
    }
}
