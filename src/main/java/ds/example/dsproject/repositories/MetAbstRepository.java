package ds.example.dsproject.repositories;

import ds.example.dsproject.entities.Met;
import org.springframework.data.jpa.repository.JpaRepository;


public interface MetAbstRepository<T extends Met> extends JpaRepository<T, Long> {

}
