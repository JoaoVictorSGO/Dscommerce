package com.devsuperior.aula_m4.repositories;

import com.devsuperior.aula_m4.entities.Department;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {
}
