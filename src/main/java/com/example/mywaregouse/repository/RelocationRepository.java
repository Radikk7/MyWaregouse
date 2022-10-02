package com.example.mywaregouse.repository;

import com.example.mywaregouse.models.Relocation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RelocationRepository extends JpaRepository<Relocation,Long> {
}
