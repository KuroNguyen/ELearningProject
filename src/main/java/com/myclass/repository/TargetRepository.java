package com.myclass.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myclass.entity.Target;

/**
 * Author: Nguyen Chanh Truc
 * Created: Feb 7, 2021	
 */
public interface TargetRepository extends JpaRepository<Target, Integer>{
	
}
