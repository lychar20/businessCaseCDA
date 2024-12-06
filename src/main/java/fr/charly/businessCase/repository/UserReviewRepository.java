package fr.charly.businessCase.repository;

import fr.charly.businessCase.entity.UserReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserReviewRepository extends JpaRepository<UserReview, Long> {
}