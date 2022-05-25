package com.mns.locmns.controller;

import com.mns.locmns.dao.TypeMaterielDao;
import com.mns.locmns.model.TypeMateriel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
public class  TypeMaterielController {
    private TypeMaterielDao typeMaterielDao;

    @Autowired
    public TypeMaterielController(TypeMaterielDao typeMaterielDao){this.typeMaterielDao=typeMaterielDao;}

    @GetMapping("/liste-type-materiel")
    public List<TypeMateriel> listeMateriel () {
        return this.typeMaterielDao.findAll();
    }
}