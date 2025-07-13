package com.devsuperior.aula_m4.services;

import org.springframework.stereotype.Service;

import com.devsuperior.aula_m4.dto.PersonDTO;
import com.devsuperior.aula_m4.dto.PersonDepartmentDTO;
import com.devsuperior.aula_m4.entities.Department;
import com.devsuperior.aula_m4.entities.Person;
import com.devsuperior.aula_m4.repositories.DepartmentRepository;
import com.devsuperior.aula_m4.repositories.PersonRepository;

import jakarta.transaction.Transactional;

@Service
public class PersonService {

	private PersonRepository repository;
	private DepartmentRepository departmentRepository;
	public PersonService(PersonRepository repository, DepartmentRepository departmentRepository) {
		this.repository = repository;
		this.departmentRepository = departmentRepository;
	}
	
	@Transactional
	public PersonDTO insert(PersonDTO dto) {
		Person entity = new Person();
		entity.setName(dto.getName());
		entity.setSalary(dto.getSalary());
		Department dept = departmentRepository.getReferenceById(dto.getDepartmentId()); 
		entity.setDepartment(dept);
		entity = repository.save(entity);
		return new PersonDTO(entity);
	}
	
	@Transactional
	public PersonDepartmentDTO insert(PersonDepartmentDTO dto) {
		Person entity = new Person();
		entity.setName(dto.getName());
		entity.setSalary(dto.getSalary());
		//Aqui ele gera um objeto departamento fake(Ele ainda não fez o select).
		//Se ussase o findById() ele iria buscar no banco um objeto que já sei que existe no banco.
		Department dept = departmentRepository.getReferenceById(dto.getDepartment().getId()); 
		entity.setDepartment(dept);
		//(Aqui ele salva e já retorna o objeto salvo)
		entity = repository.save(entity);
		return new PersonDepartmentDTO(entity);
	}
}
