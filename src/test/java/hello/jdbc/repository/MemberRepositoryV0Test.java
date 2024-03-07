package hello.jdbc.repository;

import hello.jdbc.domain.Member;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.NoSuchElementException;

import static org.assertj.core.api.Assertions.*;

@Slf4j
class MemberRepositoryV0Test {

    MemberRepositoryV0 memberRepositoryV0 = new MemberRepositoryV0();

    @Test
    void crud() throws SQLException {
        // SAVE
        Member member = new Member("memberV0", 10000);
        memberRepositoryV0.save(member);

        // FIND BY ID
        Member findMember = memberRepositoryV0.findById(member.getMemberId());
        log.info("findMember={}", findMember);
        assertThat(findMember).isEqualTo(member);

        // UPDATE: money: 10000 -> 20000
        memberRepositoryV0.update(member.getMemberId(), 20000);
        Member updatedMember = memberRepositoryV0.findById(member.getMemberId());
        assertThat(updatedMember.getMoney()).isEqualTo(20000);

        // DELETE
        memberRepositoryV0.delete(member.getMemberId());
        assertThatThrownBy(() -> memberRepositoryV0.findById(member.getMemberId()))
                .isInstanceOf(NoSuchElementException.class);
    }
}
