package com.jobworld.project.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobworld.project.domain.Member;
import com.jobworld.project.dto.MemberDTO;
import com.jobworld.project.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
@Slf4j
public class MemberService {
	private final MemberRepository repo;
	 
	// 회원 가입
    @Transactional
    public String join(MemberDTO member) throws Exception {
        String msg = "";
        Member insert = null;
        msg = validateDuplicateMember(member); // 검증
		if(msg.equals("검증 성공")) 
			insert = setMember(member);
		else 
			return "동일한 아이디가 존재합니다.";
		try {
			repo.save(insert);
			return "가입 성공";
		} catch (Exception e) {
			log.error("MemberService join(UserDomain) error --> {}", e);
		}
        return "가입 실패";
    }

    private Member setMember(MemberDTO member) {
		Member insert = new Member();
		insert.setId(member.getUser_id());
		insert.setPw(member.getUser_pw());
		insert.setBirthday(member.getUser_birthday());
		insert.setEmail(member.getUser_email());
		insert.setName(member.getUser_nm());
		insert.setPhoneNum(member.getUser_phone_num());
		insert.setZip_cd(member.getZip_cd());
		insert.setAddress_info(member.getAddress_info());
		insert.setAddress_detail(member.getAddress_detail());
		insert.setLogin_type(0);
		return insert;
	}

	private String validateDuplicateMember(MemberDTO member) throws Exception {
    	try {
    		Member findMembers = repo.findOne(member.getUser_id());
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
			findId = repo.findOne(user.getId());
			
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
			Member find = repo.findOne(user.getId());
			if(user.getId().equals(find.getId()) && user.getPw().equals(find.getId()))
				repo.delete(user.getId());
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
	
    public Member findUser(String user_id) {
    	Member findUser = new Member();
		try {
			findUser = repo.findOne(user_id);
			return findUser; 
		} catch (Exception e) {
			log.error("MemberService findUser(String) error --> {}", e);
		}
    	
    	return findUser;
    }
}
