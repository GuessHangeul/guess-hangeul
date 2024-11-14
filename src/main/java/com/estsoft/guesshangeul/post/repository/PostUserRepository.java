package com.estsoft.guesshangeul.post.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.estsoft.guesshangeul.post.entity.PostUser;
import com.estsoft.guesshangeul.post.entity.QuizPost;
import com.estsoft.guesshangeul.user.entity.Users;

@Repository
public interface PostUserRepository extends JpaRepository<PostUser, Long> {
	Optional<PostUser> findByQuizPostAndUser(QuizPost quizPost, Users user);
}
