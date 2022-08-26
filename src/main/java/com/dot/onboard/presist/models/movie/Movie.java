/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.dot.onboard.presist.models.movie;

import com.dot.onboard.presist.models.moveSchedule.MovieSchedule;
import com.dot.onboard.presist.models.movieTags.MovieTags;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.Lob;
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
@Table(name = "movies")
public class Movie {

    @Id
    private Long id;

    private String title;
    
    @Lob
    @Column(length = 512)
    private String overview;
    private String poster;

    @OneToMany(mappedBy = "tag")
    private Set<MovieTags> tags = new HashSet<>();

    @OneToMany(mappedBy = "movie", fetch = FetchType.LAZY)
    private Set<MovieSchedule> movieSchedules;

    @Column(name = "play_until")
    private Date playUntil;

    @Column(name = "create_at")
    @CreationTimestamp
    private Date createAt;

    @Column(name = "update_at")
    @UpdateTimestamp
    private Date updateAt;

    @Column(name = "delete_at")
    private Date deleteAt;

}
