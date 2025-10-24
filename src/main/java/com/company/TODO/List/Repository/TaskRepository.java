package com.company.TODO.List.Repository;

import com.company.TODO.List.Model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TaskRepository extends JpaRepository<Task, Long>{

    boolean existsByTitle(String title);
}