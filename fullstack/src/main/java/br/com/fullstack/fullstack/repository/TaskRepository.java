package br.com.fullstack.fullstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import br.com.fullstack.fullstack.model.Task;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    
}