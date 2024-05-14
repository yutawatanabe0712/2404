package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.LeavingRegisterEntity;

/**
 * 勤怠情報 Repository
 */
@Repository
public  interface LeavingRegisterRepository extends JpaRepository<LeavingRegisterEntity, Integer> {

}
