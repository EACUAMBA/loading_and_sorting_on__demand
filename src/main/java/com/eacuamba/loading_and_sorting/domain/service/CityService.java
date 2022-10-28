package com.eacuamba.loading_and_sorting.domain.service;

import com.eacuamba.loading_and_sorting.domain.model.City;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import org.springframework.stereotype.Service;

@Service
public class CityService implements Searchable<City> {

    @Override
    public Search search(Search search, City city) {
        search.setResultMode(ISearch.RESULT_AUTO);
        search.clearFilters();

        //Filter goes here

        return search;
    }
}
