package ds.example.dsproject.repositories;

import ds.example.dsproject.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


public interface ClientRepository extends JpaRepository<Client, Long > {

}
