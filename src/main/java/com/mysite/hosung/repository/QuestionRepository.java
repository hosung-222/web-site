package com.mysite.hosung.repository;

import com.mysite.hosung.domain.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question ,Long> {
}
