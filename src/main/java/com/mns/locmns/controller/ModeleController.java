package com.mns.locmns.controller;

import com.mns.locmns.dao.ModeleDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class ModeleController {
    private ModeleDao modeleDao;

    @Autowired
    public ModeleController(ModeleDao modeleDao){this.modeleDao=modeleDao;}
}
