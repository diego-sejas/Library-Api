package com.api.library.controller;


import com.api.library.controller.documentation.MemberControllerDoc;
import com.api.library.dto.MemberDTO;
import com.api.library.exception.BadRequestException;

import com.api.library.domain.service.IMemberService;
import com.api.library.domain.util.Url;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(Url.MEMBERS_URI)
public class MemberController implements MemberControllerDoc {



    @Autowired
    private IMemberService memberService;

    @Override
    @GetMapping
    public ResponseEntity<List<MemberDTO>> getAllMembers(){
        List<MemberDTO> result = this.memberService.getAll();
        return ResponseEntity.ok().body(result);
    }

    @Override
    @PostMapping
    public ResponseEntity<MemberDTO> addMember(@Valid @RequestBody MemberDTO memberDto, BindingResult result) {
        if (result.hasErrors()) {
            throw new BadRequestException(result);
        }
        MemberDTO savedMember = memberService.addMember(memberDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedMember);
    }

    @Override
    @PutMapping("/{id}")
    public ResponseEntity<MemberDTO> updateMember(@Valid @RequestBody MemberDTO dto,
                                                  @PathVariable Long id,
                                                  BindingResult result){
        if (result.hasErrors()) {throw new BadRequestException(result);}
        return new ResponseEntity<>(memberService.updateById(dto, id), HttpStatus.OK);
    }

    @Override
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteMember(@PathVariable(name = "id") Long id){
        memberService.deleteById(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();

    }
}
