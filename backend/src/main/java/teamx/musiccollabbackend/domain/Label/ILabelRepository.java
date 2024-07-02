package teamx.musiccollabbackend.domain.Label;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ILabelRepository extends CrudRepository<LabelModel, Integer> {

    LabelModel findById(int idToFind);

    @NonNull
    List<LabelModel> findAll();

    List<LabelModel> findByType(LabelModel.LabelType type);

    @Query("SELECT l FROM LabelModel l WHERE l.id IN :ids")
    List<LabelModel> findByIds(@Param("ids") List<Integer> idsToFind);

}
