// src/main/java/com/estsoft/guesshangeul/post/entity/GeneralPost.java
package com.estsoft.guesshangeul.post.entity;

import com.estsoft.guesshangeul.board.entity.GeneralBoard;
import com.estsoft.guesshangeul.user.entity.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;


import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@Table(name = "general_post")
@EntityListeners(AuditingEntityListener.class)
public class GeneralPost {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "general_post_id")
	private Long id;

	@ManyToOne
	@JoinColumn(name = "user_id", nullable = false)
	private Users users;

	@ManyToOne
	@JoinColumn(name = "board_id", nullable = false)
	private GeneralBoard generalBoard;

	private String title;
	private String content;
	private boolean isHidden;
	private Long view;
	private LocalDateTime createdAt;
	private LocalDateTime updatedAt;
}