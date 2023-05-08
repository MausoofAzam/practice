package com.snort.practice.entity;

import com.snort.practice.request.QuestionRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "question")
public class Question {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String questionType;
    @OneToOne
    @JoinColumn(name = "options_id")
    private Option options;
    private Integer totalMarks;
    private String category;
    private String level;
    private String questionDescription;
    @Transient
    private Boolean isCorrectAnswer;
    public Question(QuestionRequest questionRequest) {
    }
}