package io.ssafy.ssafyland.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class Treasure {
    @Id
    @Column(name = "treasure_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long treasureId;
    @Column(name = "treasure_name", nullable = false)
    String treasureName;
    @Column(name = "treasure_code", nullable = false)
    String treasureCode;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id", nullable = true)
    User user;

    @Builder
    public Treasure(String treasureName, String treasureCode) {
        this.treasureName = treasureName;
        this.treasureCode = treasureCode;
    }
}
