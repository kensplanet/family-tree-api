package com.kensplanet.familytreeapi.transform;

import com.kensplanet.familytreeapi.model.Parent;
import com.kensplanet.familytreeapi.model.Member;

import java.util.ArrayList;
import java.util.List;

public class Transformer {

    public static List<Parent> parentTransformer(List<Parent> parents) {
        List<Parent> transform = new ArrayList<>();
        parents.forEach(couple -> {
            Parent object = new Parent();
            object.setParentId(couple.getParentId());
            couple.getMembers().forEach(member -> {
                Member person = new Member();
                person.setMemberId(member.getMemberId());
                person.setName(member.getName());
                object.getMembers().add(person);
            });
            transform.add(object);
        });
        return transform;
    }

    public static List<Member> memberTransformer(List<Member> members) {
        List<Member> transform = new ArrayList<>();
        members.forEach(person -> {
            transform.add(new Member(person.getMemberId(), person.getName()));
        });
        return transform;
    }
}
