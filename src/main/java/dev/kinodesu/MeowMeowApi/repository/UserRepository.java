package dev.kinodesu.MeowMeowApi.repository;

import dev.kinodesu.MeowMeowApi.model.user.MeowUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<MeowUser, Long> {
}
