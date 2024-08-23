package vn.com.smoke.springlocktransaction.repository;

import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import vn.com.smoke.springlocktransaction.entities.Speaker;

import java.util.Optional;

@Repository
public interface SpeakerRepository extends JpaRepository<Speaker, Long> {

    @Query("SELECT l from Speaker l WHERE UPPER(l.talkName) = UPPER(?1) ")
    @Lock(LockModeType.PESSIMISTIC_WRITE)
    Optional<Speaker> findBySpeakerName(String name);
}
