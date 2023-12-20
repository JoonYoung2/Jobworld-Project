package com.jobworld.project.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.jobworld.project.dto.response.apply.ApplyResponseDto;
import com.jobworld.project.dto.response.company.recruit.RecruitResponseDto;
import com.jobworld.project.dto.response.user.resume.ResumeResponseDto;
import com.jobworld.project.exception.NotFoundException;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.jobworld.project.domain.Apply;
import com.jobworld.project.domain.Recruit;
import com.jobworld.project.domain.Resume;
import com.jobworld.project.dto.request.apply.ApplyRequestDto;
import com.jobworld.project.dto.request.apply.UserResumeRecruitRequestDto;
import com.jobworld.project.repository.ApplyRepository;
import com.jobworld.project.repository.RecruitRepository;
import com.jobworld.project.repository.ResumeRepository;
import com.jobworld.project.repository.apply.CompApplyStatusDTO;
import com.jobworld.project.repository.apply.UserApplyStatusDTO;

import lombok.RequiredArgsConstructor;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class ApplyService {
	private final ApplyRepository repo;
	private final ResumeRepository resumeRepository;
	private final RecruitRepository recruitRepository;
	private final HttpSession session;

	public ResumeResponseDto getUserResume() {
		List<Resume> list = resumeRepository.findByMemberId(session.getAttribute("user_id").toString());
		if(list.size() > 0) {
			Resume resume = list.get(0);
			ResumeResponseDto dto = setResumeResponseDto(resume);
			return dto;
		}else
			return null;
	}

	public RecruitResponseDto getRecruitInfo(Long recruitId) {
		Recruit recruit = recruitRepository.findById(recruitId).orElseThrow(
				() -> new NotFoundException("찾는 공고문이 없습니다."));
		RecruitResponseDto dto = RecruitResponseDto.fromEntity(recruit);
		return dto;
	}

	@Transactional
	public String applySave(ApplyRequestDto dto) {
		Resume resume = resumeRepository.findById(dto.getResumeId()).orElseThrow(
				() -> new NotFoundException("찾는 이력서가 존재하지 않습니다."));
		Recruit recruit = recruitRepository.findById(dto.getRecruitId()).orElseThrow(
				() -> new NotFoundException("찾는 공고문이 없습니다."));
		Optional<Apply> applyCheck = repo.checkApplyUser(dto.getRecruitId(), dto.getResumeId());
		if (applyCheck.isPresent()) {
			return "이미 지원하셨습니다.";
		} else {
			Apply apply = Apply
					.builder()
					.resume(resume)
					.recruit(recruit)
					.build();
			repo.save(apply);
			return "성공";
		}
	}

	public List<ApplyResponseDto> getApplyInfo(Long recruitId) {
		List<Apply> applyList = repo.findByRecruitId(recruitId);
		List<ApplyResponseDto> list = new ArrayList<>();

		for(Apply apply : applyList)
			list.add(ApplyResponseDto.fromEntity(apply));

		return list;
	}

	public List<UserApplyStatusDTO> getResumeMemberInfo(List<ApplyResponseDto> check) {
		List<UserApplyStatusDTO> list = new ArrayList<>();
		for(int i = 0; i < check.size(); ++i) {
			UserApplyStatusDTO dto = new UserApplyStatusDTO();
			Resume resume = resumeRepository.findById(check.get(i).getResumeId()).orElseThrow(
					() -> new NotFoundException("찾는 이력서가 없습니다."));
			dto.setApplyId(check.get(i).getApplyId());
			dto.setState(check.get(i).getApplyState());
			dto.setResumeTitle(resume.getTitle());
			dto.setUserBirthday(resume.getMember().getBirthday());
			dto.setUserEmail(resume.getMember().getEmail());
			dto.setUserId(resume.getMember().getId());
			dto.setUserImg(resume.getImg());
			dto.setUserNm(resume.getMember().getName());
			dto.setResumeId(resume.getId());
			dto.setUserPhoneNum(resume.getMember().getPhoneNum());
			dto.setZipCd(resume.getMember().getZipCd());
			dto.setAddressInfo(resume.getMember().getAddressInfo());
			dto.setAddressDetail(resume.getMember().getAddressDetail());
			list.add(dto);
		}
		return list;
	}

	public Long getResumeId(String user_id) {
		List<Resume> resume = resumeRepository.findByMemberId(user_id);
		if(resume.size()>0) {
			return resume.get(0).getId();
		}else
			return 0L;
	}

	public List<ApplyResponseDto> getApplyResponseDtoList(Long resumeId) {
		List<Apply> applyList = repo.findByResumeId(resumeId);
		List<ApplyResponseDto> list = new ArrayList<>();

		for(Apply apply : applyList)
			list.add(ApplyResponseDto.fromEntity(apply));

		return list;
	}

	public List<CompApplyStatusDTO> getRecruitCompanyInfo(List<ApplyResponseDto> check) {
		List<CompApplyStatusDTO> list = new ArrayList<>();
		for(int i = 0; i < check.size(); ++i) {
			CompApplyStatusDTO dto = new CompApplyStatusDTO();
			Recruit recruit = recruitRepository.findById(check.get(i).getRecruitId()).orElseThrow(
					() -> new NotFoundException("찾는 공고문이 없습니다."));;
			dto.setApplyId(check.get(i).getApplyId());
			dto.setCompBusinessType(recruit.getCompany().getBusinessType());
			dto.setCompEmplNum(recruit.getCompany().getEmplNum());
			dto.setCompId(recruit.getCompany().getId());
			dto.setCompNm(recruit.getCompany().getName());
			dto.setCompSite(recruit.getCompany().getSite());
			dto.setCompSize(recruit.getCompany().getSize());
			dto.setRecruitArea(recruit.getArea());
			dto.setRecruitCareer(recruit.getCareer());
			dto.setRecruitEducation(recruit.getEducation());
			dto.setRecruitEmployment(recruit.getEmployment());
			dto.setRecruitEndDate(recruit.getEndDate());
			dto.setRecruitId(recruit.getId());
			dto.setRecruitOpenType(recruit.getOpenType());
			dto.setRecruitSalary(recruit.getSalary());
			dto.setRecruitStartDate(recruit.getStartDate());
			dto.setRecruitTime(recruit.getTime());
			dto.setState(check.get(i).getApplyState());
			dto.setRecruitTitle(recruit.getTitle());
			dto.setCompBrandImg(recruit.getCompany().getBrandImg());
			list.add(dto);

		}
		return list;
	}

	@Transactional
	public void applyCancel(Long applyId) {
		repo.deleteById(applyId);
	}

	@Transactional
	public Long stateUpdate(Long applyId, int state) {
		Apply apply = repo.findById(applyId).orElseThrow(
				() -> new NotFoundException("지원서를 찾을 수 없습니다.")
		);
		apply.updateState(state);
		Long recruitId = apply.getRecruit().getId();
		return recruitId;
	}

	public UserResumeRecruitRequestDto getApplyInfo(Long recruitId, Long resumeId) {
		Recruit recruit = recruitRepository.findById(recruitId).orElseThrow(
				() -> new NotFoundException("찾는 공고문이 없습니다."));;
		Resume resume = resumeRepository.findById(resumeId).orElseThrow(
				() -> new NotFoundException("찾는 이력서가 없습니다."));
		UserResumeRecruitRequestDto dto = setUserResumeRecruitDto(recruit, resume);
		return dto;
	}

	private ResumeResponseDto setResumeResponseDto(Resume resume) {
		ResumeResponseDto dto = ResumeResponseDto.fromEntity(resume);
		return dto;
	}

	private UserResumeRecruitRequestDto setUserResumeRecruitDto(Recruit recruit, Resume resume) {
		UserResumeRecruitRequestDto dto = new UserResumeRecruitRequestDto();
		dto.setRecruitId(recruit.getId());
		dto.setResumeId(resume.getId());
		dto.setCompNm(recruit.getCompany().getName());
		dto.setRecruitTitle(recruit.getTitle());
		dto.setResumeTitle(resume.getTitle());
		dto.setUserEmail(resume.getMember().getEmail());
		dto.setUserNm(resume.getMember().getName());
		dto.setUserPhoneNum(resume.getMember().getPhoneNum());
		
		return dto;
	}

}