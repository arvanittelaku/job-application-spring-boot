package com.example.demo1.mappers;

import com.example.demo1.dtos.company.*;
import com.example.demo1.infrastructure.SimpleMapper;
import com.example.demo1.models.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    Company fromRegisterToEntity (CompanyRegisterDto companyRegisterDto);

    CompanyViewDto toViewDto(Company company);

    List<CompanyViewDto> toViewDtoList(List<Company> companies);

    List<CompanyProfileDto> toDtoList(List<Company> companies);

    @Mapping(source = "industry", target = "industry")
    Company toEntity(CompanyRegisterDto companyRegisterDto);

    Company fromUpdateToEntity (CompanyUpdateDto companyUpdateDto);

    CompanyProfileDto toProfileDto(Company company);

    CompanyProfileDto toProfileFromLogin(CompanyLoginDto companyLoginDto);

    CompanyRegisterDto toRegisterDto(Company company);
}
