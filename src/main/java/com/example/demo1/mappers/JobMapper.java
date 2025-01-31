package com.example.demo1.mappers;

import com.example.demo1.dtos.job.*;
import com.example.demo1.infrastructure.SimpleMapper;
import com.example.demo1.models.Job;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface JobMapper extends SimpleMapper<Job, JobDetailsDto> {


    Job fromCreateToEntity(JobCreateDto jobCreateDto);

    JobDetailsDto fromEntityToDetailsDto(Job job);

    Job fromDetailsToEntity(JobDetailsDto jobDetailsDto);

    Job fromUpdateToEntity(JobUpdateDto jobUpdateDto);

    JobUpdateDto fromEntityToUpdateDto(Job job);

    Job fromSearchToEntity(JobSearchDto jobSearchDto);

    JobSearchDto fromEntityToSearchDto(Job job);

    Job fromDeleteToEntity(JobDeleteDto jobDeleteDto);

    JobDeleteDto fromEntityToDeleteDto(Job job);

    JobUpdateDto toUpdateDto(Job job);


}
