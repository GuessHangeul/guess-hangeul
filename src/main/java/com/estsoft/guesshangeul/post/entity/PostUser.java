package com.estsoft.guesshangeul.post.entity;

import com.estsoft.guesshangeul.user.entity.Users;

import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "post_user")
public class PostUser {
	@EmbeddedId
	private PostUserId postUserId;

	@MapsId("quizPostId")
	@ManyToOne
	@JoinColumn(name = "quiz_post_id")
	private QuizPost quizPost;

	@MapsId("userId")
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Users user;

	public PostUser(QuizPost quizPost, Users user) {
		this.quizPost = quizPost;
		this.user = user;
		this.postUserId = new PostUserId(quizPost.getId(), user.getId());
	}
}
