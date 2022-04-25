package com.mns.locmns.controller;

import com.mns.locmns.dao.MarqueDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin
@RestController
public class MarqueController {
    private MarqueDao marqueDao;

    @Autowired
    public MarqueController(MarqueDao marqueDao){this.marqueDao=marqueDao;}
}
