package jpabasic.reserve.domain;

import org.springframework.data.domain.Persistable;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@Entity
@Table(name = "user")
public class User implements Persistable<String> {

    public int solution(String[] want, int[] number, String[] discount) {
        int answer = 0;
        int cnt = 0;
        int k = 0;
        HashMap<String, Integer> map1 = new HashMap<>();

        for (int i = 0; i < want.length; i++) {
            map1.put(want[i], number[i]);
        }

        for (int i = 0; i < discount.length - 9; i++) {
            HashMap<String, Integer> map = new HashMap<>();
            for (int j = i; j < i + 10; j++) {
                if (!(map.get(discount[j]) == null)) {
                    map.put(discount[j], map.get(discount[j]) + 1);
                } else {
                    map.put(discount[j], 1);
                }

            }
            if (map.equals(map1)) {
                answer++;
            }
        }
        System.out.println(map1);

        // 할인하는 제품은 하루에 하나씩만 구매할 수 있습니다
        return answer;
    }

    @Id
    private String email;
    private String name;

    @Column(name = "create_date")
    private LocalDateTime createDate;

    @Transient
    private boolean isNew = true;

    protected User() {
    }

    public User(String email, String name, LocalDateTime createDate) {
        this.email = email;
        this.name = name;
        this.createDate = createDate;
    }

    public String getEmail() {
        return email;
    }

    public String getName() {
        return name;
    }

    public LocalDateTime getCreateDate() {
        return createDate;
    }

    public void changeName(String newName) {
        this.name = newName;
    }

    @Override
    public String getId() {
        return email;
    }

    @Override
    public boolean isNew() {
        return isNew;
    }

    @PostLoad
    @PrePersist
    void markNotNew() {
        this.isNew = false;
    }
}