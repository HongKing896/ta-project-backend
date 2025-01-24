package com.example.backend.signatureRequest.entity;

import javax.persistence.*;

import com.example.backend.document.entity.Document;
import com.example.backend.member.entity.Member;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "signature_request")
public class SignatureRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id")
    private Long requestId; // 서명 요청 ID (Primary Key)

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "document_id", nullable = false)
    private Document document; // 문서 ID (외래 키)

    @Column(nullable = false, length = 50)
    private String signerName;

    @Column(nullable = false, length = 50)
    private String signerEmail;

    @Column(name = "token", length = 255, nullable = false)
    private String token; // 서명 요청 URL 토큰

    @Column(name = "created_at", nullable = false, updatable = false)
    private LocalDateTime createdAt; // 요청 생성 시각

    @Column(name = "expired_at", nullable = false)
    private LocalDateTime expiredAt; // 요청 만료 시각

    @Column(name = "status", nullable = false)
    private int status; // 요청 상태 (0: 대기 중, 1: 완료, 2: 거절됨)

    @PrePersist
    protected void onCreate() {
        this.createdAt = LocalDateTime.now();
    }
}
