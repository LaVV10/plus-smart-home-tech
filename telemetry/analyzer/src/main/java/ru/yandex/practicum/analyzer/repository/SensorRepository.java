package ru.yandex.practicum.analyzer.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import ru.yandex.practicum.analyzer.model.Sensor;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

public interface SensorRepository extends JpaRepository<Sensor, String> {
    boolean existsByIdInAndHubId(Collection<String> ids, String hubId);

    @Query("SELECT s FROM Sensor s WHERE s.hubId = :hubId AND s.id IN :ids")
    List<Sensor> findByHubIdAndIdIn(@Param("hubId") String hubId, @Param("ids") Collection<String> ids);

    Optional<Sensor> findByIdAndHubId(String id, String hubId);
}
