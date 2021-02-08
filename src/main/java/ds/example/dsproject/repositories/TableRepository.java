package ds.example.dsproject.repositories;

import ds.example.dsproject.entities.TableIT;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


public interface TableRepository extends JpaRepository<TableIT, Long> {
}
