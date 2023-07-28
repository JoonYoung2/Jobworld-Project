package com.jobworld.project.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.jobworld.project.domain.Member;
import com.jobworld.project.domain.Resume;
import com.jobworld.project.dto.ResumeDTO;
import com.jobworld.project.dto.resumeInfoDto.UserResumeDTO;
import com.jobworld.project.dto.resumeInfoDto.UserResumeMultiDTO;
import com.jobworld.project.repository.MemberRepositoryOld;
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
	private final MemberRepositoryOld memberRepository;

	private static String dir = "D:\\jobworld_controller\\jobworld_project\\project\\src\\main\\webapp\\resources\\upload\\";

	private ResumeDTO setResume(Resume resume) {
		ResumeDTO dto = new ResumeDTO();

		dto.setResume_id(resume.getId());
		dto.setResume_title(resume.getTitle());
		dto.setUser_id(resume.getMember().getId());
		dto.setUser_img(resume.getImg());
		return dto;
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
	public String resumeWrite(UserResumeMultiDTO dto) {
		String user_id = dto.getUser_id();
		String resume_title = dto.getResume_title();
		MultipartFile file = dto.getFile();
		if (resume_title == null || resume_title.equals("")) {
			return "제목을 입력해주세요.";
		}
		if (file == null || file.isEmpty()) {
			return "업로드 파일을 등록해주세요.";
		}
		String user_img = userImgSave(file, user_id);
		Member member = memberRepository.findOne(user_id);
		Resume resume = Resume.createResume(member, user_id, user_img, resume_title);
		repo.save(resume);
		return "쓰기 성공";
	}

//	@Transactional
//	public String resumeWrite(MultipartHttpServletRequest multi) {
//		String user_id = multi.getParameter("user_id");
//		String resume_title = multi.getParameter("resume_title");
//		MultipartFile file = multi.getFile("file");
//		if (resume_title == null || resume_title.equals("")) {
//			return "제목을 입력해주세요.";
//		}
//		if (file == null || file.isEmpty()) {
//			return "업로드 파일을 등록해주세요.";
//		}
//		String user_img = userImgSave(file, user_id);
//		Member member = memberRepository.findOne(user_id);
//		Resume resume = Resume.createResume(member, user_id, user_img, resume_title);
//		repo.save(resume);
//		return "쓰기 성공";
//	}

	@Transactional
	public String resumeUpdate(Resume resume) {
		try {
			Resume update = repo.findOne(resume.getId());
			update.setImg(resume.getImg());
			update.setTitle(resume.getTitle());
			return "수정 완료";
		} catch (Exception e) {
			log.error("ResumeService resumeUpdate(Resume) error --> {}", e);
		}
		return "수정 실패";
	}

	public String resumeFind(String user_id) {
		String msg = validateDuplicateResume(user_id);
		return msg;
	}

	private String validateDuplicateResume(String user_id) {
		List<Resume> list = repo.findByName(user_id);
		String msg = "";
		if(list.size()>0) 
			msg = "있음";
		else
			msg = "없음";

		return msg;
	}
	
	public ResumeDTO getResume(String user_id) {
		List<Resume> list = repo.findByName(user_id);
		if(list.size() > 0) {
			Resume resume = list.get(0);
			ResumeDTO dto = setResume(resume);
			return dto;			
		}
		
		return null;
	}

	public UserResumeDTO getUserResumeDto(String user_id) {
		List<Resume> list = repo.findByName(user_id);
		if(list.size() > 0) {
			Resume resume = list.get(0);
			UserResumeDTO dto = setUserResumeDto(resume);
			return dto;			
		}
		
		return null;
	}
	public UserResumeDTO getUserResumeDto(int resume_id) {
		List<Resume> list = repo.findById(resume_id);
		if(list.size() > 0) {
			Resume resume = list.get(0);
			UserResumeDTO dto = setUserResumeDto(resume);
			return dto;			
		}
		
		return null;
	}

	private UserResumeDTO setUserResumeDto(Resume resume) {
		UserResumeDTO dto = new UserResumeDTO();
		
		dto.setUser_id(resume.getMember().getId());
		dto.setAddress_detail(resume.getMember().getAddress_detail());
		dto.setAddress_info(resume.getMember().getAddress_info());
		dto.setResume_id(resume.getId());
		dto.setResume_title(resume.getTitle());
		dto.setUser_birthday(resume.getMember().getBirthday());
		dto.setUser_email(resume.getMember().getEmail());
		dto.setUser_img(resume.getImg());
		dto.setUser_nm(resume.getMember().getName());
		dto.setUser_phone_num(resume.getMember().getPhoneNum());
		dto.setZip_cd(resume.getMember().getZip_cd());
		
		return dto;
	}

	public ResumeDTO getResumeInfo(int resume_id) {
		Resume getResume = repo.findOne(resume_id);
		ResumeDTO resume = setResume(getResume);
		return resume;
	}

	@Transactional
	public UserResumeDTO imgUpdate(MultipartHttpServletRequest multi) {
		int resume_id = Integer.parseInt(multi.getParameter("resume_id"));
		String user_id = multi.getParameter("user_id");
		MultipartFile file = multi.getFile("file");
		if (file == null || file.isEmpty()) {
			Resume resume = repo.findOne(resume_id);
			UserResumeDTO dto = setUserResumeDto(resume);
			return dto;
		}
		// 폴더 및 파일 삭제
		folderDelete(user_id);

		String user_img = userImgSave(file, user_id);

		Resume resume = repo.findOne(resume_id);
		resume.setImg(user_img);

		UserResumeDTO dto = setUserResumeDto(resume);
		
		return dto;
	}

	@Transactional
	public ResumeDTO resumeUpdate(ResumeDTO dto) {
		Resume resume = repo.findOne(dto.getResume_id());
		resume.setTitle(dto.getResume_title());

		dto.setResume_id(resume.getId());
		dto.setUser_img(resume.getImg());
		dto.setResume_title(resume.getTitle());
		dto.setUser_id(resume.getMember().getId());
		return dto;
	}

	@Transactional
	public UserResumeDTO personalInfoUpdate(UserResumeDTO dto) {
		UserResumeDTO userResumeDto = setPersonalMulti(dto);
		Resume resume = repo.findOne(userResumeDto.getResume_id());
		UserResumeDTO userResume = personResumeUpdate(userResumeDto, resume);
		return userResume;
	}

	private UserResumeDTO personResumeUpdate(UserResumeDTO userResumeDto, Resume resume) {
		if(userResumeDto.getZip_cd() != resume.getMember().getZip_cd()) {
			resume.getMember().setZip_cd(userResumeDto.getZip_cd());
			resume.getMember().setAddress_info(userResumeDto.getAddress_info());
		}
		
		if(userResumeDto.getAddress_detail() != resume.getMember().getAddress_detail()) 
			resume.getMember().setAddress_detail(userResumeDto.getAddress_detail());
		
		if(userResumeDto.getResume_title() != resume.getTitle())
			resume.setTitle(userResumeDto.getResume_title());
		
		if(userResumeDto.getUser_birthday() != resume.getMember().getBirthday())
			resume.getMember().setBirthday(userResumeDto.getUser_birthday());
		
		if(userResumeDto.getUser_email() != resume.getMember().getEmail()) 
			resume.getMember().setEmail(userResumeDto.getUser_email());
		
		if(userResumeDto.getUser_img() != null)
			resume.setImg(userResumeDto.getUser_img());
		else
			userResumeDto.setUser_img(resume.getImg());
		
		if(userResumeDto.getUser_nm() != resume.getMember().getName())
			resume.getMember().setName(userResumeDto.getUser_nm());
		
		if(userResumeDto.getUser_phone_num() != resume.getMember().getPhoneNum())
			resume.getMember().setPhoneNum(userResumeDto.getUser_phone_num());
		
		return userResumeDto;
	}

	private UserResumeDTO setPersonalMulti(UserResumeDTO dto) {
		
		MultipartFile file = dto.getFile();
		
		if (file == null || file.isEmpty()) {
			return dto;
		}
		
		folderDelete(dto.getUser_id());
		dto.setUser_img(userImgSave(file, dto.getUser_id()));
		
		return dto;
	}

	
}
