package teamx.musiccollabbackend.domain.User;

import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;
import teamx.musiccollabbackend.domain.dto.user.UserWithReports;

import java.util.List;
import java.util.Optional;

@Repository
public interface IUserRepository extends CrudRepository<UserModel, Integer> {

    UserModel findById(int idToFind);

    Optional<UserModel> findUserByEmail(String email);

    List<UserModel> findAllByContactInfoIsContainingIgnoreCase(String val);

    List<UserModel> findAllByCountryContainingIgnoreCase(String val);

    List<UserModel> findAllByFirstNameContainingIgnoreCase(String val);

    List<UserModel> findAllByLastNameContainingIgnoreCase(String val);

    @Query("SELECT DISTINCT new teamx.musiccollabbackend.domain.dto.user.UserWithReports(user) " +
            "FROM UserModel user " +
            "LEFT JOIN FETCH user.reports report " +
            "WHERE SIZE(report) > 0")
    List<UserWithReports> findReported();


    @Transactional
    @Modifying
    @Query("UPDATE UserModel u SET u.active = :active WHERE u.idUser = :userId")
    void setActiveStatus(int userId, int active);

    //<S extends UserModel> S save(@NonNull UserModel toSave);

    //UserModel delete(int id);

    //UserModel delete(int idToDelete);

    @NonNull
    List<UserModel> findAll();
}
