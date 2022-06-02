package com.mns.locmns.controller;

import com.mns.locmns.dao.EtatMaterielDao;
import com.mns.locmns.dao.ModeleDao;
import com.mns.locmns.model.EtatMateriel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class EtatMaterielController {
    @Autowired
    private EtatMaterielDao etatMaterielDao;
}
