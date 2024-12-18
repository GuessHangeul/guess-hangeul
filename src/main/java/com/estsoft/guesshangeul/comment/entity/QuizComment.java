package com.estsoft.guesshangeul.comment.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import com.estsoft.guesshangeul.post.entity.QuizPost;
import com.estsoft.guesshangeul.user.entity.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "quiz_comment")
@EntityListeners(AuditingEntityListener.class)
public class QuizComment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "quiz_comment_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;

	@ManyToOne
	@JoinColumn(name = "quiz_post_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private QuizPost post;

	@Column(nullable = false)
	private String content;

	@CreatedDate
	@Column(nullable = false)
	private LocalDateTime createdAt;
}
