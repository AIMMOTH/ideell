package eu.ideell.api.spring.analytics;

import org.springframework.data.jpa.repository.JpaRepository;

public interface AnalyticsRepository extends JpaRepository<AnalyticsRow, Integer> {

}
