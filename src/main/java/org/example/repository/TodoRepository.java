package org.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import org.example.orm.Todo;

@Repository
public interface TodoRepository extends JpaRepository<Todo, Long>{

}
