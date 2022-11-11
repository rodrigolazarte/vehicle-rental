package com.mlazarte.vehiclerental.repositories;

import com.mlazarte.vehiclerental.models.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findByPhoneNumber(String phoneNumber);

    Optional<Client> findByFirstNameAndLastName(String firstName, String lastName);
}
