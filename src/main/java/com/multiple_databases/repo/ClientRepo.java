package com.multiple_databases.repo;

import com.multiple_databases.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Long> {
}
