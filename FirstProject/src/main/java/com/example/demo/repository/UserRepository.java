package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long>
{
	 // Custom query to find the smallest missing ID
    @Query(value = """
        SELECT MIN(t1.id + 1) 
        FROM users t1 
        LEFT JOIN users t2 ON t1.id + 1 = t2.id 
        WHERE t2.id IS NULL
    """, nativeQuery = true)
    Optional<Long> findNextAvailableId();
}
