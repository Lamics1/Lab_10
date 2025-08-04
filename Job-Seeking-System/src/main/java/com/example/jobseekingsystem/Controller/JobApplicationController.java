package com.example.jobseekingsystem.Controller;
import com.example.jobseekingsystem.Api.ApiResponse;
import com.example.jobseekingsystem.Model.JobApplication;
import com.example.jobseekingsystem.Service.JobApplicationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
@RequiredArgsConstructor
@RestController
@RequestMapping("api/v1/job-application")
public class JobApplicationController {

    private final JobApplicationService jobApplicationService;

    @GetMapping("/get")
    public ResponseEntity<?> GetAllJobApplication() {
        return ResponseEntity.status(200).body(jobApplicationService.getAllJobApplication());
    }

    @PostMapping("apply")
    public ResponseEntity<?> ApplyForJob(@RequestBody @Valid JobApplication jobApplication,@PathVariable Integer userId, Integer jobPostId ,Errors errors) {
        if (errors.hasErrors()) {
            return ResponseEntity.status(404).body(errors.getFieldError().getDefaultMessage());
        }
        Integer job = jobApplicationService.ApplyForJob(jobApplication ,userId, jobPostId);
        if (job == 1) {
            return ResponseEntity.status(404).body(new ApiResponse("userId not found"));
        }
        else if (job ==2){
            return ResponseEntity.status(404).body(new ApiResponse("jobPostId not found"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("JobApplication added successfully"));
    }


    @DeleteMapping("delete/{id}")
    public ResponseEntity<?>WithdrawJobApplication(@PathVariable Integer id) {
        Boolean isDelete = jobApplicationService.WithdrawJobApplication(id);
        if (isDelete) {
            return ResponseEntity.status(200).body(new ApiResponse("JobApplication Deleted successfully"));
        }
        return ResponseEntity.status(200).body(new ApiResponse("JobApplication not Delete"));
    }
    }


