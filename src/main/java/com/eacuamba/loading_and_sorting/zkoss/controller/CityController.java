package com.eacuamba.loading_and_sorting.zkoss.controller;

import com.eacuamba.loading_and_sorting.domain.model.City;
import com.eacuamba.loading_and_sorting.domain.repository.CityRepository;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.Page;
import org.zkoss.zk.ui.metainfo.ComponentInfo;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.VariableResolver;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zk.ui.select.annotation.WireVariable;
import org.zkoss.zul.ListModelList;
import org.zkoss.zul.Listbox;
import org.zkoss.zul.Vlayout;
import sun.security.acl.WorldGroupImpl;

import java.util.List;

@VariableResolver(org.zkoss.zkplus.spring.DelegatingVariableResolver.class)
public class CityController extends SelectorComposer<Vlayout> {

    @WireVariable
    private CityRepository cityRepository;

    @Wire
    private Listbox listbox_city;

    @Override
    public ComponentInfo doBeforeCompose(Page page, Component parent, ComponentInfo compInfo) {
        return super.doBeforeCompose(page, parent, compInfo);
    }

    @Override
    public void doAfterCompose(Vlayout comp) throws Exception {
        super.doAfterCompose(comp);
        final List<City> cityList = cityRepository.findAll();

        final ListModelList<City> cityListModelList = new ListModelList<>(cityList);
        listbox_city.setModel(cityListModelList);
    }
}
