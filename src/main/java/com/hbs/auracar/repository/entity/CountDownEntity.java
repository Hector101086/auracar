package com.hbs.auracar.repository.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.Instant;

@Getter
@Setter
@Entity
@Table(name = "countdown", indexes = {
        @Index(name = "countdown_id_carid_pk", columnList = "id, carid", unique = true)
})
public class CountDownEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "carid", nullable = false)
    private CarEntity carEntityId;

    @Column(name = "startdate", nullable = false)
    private Instant startDate;

    @Column(name = "enddate", nullable = false)
    private Instant endDate;

}
