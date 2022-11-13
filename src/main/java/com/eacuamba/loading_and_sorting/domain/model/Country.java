package com.eacuamba.loading_and_sorting.domain.model;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
@Entity
@Table(name = "countries", schema = "world")
public class Country {
    @Id
    private Long id;
    private String name;
    @Column(name = "phonecode")
    private String phoneCode;
    @Column(name = "currency_name")
    private String currencyName;
    private String region;

    @OneToMany(mappedBy = "country")
    private List<City> cityList;
}
