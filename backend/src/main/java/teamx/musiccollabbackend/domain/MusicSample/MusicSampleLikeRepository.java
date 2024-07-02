package teamx.musiccollabbackend.domain.MusicSample;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MusicSampleLikeRepository extends CrudRepository<MusicSampleLike, MusicSampleUserId> {
    List<MusicSampleLike> findByUserId(int id);

    @NonNull
    List<MusicSampleLike> findAll();

    @Query("SELECT msl FROM MusicSampleLike msl WHERE msl.userId IN :userIds")
    List<MusicSampleLike> findByUserIds(@Param("userIds") List<Integer> userIds);
}

