package com.example.jobseekingsystem.Service;

import com.example.jobseekingsystem.Model.JobApplication;
import com.example.jobseekingsystem.Model.JobPost;
import com.example.jobseekingsystem.Model.User;
import com.example.jobseekingsystem.Repository.JobApplicationRepository;
import com.example.jobseekingsystem.Repository.JobPostRepository;
import com.example.jobseekingsystem.Repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class JobApplicationService {

    private final JobPostRepository jobPostRepository;
    private final UserRepository userRepository;
    private final JobApplicationRepository jobApplicationRepository;

    public List<JobApplication> getAllJobApplication() {
        return jobApplicationRepository.findAll();
    }

    public Integer ApplyForJob(JobApplication jobApplication,Integer userId, Integer jobPostId) {
        User user = userRepository.getById(userId);
        if (user == null) {
            return 1;
        }
        JobPost jobPost = jobPostRepository.getById(jobPostId);
        if (jobPost == null) {
            return 2;

        }
        jobApplicationRepository.save(jobApplication);
        return 3;
    }


    public Boolean WithdrawJobApplication(Integer id) {
        JobApplication jobApplication = jobApplicationRepository.findById(id).orElse(null);
        if (jobApplication == null) {
            return false;
        }
        jobApplicationRepository.delete(jobApplication);
        return true;
    }

    }


//    public Boolean updateJopApplication(Integer id, JobApplication jobApplication) {
//        JobApplication oldjobApplication = jobApplicationRepository.getById(id);
//        if (jobApplication == null) {
//            return false;
//        }
//        oldjobApplication.setUserID(id);
//        oldjobApplication.setJobPostId(id);
//        jobApplicationRepository.save(oldjobApplication);
//        return true;
//    }
//
//}


