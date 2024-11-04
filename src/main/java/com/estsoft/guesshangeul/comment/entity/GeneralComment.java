package com.estsoft.guesshangeul.comment.entity;

import java.time.LocalDateTime;

import com.estsoft.guesshangeul.post.entity.GeneralPost;
import com.estsoft.guesshangeul.users.entity.Users;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "general_comment")
public class GeneralComment {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "general_comment_id", unique = true, nullable = false)
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users users;

	@ManyToOne
	@JoinColumn(name = "general_post_id")
	private GeneralPost post;

	@Column(nullable = false)
	private String content;

	@Column(nullable = false)
	private LocalDateTime createdAt;
}
