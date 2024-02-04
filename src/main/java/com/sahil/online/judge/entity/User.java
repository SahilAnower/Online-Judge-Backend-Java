package com.sahil.online.judge.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "we_code_user")
public class User extends BaseEntity{
    private String email;
    @Column(name = "user_name")
    private String userName;
    @Column(name = "first_name")
    private String firstName;
    @Column(name = "last_name")
    private String lastName;
    @Column(name = "encoded_password")
    private String encodedPassword;
    @Column(name = "unique_id")
    private String uniqueId;
    private Gender gender;
    private String location;
    private String website;
    private String github;
    private String linkedin;
    private String twitter;
}
