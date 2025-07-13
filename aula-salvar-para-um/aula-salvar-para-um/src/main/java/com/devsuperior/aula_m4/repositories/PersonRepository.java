package com.devsuperior.aula_m4.repositories;

import com.devsuperior.aula_m4.entities.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
