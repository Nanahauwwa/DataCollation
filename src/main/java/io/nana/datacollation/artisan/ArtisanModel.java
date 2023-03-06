package io.nana.datacollation.artisan;

import io.nana.datacollation.programme.ProgrammeModel;
import jakarta.persistence.*;
import jakarta.validation.constraints.Size;
import lombok.Data;
import org.hibernate.validator.constraints.Length;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Table( name = "artisan")
public class ArtisanModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Size(min = 5, max = 70)
    @Column( name = "name" , nullable = false)
    private String fullName;

    @Length(min = 5, max = 40)
    @Column( name = "trade" , nullable = false)
    private String trade;

    @Length(min = 11, max = 13)
    @Column( name = "phone_no" , nullable = false, unique = true)
    private String phoneNumber;

    @Column(name = "sex", nullable = false)
    @Enumerated(EnumType.STRING)
    private Constants.Genders sex;

    @Length(min = 2, max = 35)
    @Column( name = "city" , nullable = false)
    private String city;
    @Length(min = 3, max = 35)
    @Column( name = "state" , nullable = false)
    private String state;

    @OneToMany(mappedBy = "artisan")
    private List<ProgrammeModel> programmes = List.of();

    public ArtisanModel() {
        programmes = new ArrayList<ProgrammeModel>();
    }
}
