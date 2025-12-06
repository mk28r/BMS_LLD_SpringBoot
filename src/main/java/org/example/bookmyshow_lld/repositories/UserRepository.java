package org.example.bookmyshow_lld.repositories;

import org.example.bookmyshow_lld.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
