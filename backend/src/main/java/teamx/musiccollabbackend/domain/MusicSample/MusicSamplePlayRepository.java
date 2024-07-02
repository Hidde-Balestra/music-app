package teamx.musiccollabbackend.domain.MusicSample;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MusicSamplePlayRepository extends CrudRepository<MusicSamplePlay, MusicSampleUserId> {

    @Query("SELECT msl FROM MusicSamplePlay msl WHERE msl.userId IN :userIds")
    List<MusicSamplePlay> findByUserIds(@Param("userIds") List<Integer> userIds);
}
