package com.example.demo1.repositories;

import com.example.demo1.models.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {
    List<Company> findAll();
    Optional<Company> findById(long id);
    Optional<Company> findByCompanyName(String companyName);
    Optional<Company> findByAddress(String companyEmail);
    Optional<Company> findByPhone(String companyPhone);
    Optional<Company> findByEmail(String companyEmail);
}




