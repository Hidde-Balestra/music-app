package teamx.musiccollabbackend.domain.Label;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
public class LabelController {

    ILabelRepository repo;

    @Autowired
    public LabelController(ILabelRepository repo){
        this.repo = repo;
    }


    @GetMapping(value = "/labels/{id}")
    public LabelModel getLabel(@PathVariable int id){
        System.out.println("getLabel " + id);
        return repo.findById(id);
    }


    @GetMapping(value = "/labels")
    public List<LabelModel> getAllLabels(){
        System.out.println("getAllLabels");
        return repo.findAll();
    }


    @GetMapping(value = "/labels/type/{type}")
    public List<LabelModel> getLabelsByType(@PathVariable String type) {
//        System.out.println("getLabelsByType " + type);
        try{
            LabelModel.LabelType labelType = LabelModel.LabelType.valueOf(type.toUpperCase());
            return repo.findByType(labelType);
        } catch (IllegalArgumentException e) {
            return Collections.emptyList();
        }
    }

}
