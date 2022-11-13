package com.eacuamba.loading_and_sorting.domain.model;


import lombok.*;

import javax.persistence.*;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "cities", schema = "world")
public class City {
    @Id
    private Long id;
    private String name;
    @Column(name = "state_code")
    private String stateCode;

    @Column(name = "country_code")
    private String countryCode;

    @ManyToOne(fetch = FetchType.EAGER)
    private Country country;
}
