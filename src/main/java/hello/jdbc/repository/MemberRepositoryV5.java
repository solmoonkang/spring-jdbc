package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;

/**
 * JdbcTemplate 사용
 */
@Slf4j
public class MemberRepositoryV5 implements MemberRepository {

    private final JdbcTemplate jdbcTemplate;

    public MemberRepositoryV5(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    // SAVE() ...
    @Override
    public Member save(Member member) {
        String sql = "insert into member(member_id, money) values(?, ?)";
        jdbcTemplate.update(sql, member.getMemberId(), member.getMoney());
        return member;
    }

    // FINDBYID() ...
    @Override
    public Member findById(String memberId) {
        String sql = "select * from member where member_id = ?";
        return jdbcTemplate.queryForObject(sql, memberRowMapper(), memberId);
    }


    // UPDATE() ...
    @Override
    public void update(String memberId, int money) {
        String sql = "update member set money=? where member_id=?";
        jdbcTemplate.update(sql, money, memberId);
    }

    // DELETE() ...
    @Override
    public void delete(String memberId) {
        String sql = "delete from member where member_id=?";
        jdbcTemplate.update(sql, memberId);
    }

    private RowMapper<Member> memberRowMapper() {
        return (resultSet, rowNumber) -> {
            Member member = new Member();
            member.setMemberId(resultSet.getString("member_id"));
            member.setMoney(resultSet.getInt("money"));
            return member;
        };
    }
}
