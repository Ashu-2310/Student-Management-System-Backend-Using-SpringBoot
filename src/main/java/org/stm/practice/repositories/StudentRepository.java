package org.stm.practice.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.stm.practice.entities.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {
}
