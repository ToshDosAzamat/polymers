package com.example.polimerlarbackend.controller;

import com.example.polimerlarbackend.model.Member;
import com.example.polimerlarbackend.service.MemberService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import java.util.*;



@RestController
@RequestMapping("/api")
@Validated
public class MemberController {
    private MemberService memberService;

    public MemberController(MemberService memberService) {
        this.memberService = memberService;
    }
    @PostMapping(value = "/member/create")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<Member> creatingMember(@RequestParam @NotEmpty @Email String email){
        return new ResponseEntity<>(memberService.createMember(email), HttpStatus.CREATED);
    }
    @GetMapping("/member/get/all")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<List<Member>> readingAll(){
        return new ResponseEntity<>(memberService.readAllMembers(),HttpStatus.OK);
    }
    @GetMapping("/member/get/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<Member> readingByIdMember(@PathVariable long id){
        return new ResponseEntity<>(memberService.readById(id),HttpStatus.OK);
    }
    @GetMapping("/member/delete/{id}")
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_ADMIN')")
    public ResponseEntity<String> deletingMember(@PathVariable long id){
        return new ResponseEntity<>(memberService.deleteMember(id), HttpStatus.OK);
    }

}

