package io.ssafy.ssafyland.db.entity;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Time;
import java.time.LocalTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
public class User {
    @Id
    @Column(name = "user_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long userId;
    @Column(name = "user_name", nullable = false, unique = true)
    String userName;
    @Column(name = "treasure_count", nullable = false)
    int treasureCount = 0;
    @Column(name = "forest_record", nullable = true, columnDefinition = "TIME")
    Time forestRecord;

    public void increaseTreasureCount() {
        treasureCount++;
    }

    public void recordForest(LocalTime forestRecord) {
        if (this.forestRecord.equals(new Time(0, 0, 0))) {
            setForestRecord(Time.valueOf(forestRecord));
            return;
        }
        if (forestRecord.isBefore(this.forestRecord.toLocalTime())) {
            setForestRecord(Time.valueOf(forestRecord));
        }
    }

    @PrePersist
    public void prePersist() {
        this.forestRecord = this.forestRecord == null ? new Time(0, 0, 0) : this.forestRecord;
    }

    @Builder
    public User(String userName) {
        this.userName = userName;
    }
}
