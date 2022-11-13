package com.eacuamba.loading_and_sorting.domain.searchable;

import com.eacuamba.loading_and_sorting.domain.model.City;
import com.googlecode.genericdao.search.Filter;
import com.googlecode.genericdao.search.ISearch;
import com.googlecode.genericdao.search.Search;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

@Service
public class CitySearchable implements Searchable<City> {

    @Override
    public Search search(Search search, City city) {
        search.setResultMode(ISearch.RESULT_AUTO);
        search.clearFilters();
        search.addFetch("country");

        if(StringUtils.isNotBlank(city.getName())){
            search.addFilter(Filter.ilike("name", city.getName()));
        }

        return search;
    }
}
