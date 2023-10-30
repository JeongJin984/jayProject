package com.jay.transactserver.data.repo

import com.jay.transactserver.data.entity.Transact
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface TransactRepository : JpaRepository<Transact, String> {
}