package com.kensplanet.familytreeapi.service;

import com.kensplanet.familytreeapi.model.Parent;
import com.kensplanet.familytreeapi.model.Member;
import com.kensplanet.familytreeapi.repository.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Component
@Transactional
public class LoadData {

    @Autowired
    private MemberRepository memberRepository;

    //method invoked during the startup
    @PostConstruct
    public void loadData() {
        List<Member> memberList = new ArrayList<>();

        Member member1 = new Member();
        member1.setName("King Gustaf VI Adolf");
        member1.setBirthPlace("Stockholm, Sweden");
        member1.setBirthYear(1882);
        member1.setDeathYear(1973);
        member1.setSex("M");

        memberList.add(member1);

        Member member2 = new Member();
        member2.setName("Crown Princess Margareta, Duchess of Skåne");
        member2.setBirthPlace("United Kingdom");
        member2.setBirthYear(1882);
        member2.setDeathYear(1920);
        member2.setSex("F");

        memberList.add(member2);

        Member member3 = new Member();
        member3.setName("Prince Gustaf Adolf, Duke of Västerbotten");
        member3.setBirthPlace("Stockholm, Sweden");
        member3.setBirthYear(1906);
        member3.setDeathYear(1947);
        member3.setSex("M");

        memberList.add(member3);

        Member member4 = new Member();
        member4.setName("Sigvard Bernadotte");
        member4.setBirthPlace("Stockholm, Sweden");
        member4.setBirthYear(1907);
        member4.setDeathYear(2002);
        member4.setSex("M");

        memberList.add(member4);

        Member member5 = new Member();
        member5.setName("Princess Sibylla of Saxe-Coburg and Gotha");
        member5.setBirthPlace("Germany");
        member5.setBirthYear(1908);
        member5.setDeathYear(1972);
        member5.setSex("F");

        memberList.add(member5);

        Member member6 = new Member();
        member6.setName("Marianne Bernadotte");
        member6.setBirthPlace("Helsingborg, Sweden");
        member6.setBirthYear(1924);
        member6.setSex("F");

        memberList.add(member6);

        Member member7 = new Member();
        member7.setName("Princess Margaretha, Mrs. Ambler");
        member7.setBirthPlace("Solna, Sweden");
        member7.setBirthYear(1934);
        member7.setSex("F");

        memberList.add(member7);

        Member member8 = new Member();
        member8.setName("Tord Magnuson");
        member8.setBirthPlace("Stockholm, Sweden");
        member8.setBirthYear(1941);
        member8.setSex("M");

        memberList.add(member8);

        Parent parent1 = new Parent();
        List<Member> members = new ArrayList<>();
        members.add(member1);
        members.add(member2);
        parent1.setMembers(members);
        List<Member> children = new ArrayList<>();
        children.add(member3);
        children.add(member4);
        parent1.setChildren(children);
        List<Parent> spouses = new ArrayList<>();
        spouses.add(parent1);
        member1.setSpouses(spouses);
        member2.setSpouses(spouses);
        member3.setParent(parent1);
        member4.setParent(parent1);

        Parent parent2 = new Parent();
        parent2.getMembers().add(member3);
        parent2.getMembers().add(member5);
        List<Parent> spouses2 = new ArrayList<>();
        spouses2.add(parent2);
        member3.setSpouses(spouses2);
        member5.setSpouses(spouses2);
        parent2.getChildren().add(member7);
        parent2.getChildren().add(member8);
        member7.setParent(parent2);
        member8.setParent(parent2);

        Parent parent3 = new Parent();
        parent3.getMembers().add(member4);
        parent3.getMembers().add(member6);
        List<Parent> spouses3 = new ArrayList<>();
        spouses3.add(parent3);
        member4.setSpouses(spouses3);
        member6.setSpouses(spouses3);

        memberRepository.saveAll(memberList);
    }
}
