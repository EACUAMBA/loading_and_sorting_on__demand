package com.eacuamba.loading_and_sorting.zkoss.helper;

import com.eacuamba.loading_and_sorting.domain.searchable.Searchable;
import com.eacuamba.loading_and_sorting.helper.ApplicationContextHelper;
import com.googlecode.genericdao.search.Search;
import com.googlecode.genericdao.search.Sort;
import com.googlecode.genericdao.search.jpa.JPASearchFacade;
import org.apache.commons.lang3.ArrayUtils;
import org.zkoss.zk.ui.Desktop;
import org.zkoss.zk.ui.Executions;
import org.zkoss.zul.AbstractListModel;
import org.zkoss.zul.FieldComparator;
import org.zkoss.zul.event.ListDataEvent;
import org.zkoss.zul.ext.Sortable;

import java.util.*;

/**
 * This class will help you to have pagination, this class is a {@link AbstractListModel} and implements the interface {@link Sortable}, so this class allow you to make pagination and sort directly from the database layer.
 * This class uses the lib search-dao-hibernate from https://code.google.com/archive/p/hibernate-generic-dao to achieve this, exactly the class {@link JPASearchFacade}
 *
 * @param <T> the entity to be searched.
 * @author Edilson Alexandre Cuamba
 */
public class PaginatedListModel<T> extends AbstractListModel<T> implements Sortable<T> {
    private final JPASearchFacade jpaSearchFacade;
    private final Search search;
    private final String CACHE_KEY = String.format("%s_cache", PaginatedListModel.class.getName());
    private final Sort[] sorts;
    private final T t;
    private Integer size = null;

    @SuppressWarnings({"unchecked"})
    public PaginatedListModel(T t, Sort... sorts) {
        this.jpaSearchFacade = ApplicationContextHelper.getBean(JPASearchFacade.class);
        this.search = ((Searchable<T>) ApplicationContextHelper.getBean(Searchable.class, t.getClass())).search(new Search(t.getClass()), t);
        this.sorts = sorts;
        this.t = t;
        this.resetCache();
    }

    public PaginatedListModel(T t, Boolean multiple, Sort... sorts) {
        this(t, sorts);
        this.setMultiple(multiple);
    }


    @Override
    @SuppressWarnings({"unchecked"})
    public T getElementAt(int index) {
        Map<Integer, T> cacheMap = getCache();

        T target = cacheMap.get(index);
        if (Objects.isNull(target)) {
            List<T> page;

            if (ArrayUtils.isEmpty(sorts)) {
                page = jpaSearchFacade.search(search.setPage(this.getActivePage()).setMaxResults(this.getPageSize()));
            } else
                page = jpaSearchFacade.search(search.setPage(this.getActivePage()).setMaxResults(this.getPageSize()).addSorts(sorts));

            int nextIndex = index;
            for (T t : page) {
                cacheMap.put(nextIndex++, t);
            }
        } else {
            return target;
        }

        target = cacheMap.get(index);
        if (Objects.isNull(target)) {
            throw new RuntimeException(String.format("The item at index %d was not found.", index));
        } else
            return target;
    }

    @Override
    public int getSize() {
        if (Objects.isNull(size))
            return this.size = jpaSearchFacade.count(search);
        return size;
    }

    @SuppressWarnings({"unchecked"})
    private Map<Integer, T> getCache() {
        Desktop desktop = Executions.getCurrent().getDesktop();
        Map<Integer, T> cacheMap = (Map<Integer, T>) desktop.getAttribute(this.CACHE_KEY);
        if (Objects.isNull(cacheMap)) {
            cacheMap = new HashMap<>();
            desktop.setAttribute(this.CACHE_KEY, cacheMap);
        }
        return cacheMap;
    }

    @SuppressWarnings({"unchecked"})
    public void resetCache() {
        this.size = null;
        Desktop desktop = Executions.getCurrent().getDesktop();
        Map<Integer, T> cacheMap = (Map<Integer, T>) desktop.getAttribute(this.CACHE_KEY);
        if(Objects.nonNull(cacheMap))
            cacheMap.clear();
        fireEvent(ListDataEvent.CONTENTS_CHANGED, -1, -1);
    }

    @Override
    public void sort(Comparator<T> tComparator, boolean ascending) {
        search.clearSorts();
        String rawOrderBy = ((FieldComparator) tComparator).getRawOrderBy();

        Arrays.stream(rawOrderBy.split(",")).map(String::trim).forEach(s -> {
            search.addSort(s, !ascending, true);
        });

        this.clearSelection();
        this.resetCache();
    }

    @Override
    public String getSortDirection(Comparator<T> cmpr) {
        return null;
    }
}
