package com.example.jobseekingsystem.Service;

import com.example.jobseekingsystem.Model.JobPost;
import com.example.jobseekingsystem.Model.User;
import com.example.jobseekingsystem.Repository.JobPostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class JobPostService {

private final JobPostRepository jobPostRepository;

    public List<JobPost> getAllJobPost() {
        return jobPostRepository.findAll();
    }

    public void AddJobPost(JobPost jobPost) {
        jobPostRepository.save(jobPost);

    }

    public Boolean updateJobPost(Integer id, JobPost jobPost) {
        JobPost oldJobPost = jobPostRepository.getById(id);
        if (jobPost == null) {
            return false;
        }
        oldJobPost.setTitle(jobPost.getTitle());
        oldJobPost.setSalary(jobPost.getSalary());
        oldJobPost.setDescription(jobPost.getDescription());
        oldJobPost.setLocation(jobPost.getLocation());
        oldJobPost.setPostingDate(jobPost.getPostingDate());
        jobPostRepository.save(oldJobPost);
        return true;
    }

    public Boolean DeleteJobPost(Integer id) {
        JobPost oldJobPost = jobPostRepository.getById(id);
        if (oldJobPost == null) {
            return false;
        }
        jobPostRepository.delete(oldJobPost);
        return true;
    }

}
