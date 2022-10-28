package com.eacuamba.loading_and_sorting.helper;

import com.googlecode.genericdao.search.jpa.JPAAnnotationMetadataUtil;
import com.googlecode.genericdao.search.jpa.JPASearchFacade;
import com.googlecode.genericdao.search.jpa.JPASearchProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
public class JPAFacadeConfiguration {
    @PersistenceContext
    private EntityManager entityManager;

    @Bean
    public JPASearchFacade getJpaSearchFacade(){
        final JPASearchFacade jpaSearchFacade = new JPASearchFacade();
        final JPAAnnotationMetadataUtil jpaAnnotationMetadataUtil = new JPAAnnotationMetadataUtil();
        final JPASearchProcessor jpaSearchProcessor = new JPASearchProcessor(jpaAnnotationMetadataUtil);
        jpaSearchFacade.setSearchProcessor(jpaSearchProcessor);
        jpaSearchFacade.setEntityManager(entityManager);
        return jpaSearchFacade;
    }
}
