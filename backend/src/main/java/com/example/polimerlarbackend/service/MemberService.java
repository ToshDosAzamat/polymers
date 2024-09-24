package com.example.polimerlarbackend.service;

import com.example.polimerlarbackend.model.Member;
import java.util.*;

public interface MemberService {
    Member createMember(String email);
    List<Member> readAllMembers();
    Member readById(long id);
    String deleteMember(long id);


}
