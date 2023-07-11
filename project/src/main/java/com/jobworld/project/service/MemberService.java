package com.jobworld.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.validation.Valid;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobworld.project.domain.Member;
import com.jobworld.project.dto.MemberDTO;
import com.jobworld.project.repository.MemberRepository;
import com.jobworld.project.repository.MemberRepositoryOld;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
@Slf4j
public class MemberService {
	private final MemberRepository repo;
    
    private MemberDTO setMember(Member member) {
		MemberDTO dto = new MemberDTO();
		dto.setUser_id(member.getId());
		dto.setUser_pw(member.getPw());
		dto.setUser_birthday(member.getBirthday());
		dto.setUser_email(member.getEmail());
		dto.setUser_nm(member.getName());
		dto.setUser_phone_num(member.getPhoneNum());
		dto.setZip_cd(member.getZip_cd());
		dto.setAddress_info(member.getAddress_info());
		dto.setAddress_detail(member.getAddress_detail());
		dto.setLogin_type(member.getLogin_type());
		return dto;
	}
    
	private String validateDuplicateMember(MemberDTO member) throws Exception {
    	try {
    		Member findMembers = repo.findById(member.getUser_id()).get();
    		if(findMembers.getId() != null){
    			return "검증 실패";
    		}
    	}catch(Exception e) {
    		log.error("MemberService validateDuplicateMember(MemberDTO) error --> {}", e);
    	}
		return "검증 성공";
    }
    
    // 회원 수정
    @Transactional
    public void update(Member user) throws Exception {
		Member findId = new Member();
		try {
			findId = repo.findById(user.getId()).get();
			
			findId.setAddress_detail(user.getAddress_detail());
			findId.setAddress_info(user.getAddress_info());
			findId.setZip_cd(user.getZip_cd());
			findId.setBirthday(user.getBirthday());
			findId.setEmail(user.getEmail());
			findId.setName(user.getName());
			findId.setPhoneNum(user.getPhoneNum());
			findId.setPw(user.getPw());			
		}catch(Exception e) {
			log.error("MemberService update(UserDomain) error --> {}", e);
		}
	}
    
    //회원 삭제
    @Transactional
    public String delete(Member user) {
    	try {
			Member find = repo.findById(user.getId()).get();
			if(user.getId().equals(find.getId()) && user.getPw().equals(find.getId()))
				repo.deleteById(user.getId());
			else
				return "삭제 실패";
		} catch (Exception e) {
			log.error("MemberService delete(UserDomain) error --> {}", e);
		}
    	return "삭제 성공";
    }
    
    public List<Member> userFindAll(){
    	List<Member> list = new ArrayList<>();
    	try {
			return repo.findAll();
		} catch (Exception e) {
			log.error("MemberService userFindAll(UserDomain) error --> {}", e);
		}
    	return list;
    }
	
    public MemberDTO findUser(String user_id) {
    	Member findUser = new Member(); 
    	try {
    		 findUser = repo.findById(user_id).get();
    	}catch(NoSuchElementException e) {
    		return null;
    	}
    	
		MemberDTO dto = setMember(findUser);
    	return dto;
    }
    
    @Transactional
	public void save(MemberDTO dto) {
		Member member = Member.setMember(dto);
		repo.save(member);
	}
}
