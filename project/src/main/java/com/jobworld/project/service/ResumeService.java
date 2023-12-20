package com.jobworld.project.service;

import com.jobworld.project.dto.request.user.resume.UserResumeRequestDto;
import com.jobworld.project.dto.response.user.resume.ResumeResponseDto;
import com.jobworld.project.exception.NotFoundException;
import com.jobworld.project.repository.MemberRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jobworld.project.domain.Member;
import com.jobworld.project.domain.Resume;
import com.jobworld.project.dto.request.user.resume.ResumeRequestDto;
import com.jobworld.project.dto.response.user.resume.UserResumeResponseDto;
import com.jobworld.project.repository.ResumeRepository;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
@Slf4j
public class ResumeService {

	private final ResumeRepository repo;
	private final MemberRepository memberRepository;

	private static final String dir = "D:\\jobword_test\\project\\src\\main\\webapp\\resources\\upload\\";

	private ResumeResponseDto setResume(Resume resume) {
		return ResumeResponseDto.fromEntity(resume);
	}

	public String userImgSave(MultipartFile file, String user_id) {
		String originalName = file.getOriginalFilename();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss-");
		Calendar cal = Calendar.getInstance();
		String fileName = sdf.format(cal.getTime()) + originalName;
		String path = dir + user_id + "\\" + fileName;
		File targetFile = new File(path);
		if (targetFile.exists() == false) {
			targetFile.mkdirs();
		}
		try {
			file.transferTo(targetFile);
		} catch (IllegalStateException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return fileName;
	}

	public void folderDelete(String user_id) {
		String path = dir + user_id;
		File folder = new File(path);
		try {
			while (folder.exists()) {
				File[] folder_list = folder.listFiles(); // 파일리스트 얻어오기
				for (int j = 0; j < folder_list.length; j++) {
					folder_list[j].delete(); // 파일 삭제
				}
				if (folder_list.length == 0 && folder.isDirectory()) {
					folder.delete();
				}
			}
		} catch (Exception e) {
			System.out.println(e);
		}
	}
	
	@Transactional
	public String resumeWrite(ResumeRequestDto resumeRequestDto) {
		String userId = resumeRequestDto.getUserId();
		String resumeTitle = resumeRequestDto.getResumeTitle();
		MultipartFile file = resumeRequestDto.getFile();
		if (resumeTitle == null || resumeTitle.equals("")) {
			return "제목을 입력해주세요.";
		}
		if (file == null || file.isEmpty()) {
			return "업로드 파일을 등록해주세요.";
		}
		String userImg = userImgSave(file, userId);
		Member member = memberRepository.findById(userId).orElseThrow(
				() -> new NotFoundException("유저를 찾을 수 없습니다."));

		Resume resume = Resume
				.builder()
				.member(member)
				.title(resumeTitle)
				.img(userImg)
				.build();
		repo.save(resume);
		return "쓰기 성공";
	}

	@Transactional
	public String resumeUpdate(Resume resume) {
		Resume update = repo.findById(resume.getId()).orElseThrow(
				() -> new NotFoundException("찾는 이력서가 존재하지 않습니다."));
		update.updateImg(resume.getImg());
		update.updateTitle(resume.getTitle());
		return "수정 완료";
	}

	public String resumeFind(String userId) {
		String msg = validateDuplicateResume(userId);
		return msg;
	}

	private String validateDuplicateResume(String memberId) {
		List<ResumeResponseDto> list = repo.findByMemberId(memberId)
				.stream().map(ResumeResponseDto::fromEntity).toList();
		String msg = "";
		if(list.size()>0) 
			msg = "있음";
		else
			msg = "없음";
		log.info("msg => {}", msg);
		return msg;
	}
	
	public ResumeResponseDto getResume(String memberId) {
		List<ResumeResponseDto> list = repo.findByMemberId(memberId)
				.stream().map(ResumeResponseDto::fromEntity).toList();
		if(list.size() > 0) {
			return list.get(0);
		}
		return null;
	}

	public UserResumeResponseDto getUserResumeDto(String memberId) {
		List<Resume> list = repo.findByMemberId(memberId);
		if(list.size() > 0) {
			return UserResumeResponseDto.fromEntity(list.get(0));
		}
		return null;
	}
	public UserResumeResponseDto getUserResumeDto(Long resumeId) {
		Resume resume = repo.findById(resumeId).orElseThrow(
				() -> new NotFoundException("이력서가 존재하지 않습니다."));
		return UserResumeResponseDto.fromEntity(resume);
	}

	public ResumeResponseDto getResumeInfo(Long resume_id) {
		Resume resume = repo.findById(resume_id).orElseThrow(
				() -> new NotFoundException("이력서가 존재하지 않습니다."));
		ResumeResponseDto resumeRequestDto = ResumeResponseDto.fromEntity(resume);
		return resumeRequestDto;
	}

	@Transactional
	public ResumeResponseDto imgUpdate(MultipartHttpServletRequest multi) {
		Long resumeId = Long.parseLong(multi.getParameter("resumeId"));
		String userId = multi.getParameter("userId");
		MultipartFile file = multi.getFile("file");
		if (file == null || file.isEmpty()) {
			Resume resume = repo.findById(resumeId).orElseThrow(
					() -> new NotFoundException("이력서가 존재하지 않습니다.")
			);
			ResumeResponseDto dto = ResumeResponseDto.fromEntity(resume);
			return dto;
		}
		// 폴더 및 파일 삭제
		folderDelete(userId);

		String userImg = userImgSave(file, userId);

		Resume resume = repo.findById(resumeId).orElseThrow(
				() -> new NotFoundException("이력서가 존재하지 않습니다.")
		);
		resume.updateImg(userImg);

		return ResumeResponseDto.fromEntity(resume);
	}

	@Transactional
	public ResumeResponseDto resumeUpdate(ResumeRequestDto resumeRequestDto) {
		Resume resume = repo.findById(resumeRequestDto.getResumeId()).orElseThrow(
				() -> new NotFoundException("이력서가 존재하지 않습니다.")
		);
		resume.updateTitle(resumeRequestDto.getResumeTitle());
		return ResumeResponseDto.fromEntity(resume);
	}

	@Transactional
	public UserResumeResponseDto personalInfoUpdate(UserResumeRequestDto dto) {
		UserResumeRequestDto userResumeDto = setPersonalMulti(dto);
		Resume resume = repo.findById(userResumeDto.getResumeId()).orElseThrow(
				() -> new NotFoundException("이력서가 존재하지 않습니다.")
		);
		UserResumeRequestDto userResume = personResumeUpdate(userResumeDto, resume);

		return UserResumeResponseDto.fromEntity(resume);
	}

	private UserResumeRequestDto personResumeUpdate(UserResumeRequestDto userResumeDto, Resume resume) {
		if(userResumeDto.getZipCd() != resume.getMember().getZipCd()) {
			resume.getMember().updateZipCd(userResumeDto.getZipCd());
			resume.getMember().updateAddressInfo(userResumeDto.getAddressInfo());
		}
		
		if(userResumeDto.getAddressDetail() != resume.getMember().getAddressDetail())
			resume.getMember().updateAddressDetail(userResumeDto.getAddressDetail());
		
		if(userResumeDto.getResumeTitle() != resume.getTitle())
			resume.updateTitle(userResumeDto.getResumeTitle());
		
		if(userResumeDto.getUserBirthday() != resume.getMember().getBirthday())
			resume.getMember().updateBirthday(userResumeDto.getUserBirthday());
		
		if(userResumeDto.getUserEmail() != resume.getMember().getEmail())
			resume.getMember().updateEmail(userResumeDto.getUserEmail());
		
		if(userResumeDto.getUserImg() != null)
			resume.updateImg(userResumeDto.getUserImg());
		else
			userResumeDto.setUserImg(resume.getImg());
		
		if(userResumeDto.getUserNm() != resume.getMember().getName())
			resume.getMember().updateName(userResumeDto.getUserNm());
		
		if(userResumeDto.getUserPhoneNum() != resume.getMember().getPhoneNum())
			resume.getMember().updatePhoneNum(userResumeDto.getUserPhoneNum());
		
		return userResumeDto;
	}

	private UserResumeRequestDto setPersonalMulti(UserResumeRequestDto dto) {
		
		MultipartFile file = dto.getFile();
		
		if (file == null || file.isEmpty()) {
			return dto;
		}
		
		folderDelete(dto.getUserId());
		dto.setUserImg(userImgSave(file, dto.getUserId()));
		
		return dto;
	}

	
}
