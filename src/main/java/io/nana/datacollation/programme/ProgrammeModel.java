package io.nana.datacollation.programme;

import io.nana.datacollation.artisan.ArtisanModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
@Entity
@Table( name = "programme")
public class ProgrammeModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    @Size(min = 1, max = 75)
    @Column(name = "title")
    private String title;

    @Column(name = "description", nullable = true)
    private String description;

    @ManyToOne
    @JoinColumn(name = "artisan_id")
    private ArtisanModel artisan;
}
