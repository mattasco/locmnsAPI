package com.mns.locmns.controller;

import com.mns.locmns.dao.ModeleDao;
import com.mns.locmns.dao.TypeMaterielDao;
import com.mns.locmns.model.TypeMateriel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class TypeMaterielController {
    private TypeMaterielDao typeMaterielDao;

    @Autowired
    public TypeMaterielController(TypeMaterielDao typeMaterielDao){this.typeMaterielDao=typeMaterielDao;}
}
