package com.myProject.banking.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.myProject.banking.entity.Account;

public interface AccountRepository extends JpaRepository<Account, Long> {

}
