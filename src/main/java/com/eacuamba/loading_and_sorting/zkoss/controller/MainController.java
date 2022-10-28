package com.eacuamba.loading_and_sorting.zkoss.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(path = "/cities")
    public String cities(){
        return "view/Cities";
    }

    @GetMapping(path = "/cities-paginated")
    public String citiesPaginated(){
        return "view/CitiesPaginated";
    }
}
