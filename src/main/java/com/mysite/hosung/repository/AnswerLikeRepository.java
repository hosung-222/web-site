package com.mysite.hosung.repository;

import com.mysite.hosung.domain.Answer;
import com.mysite.hosung.domain.User;
import com.mysite.hosung.domain.mapping.AnswerLike;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswerLikeRepository extends JpaRepository<AnswerLike, Long> {

    Optional<AnswerLike> findByAnswerAndUser(Answer answer, User user);
}
