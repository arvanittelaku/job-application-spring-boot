package com.example.demo1.services;

import com.example.demo1.dtos.job.JobCreateDto;
import com.example.demo1.dtos.job.JobDeleteDto;
import com.example.demo1.dtos.job.JobSearchDto;
import com.example.demo1.dtos.job.JobUpdateDto;
import com.example.demo1.models.Job;
import com.example.demo1.services.baseService.Addable;
import com.example.demo1.services.baseService.Findable;
import com.example.demo1.services.baseService.Modifiable;
import com.example.demo1.services.baseService.Removable;

public interface JobServices extends Findable<JobSearchDto, Job>, Removable<JobDeleteDto>,
        Addable<JobCreateDto,Job>, Modifiable<JobUpdateDto,Job>

{



}
