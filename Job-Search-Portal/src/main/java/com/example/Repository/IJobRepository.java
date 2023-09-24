package com.example.Repository;

import com.example.Entity.Job;
import com.example.Entity.JobType;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface IJobRepository extends CrudRepository<Job, Long> {


    List<Job> findByJobType(JobType jobType);
    List<Job> findByJobTypeAndCompanyName(JobType jobType , String companyName);
    List<Job> findByJobTypeOrderBySalaryDesc(JobType jobType);
    List<Job> findByLocation(String location);


    @Query(value = "select * from jobs" , nativeQuery = true)
    List<Job> getAllJobs();

    @Query(value = "select * from jobs where salary > :salary" , nativeQuery = true)
    List<Job> getJobBySalary(Double salary);
    @Modifying
    @Transactional
    @Query(value = "delete from jobs where job_id = :id" , nativeQuery = true)
    void deleteJob(Long id);

}
