package com.jobworld.project.dto.request.user.resume;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UserResumeMultiRequestDto {
    private int resumeId;
    private String userId;
    private String userImg;
    private String resumeTitle;
    private MultipartFile file;
}
