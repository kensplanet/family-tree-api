package com.kensplanet.familytreeapi.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.*;

@Entity
@Table
@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonIgnoreProperties({"hibernateLazyInitializer"})
public class Parent {

    @Transient
    private Set<Member> fetchedMembers = new HashSet<>();

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer parentId;

    @ManyToMany(cascade = CascadeType.PERSIST)
    @NotNull
    private List<Member> members = new ArrayList<>();

    @OneToMany(cascade = CascadeType.PERSIST)
    private List<Member> children = new ArrayList<>();

    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    public List<Member> getMembers() {
        if (fetchedMembers.containsAll(members)) {
            members = new ArrayList<>();
        } else {
            fetchedMembers.addAll(members);
        }
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public List<Member> getChildren() {
        if (fetchedMembers.containsAll(children)) {
            children = new ArrayList<>();
        }else {
            fetchedMembers.addAll(children);
        }
        return children;
    }

    public void setChildren(List<Member> children) {
        this.children = children;
    }
}
