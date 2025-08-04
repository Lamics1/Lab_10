package com.example.jobseekingsystem.Controller;
import com.example.jobseekingsystem.Api.ApiResponse;
import com.example.jobseekingsystem.Model.JobPost;
import com.example.jobseekingsystem.Service.JobPostService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/job-Post")
public class JobPostController {

    private final JobPostService jobPostService;

    @GetMapping("/get")
    public ResponseEntity<?> GetAllJobPost() {
        return ResponseEntity.status(200).body(jobPostService.getAllJobPost());
    }

    @PostMapping("add")
    public ResponseEntity<?> AddJobPost(@RequestBody @Valid JobPost jobPost, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(404).body(errors.getFieldError().getDefaultMessage());
        }
        jobPostService.AddJobPost(jobPost);
        return ResponseEntity.status(200).body(new ApiResponse("JobPost added successfully"));
    }

    @PutMapping("update/{id}")
    public ResponseEntity<?> updateJobPost(@PathVariable Integer id, @RequestBody @Valid JobPost jobPost, Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(404).body(errors.getFieldError().getDefaultMessage());
        }
        Boolean isUpdate = jobPostService.updateJobPost(id,jobPost);
        if (isUpdate) {
            return ResponseEntity.status(200).body(new ApiResponse("JobPost updated successfully"));
        }
        return ResponseEntity.status(404).body(new ApiResponse("JobPost not updated"));
    }

    @DeleteMapping("delete/{id}")
    public ResponseEntity<?>DeleteJobPost(@PathVariable Integer id){
        Boolean isDelete =jobPostService.DeleteJobPost(id);
        if (isDelete){
            return ResponseEntity.status(200).body(new ApiResponse("JobPost Deleted successfully"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("JobPost not Delete"));
    }
}

