package com.criminalRecord.CRM.entity;

import java.util.List;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
@Table(name="criminals")
public class Criminal {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//criminalId - database column

    @Column(name="firstName",nullable = false)
    private String firstName;

    @Column(name="lastName",nullable = false)
    private String lastName;

    @Column(name="crimeType")
    private String crimeType;

    @Column(name="crimeDetails")
    private String crimeDetails;

    @Column(name="age")
    private Integer age;

    @Column(name="gender")
    private String gender;

    @Column(name="bloodGroup")
    private String bloodGroup;

    @Column(name="healthCondition")
    private String heathCondition;

    @Lob
    @Column(name="leftPhoto")
    private byte[] leftPhoto;

    @Lob
    @Column(name="rightPhoto")
    private byte[] rightPhoto;

    @Lob
    @Column(name="frontPhoto")
    private byte[] frontPhoto;

    @Column(name="jail")
    private String jailName;

    @Column(name="cellNo")
    private Integer cellNo;

    @OneToMany(mappedBy = "criminal")
    private List<Meeting> meetings;

    @OneToMany(mappedBy = "criminal")
    private List<Punishment> punishments;

    @OneToMany(mappedBy = "criminal")
    private List<Transfer> transfers;
}
