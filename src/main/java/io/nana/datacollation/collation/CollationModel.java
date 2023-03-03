package io.nana.datacollation.collation;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
@Table( name = "artisan_detail")
public class CollationModel {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column( name = "name" , nullable = false)
    private String name;

    @Column( name = "trade" , nullable = false)
    private String trade;

    @Column( name = "phone_no" , nullable = false)
    private String phoneNumber;
    @Column( name = "sex" , nullable = false)
    private String sex;
    @Column( name = "city" , nullable = false)
    private String city;
    @Column( name = "state" , nullable = false)
    private String state;
    @Column( name = "training_program" , nullable = false)
    private String program;

}
