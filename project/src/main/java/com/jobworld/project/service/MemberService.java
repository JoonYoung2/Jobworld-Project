package com.jobworld.project.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobworld.project.domain.Member;
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
    public String join(Member user) throws Exception {
        
        validateDuplicateMember(user); // 중복 회원 검증
        try {
			repo.save(user);
		} catch (Exception e) {
			log.error("MemberService join(UserDomain) error --> {}", e);
		}
        return user.getId();
    }

    private void validateDuplicateMember(Member user) throws Exception {
    	try {
    		Member findMembers = repo.findOne(user.getId());
    		if(findMembers.getId() != null){
    			throw new IllegalStateException("이미 존재하는 회원입니다.");
    		}    		
    	}catch(Exception e) {
    		log.error("MemberService validateDuplicateMember(UserDomain) error --> {}", e);
    	}
    }
    
    // 회원 수정
    @Transactional
    public void update(Member user) throws Exception {
		Member findId = null;
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
    	List<Member> list = null;
    	try {
			return repo.findAll();
		} catch (Exception e) {
			log.error("MemberService userFindAll(UserDomain) error --> {}", e);
		}
    	return list;
    }
	
    public Member findUser(String user_id) {
    	Member findUser = null;
		try {
			return repo.findOne(user_id);
		} catch (Exception e) {
			log.error("MemberService findUser(String) error --> {}", e);
		}
    	
    	return findUser;
    }
}
