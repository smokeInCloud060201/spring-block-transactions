package vn.com.smoke.springlocktransaction.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import vn.com.smoke.springlocktransaction.entities.History;

@Repository
public interface HistoryRepository extends JpaRepository<History, Long> {
}
