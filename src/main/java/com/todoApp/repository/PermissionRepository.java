package com.todoApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.todoApp.entity.Permission;

public interface PermissionRepository extends JpaRepository<Permission, Integer>{

}
