package com.itwillbs.team1.controller;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

import com.itwillbs.team1.svc.NoticeService;
import com.itwillbs.team1.vo.NoticeBean;

@Controller
public class NoticeFrontController {
	
	@Autowired
	NoticeService service;
	
	@GetMapping(value = "/NoticeWriteForm.ad")
	public String NoticeWriteForm(Model model, HttpSession session) {
		
		String sId = (String)session.getAttribute("sId");
		if(sId == null || sId.equals("") || !sId.equals("admin")) {
			model.addAttribute("msg", "로그인 필수!");
			return "fail_back";
		}
		
		return "notice/qna_notice_write.jsp";
	}
	
	@PostMapping(value = "/NoticeWritePro.ad")
	public String NoticeWritePro(HttpSession session, @ModelAttribute NoticeBean notice, Model model) {
		
		String sId = (String)session.getAttribute("sId");
		if(sId == null || sId.equals("") || !sId.equals("admin")) {
			model.addAttribute("msg", "로그인 필수!");
			return "fail_back";
		}
		
		String uploadDir = "/resources/upload";
		String saveDir = session.getServletContext().getRealPath(uploadDir);
		
		Path path = Paths.get(saveDir);
		
		try {
			Files.createDirectories(path);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
		MultipartFile[] mFiles = notice.getFiles();
		
		String realFileNames = "";
		
		List<String> realFileNameList = new ArrayList<String>();
		
		for(MultipartFile mFile : mFiles) {
			// MultipartFile 객체의 getOriginalFilename() 메서드를 통해 파일명 꺼내기
			String originalFileName = mFile.getOriginalFilename();
//			long fileSize = mFile.getSize();
//			System.out.println("원본 파일명 : " + originalFileName);
//			System.out.println("파일크기 : " + fileSize + " Byte");
			
			// 1개의 파일명을 저장할 변수 선언
			String realFileName = "";
			
			// 가져온 파일이 있을 경우에만 중복 방지 대책 수행하기
			if(!originalFileName.equals("")) {
				// 파일명 중복 방지 대책
				// 시스템에서 랜덤ID 값을 추출하여 파일명 앞에 붙이기("랜덤ID값_파일명" 형식)
				// => 랜덤ID 값 추출은 UUID 클래스 활용(범용 고유 식별자)
				String uuid = UUID.randomUUID().toString();
	//			System.out.println("업로드 될 파일명 : " + uuid + "_" + originalFileName);
				
				// UUID 와 "_" 와 실제 파일명과 "/" 기호를 결합하여 파일명 생성
				realFileName = uuid + "_" + originalFileName + "/";
			}
			
			// 업로드될 파일명에 1개 파일명을 결합
			realFileNames += realFileName;
			// 각 파일명을 List 객체에도 추가
			// => MultipartFile 객체를 통해 실제 폴더로 이동 시킬 때 사용
			realFileNameList.add(realFileName);
			
		}
		
		notice.setNotice_file("");
		notice.setNotice_real_file(realFileNames);
		
		int insertCount = service.registNotice(notice);
		
		// 등록 성공/실패에 따른 포워딩 작업 수행
				if(insertCount > 0) { // 성공
					try {
						// 주의! 파일 등록 작업 성공 후 반드시 실제 폴더 위치에 업로드 수행 필요!
						// => MultipartFile 객체는 임시 경로에 파일을 업로드하므로
						//    작업 성공 시 transferTo() 메서드를 호출하여 실제 위치로 이동 작업 필요
						//    (파라미터 : new File(업로드경로, 업로드파일명)
						// MultipartFile 배열 크기만큼 반복 
						for(int i = 0; i < mFiles.length; i++) {
							// 하나씩 배열에서 객체 꺼내기
							// 가져온 파일이 있을 경우에만 파일 이동 작업 수행
							if(!mFiles[i].getOriginalFilename().equals("")) {
								// List 객체에 저장된 파일명을 사용하여 해당 파일을 실제 위치로 이동
								mFiles[i].transferTo(
									new File(saveDir, realFileNameList.get(i)));
							}
						}
					} catch (IllegalStateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}
					
					return "redirect:/NoticeList.ad";
				} else { // 실패
					// "msg" 속성명으로 "글 쓰기 실패!" 메세지 전달 후 fail_back 포워딩
					model.addAttribute("msg", "글 쓰기 실패!");
					return "fail_back";
				}
				
			}
		
		
		
	
	
	
}
