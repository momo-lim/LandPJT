package io.ssafy.ssafyland.db.repository;

import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import io.ssafy.ssafyland.api.dto.UserDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

import static io.ssafy.ssafyland.db.entity.QUser.user;

public class UserCustomRepositoryImpl implements UserCustomRepository {

    @Autowired
    private JPAQueryFactory jpaQueryFactory;

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public List<UserDto.UserRes> findUserTreasureList() {
        final String sql = "select user_name, treasure_count, forest_record, rank() over(order by treasure_count desc) as lank " +
                "from user " +
                "where treasure_count > 0 " +
                "order by treasure_count desc " +
                "limit 5";

        return jdbcTemplate.query(sql, userResRowMapper);
    }

    @Override
    public List<UserDto.UserRes> findUserForestListByBest() {
        final String sql = "select user_name, treasure_count, forest_record, rank() over(order by forest_record asc) as lank " +
                "from user " +
                "where forest_record > '00:00:00' " +
                "order by forest_record asc " +
                "limit 5";

        return jdbcTemplate.query(sql, userResRowMapper);
    }

    @Override
    public void updateTreasureCountReset() {
        final String sql = "update user set treasure_count = 0 where 1=1";
        jdbcTemplate.update(sql);
    }

    private final RowMapper<UserDto.UserRes> userResRowMapper = (resultSet, rowNum) -> {
        UserDto.UserRes userRes = new UserDto.UserRes();
        userRes.setUserName(resultSet.getString("user_name"));
        userRes.setTreasureCount(resultSet.getInt("treasure_count"));
        userRes.setForestRecord(resultSet.getTime("forest_record").toLocalTime());
        userRes.setLank(resultSet.getInt("lank"));

        return userRes;
    };
}
