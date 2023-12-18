package com.jobworld.project.service;

import java.util.List;

import com.jobworld.project.dto.response.member.MemberResponseDto;
import com.jobworld.project.exception.NotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobworld.project.domain.Member;
import com.jobworld.project.dto.request.member.MemberRequestDto;
import com.jobworld.project.repository.MemberRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly=true)
@RequiredArgsConstructor
@Slf4j
public class MemberService {
	private final MemberRepository memberRepository;
	private final PasswordEncoder passwordEncoder;
    
    private MemberResponseDto setMemberRequestDto(Member member) {
		MemberResponseDto dto = MemberResponseDto.fromEntity(member);
		return dto;
	}
    
    // 회원 수정
    @Transactional
    public void update(MemberRequestDto memberRequestDto) throws Exception {
		Member findId = memberRepository.findById(memberRequestDto.getUserId()).orElseThrow(
				()-> new NotFoundException("해당 유저를 찾을 수 없습니다."));
		findId = Member
				.builder()
				.memberRequestDto(memberRequestDto)
				.build();
	}
    
    //회원 삭제
    @Transactional
    public String delete(MemberRequestDto memberRequestDto) {
    	try {
			Member find = memberRepository.findById(memberRequestDto.getUserId()).orElseThrow(
					() -> new NotFoundException("해당 유저를 찾을 수 없습니다."));
			if(memberRequestDto.getUserId().equals(find.getId()) && memberRequestDto.getUserPw().equals(find.getId()))
				memberRepository.deleteById(memberRequestDto.getUserId());
			else
				return "삭제 실패";
		} catch (Exception e) {
			log.error("MemberService delete(UserDomain) error --> {}", e);
		}
    	return "삭제 성공";
    }
    
    public List<MemberResponseDto> userFindAll(){
    	return memberRepository.findAll()
				.stream().map(MemberResponseDto::fromEntity).toList();
    }
	
    public MemberResponseDto findUser(String userId) {
    	Member findUser = memberRepository.findById(userId).orElseThrow(
				() -> new NotFoundException("해당 유저를 찾을 수 없습니다."));
    	return setMemberRequestDto(findUser);
    }
    
    @Transactional
	public void save(MemberRequestDto memberRequestDto) {
		memberRequestDto.setUserPw(passwordEncoder.encode(memberRequestDto.getUserPw()));
		Member member = Member
				.builder()
				.memberRequestDto(memberRequestDto)
				.build();
		memberRepository.save(member);
	}

	public boolean findUserCheck(String userId) {
		return memberRepository.findById(userId).isEmpty();
	}

	public boolean validPwCheck(String inputPw, String encodePw) {
		return passwordEncoder.matches(inputPw, encodePw);
	}
}
