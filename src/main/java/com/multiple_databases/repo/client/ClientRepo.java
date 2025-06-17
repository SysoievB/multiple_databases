package com.multiple_databases.repo.client;

import com.multiple_databases.entity.client.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepo extends JpaRepository<Client, Long> {
}
