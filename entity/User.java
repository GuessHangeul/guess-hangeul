package DTO.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.ColumnDefault;

import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", unique = true, nullable = false)
    private Long id;

    @Column(name = "email",nullable = false)
    private String email;

    @Column(name = "password", nullable = false)
    private String password;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "user_rank", nullable = false)
    @ColumnDefault("0") //디폴트 값 0으로 지정
    private int userrank;

    @Column(nullable = false)
    @ColumnDefault("0")
    private int score;

    @Column(name = "create_at", nullable = false)
    private LocalDateTime createdAt;

    @Column(name = "connected_at", nullable = false)
    private LocalDateTime connectedAt;

    @Column(name = "connect_count", nullable = false)
    private int connectCount;

    @Column(name = "user_agent_value", nullable = false)
    private String userAgent;

    @Column(name = "is_deleted", nullable = false)
    @ColumnDefault("false")
    private boolean isDeleted;
}
