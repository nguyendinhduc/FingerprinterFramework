package com.uet.fingerpinter.demo;

import com.uet.fingerpinter.db.tables.Building;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class BuildingController {
    @Autowired
    private BookRepository bookRepository;

    @RequestMapping("/getAllBuilding")
    public List<BuidingModel> getAllBuilding() {
        List<BuidingModel> buildingRecords= bookRepository.getDb().selectFrom(Building.BUILDING).fetchInto(BuidingModel.class);



        return buildingRecords;
    }

    public static class BuidingModel{
        private int id;
        private String name;
        private String buildingName;


        public String getBuildingName() {
            return buildingName;
        }

        public void setBuildingName(String buildingName) {
            this.buildingName = buildingName;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
