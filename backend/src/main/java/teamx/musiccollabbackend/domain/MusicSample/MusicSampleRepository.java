package teamx.musiccollabbackend.domain.MusicSample;

import jakarta.transaction.Transactional;
import lombok.NonNull;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import teamx.musiccollabbackend.domain.dto.sample.MusicSampleWithReports;

import java.time.LocalDateTime;

/**
 * Repository interface for managing MusicSample records in the database.
 * Provides CRUD operations and custom query methods for retrieving music samples.
 *
 * @author Djangesh Sardjoe
 * @version 1.0
 * @since 2023-12-10
 */
@Repository
public interface MusicSampleRepository extends CrudRepository<MusicSampleModel, Integer> {
    // Inherits CrudRepository functionalities

    Iterable<MusicSampleModel> findAllByIdUser(int userId);

    //This method follows the JPA naming convention resulting in the return of a single MusicSampleModel
    //corresponding with an userId and sampleName
    MusicSampleModel findByIdUserAndSampleName(int userId, String sampleName);

    MusicSampleModel findById(int idToFind);



    @NonNull
    List<MusicSampleModel> findAll();

    List<MusicSampleModel> findAllBySampleDescriptionContainingIgnoreCase(String query);
    List<MusicSampleModel> findAllBySampleDescriptionContainingIgnoreCaseAndActive(String query, int active);


    List<MusicSampleModel> findAllBySampleNameContainingIgnoreCase(String query);
    List<MusicSampleModel> findAllBySampleNameContainingIgnoreCaseAndActive(String query, int active);

    @Query("SELECT ms " +
            "FROM MusicSampleModel ms LEFT JOIN ms.plays play " +
            "WHERE play.datePlayed BETWEEN :start AND :end " +
            "AND ms.active = 1 " +
            "GROUP BY ms " +
            "ORDER BY COUNT(play) DESC")
    List<MusicSampleModel> findMostPlayedInPeriod(
            @Param("start") LocalDateTime start,
            @Param("end") LocalDateTime end
    );

    @Query("SELECT ms " +
            "FROM MusicSampleModel ms LEFT JOIN ms.plays play " +
            "WHERE play.datePlayed IS NOT NULL " +
            "AND ms.active = 1 " +
            "ORDER BY play.datePlayed DESC")
    List<MusicSampleModel> findRecentlyPlayed();

    @Query("SELECT DISTINCT new teamx.musiccollabbackend.domain.dto.sample.MusicSampleWithReports(ms) " +
            "FROM MusicSampleModel ms " +
            "LEFT JOIN FETCH ms.reports report " +
            "WHERE SIZE(report) > 0")
    List<MusicSampleWithReports> findReported();


    @Transactional
    @Modifying
    @Query("UPDATE MusicSampleModel ms SET ms.active = :active WHERE ms.idMusicSample = :sampleId")
    void setActiveStatus(int sampleId, int active);
}
