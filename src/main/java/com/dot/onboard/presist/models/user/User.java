/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.presist.models.user;

import com.dot.onboard.presist.models.order.Order;
import java.util.Date;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

/**
 *
 * @author ASUS
 */
@Data
@Entity
@Table(name = "users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    
    @Column(unique = true)
    private String email;
    private String password;
    private String avatar;

    @Column(name = "is_admin")
    private Boolean isAdmin;

    @Column(name = "create_at")
    @CreationTimestamp
    private Date createAt;

    @Column(name = "update_at")
    @UpdateTimestamp
    private Date updateAt;

    @Column(name = "delete_at")
    private Date deleteAt;
    
    
    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY,cascade = CascadeType.ALL)
    private Set<Order> orders;

}
