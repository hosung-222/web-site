package com.mysite.hosung.repository;

import com.mysite.hosung.domain.Question;
import com.mysite.hosung.domain.User;
import com.mysite.hosung.domain.mapping.QuestionLike;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionLikeRepository extends JpaRepository<QuestionLike, Long> {
    Optional<QuestionLike> findByQuestionAndUser(Question question, User user);
}
