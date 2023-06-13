package jpabasic.reserve.domain;

import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name = "access_log")
public class AccessLog {
    @Id
    @TableGenerator(name = "accessIdGen", table = "id_seq", pkColumnName = "entity", pkColumnValue = "accesslog", valueColumnName = "nextval", initialValue = 0,
            // 다중node 상황 시 문제 발생 가능성이 있기 때문에 1로 설정
            allocationSize = 1)
    @GeneratedValue(generator = "accessIdGen")
    private Long id;
    private String path;
    private LocalDateTime accessed;

    protected AccessLog() {
    }

    public AccessLog(String path, LocalDateTime accessed) {
        this.path = path;
        this.accessed = accessed;
    }

    public Long getId() {
        return id;
    }

    public String getPath() {
        return path;
    }

    public LocalDateTime getAccessed() {
        return accessed;
    }
}
