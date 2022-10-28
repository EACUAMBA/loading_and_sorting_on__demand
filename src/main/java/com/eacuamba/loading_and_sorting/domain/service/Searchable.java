package com.eacuamba.loading_and_sorting.domain.service;

import com.googlecode.genericdao.search.Search;

public interface Searchable<T> {
    Search search(Search search, T t);
}
