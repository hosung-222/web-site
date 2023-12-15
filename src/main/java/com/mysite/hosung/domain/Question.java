package com.mysite.hosung.domain;

import com.mysite.hosung.domain.common.BaseEntity;
import com.mysite.hosung.domain.mapping.QuestionLike;
import com.mysite.hosung.web.dto.QuestionRequestDTO.QuestionFormDTO;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Question extends BaseEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(length = 200)
    private String subject;

    @Column(columnDefinition = "TEXT")
    private String content;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answerList;

    @ManyToOne
    private User author;

    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<QuestionLike> questionLikes;

    public void modify(QuestionFormDTO questionFormDTO){
        this.subject = questionFormDTO.getSubject();
        this.content = questionFormDTO.getContent();
    }
}
