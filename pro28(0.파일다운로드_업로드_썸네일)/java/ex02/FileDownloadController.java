package com.myspring.pro28.ex02;

import java.io.File;
import java.io.FileInputStream;
import java.io.OutputStream;

import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import net.coobird.thumbnailator.Thumbnails;

@Controller
public class FileDownloadController {
	private static String CURR_IMAGE_REPO_PATH = "C:\\spring\\image_repo";
	
	@RequestMapping("/download")
	protected void download(@RequestParam("imageFileName") String imageFileName,
							HttpServletResponse response) throws Exception {
		OutputStream out = response.getOutputStream();
		String filePath = CURR_IMAGE_REPO_PATH + "\\" + imageFileName;
		File image = new File(filePath);
		
		//확장자를 제외한 원본 이미지 파일의 이름을 가져옵니다.
		int lastIndex = imageFileName.lastIndexOf(".");
		String fileName = imageFileName.substring(0,lastIndex);
		
		//원본 이미지 파일이름과 같은 이름의 썸네일 파일에 대한 File 객체를 생성합니다.
		File thumbnail = new File(CURR_IMAGE_REPO_PATH+"\\"+"thumbnail"+"\\"+fileName+".png");
		//원본 이미지 파일을 가로세로가 50픽셀인 png형식의 썸네일 이미지 파일로 생성합니다.
		if(image.exists()) {
			thumbnail.getParentFile().mkdirs();
			Thumbnails.of(image).size(50,50).outputFormat("png").toFile(thumbnail);
		}
		
		//생성된 썸네일 파일을 브라우저로 전송합니다.
		FileInputStream in = new FileInputStream(thumbnail);
		byte[] buffer = new byte[1024*8];
		while (true) {
			int count = in.read(buffer); //버퍼에 읽어들인 문자개수
			if (count == -1) //버퍼의 마지막에 도달했는지 체크
				break;
			out.write(buffer, 0, count);
		}
		in.close();
		out.close();
	}
	
/* 썸네일폴더는 따로 생성하지않으며 원본이미지를 썸네일 이미지로 바로 출력하는 방법
	@RequestMapping("/download")
	protected void download(@RequestParam("imageFileName") String imageFileName,
							HttpServletResponse response) throws Exception {
		OutputStream out = response.getOutputStream();
		String filePath = CURR_IMAGE_REPO_PATH + "\\" + imageFileName;
		File image = new File(filePath);
		
		//확장자를 제외한 원본 이미지 파일의 이름을 가져옵니다.
		int lastIndex = imageFileName.lastIndexOf(".");
		String fileName = imageFileName.substring(0,lastIndex);
		
		//원본 이미지 파일이름과 같은 이름의 썸네일 파일에 대한 File 객체를 생성합니다.
		File thumbnail = new File(CURR_IMAGE_REPO_PATH+"\\"+"thumbnail"+"\\"+fileName+".png");
		//원본 이미지 파일을 가로세로가 50픽셀인 png형식의 썸네일 이미지 파일로 생성합니다.
		if(image.exists()) {
			Thumbnails.of(image).size(50,50).outputFormat("png").toOutputStream(out);
		} else{
			
			return;
		}
		//생성된 썸네일 파일을 브라우저로 전송합니다.
		byte[] buffer = new byte[1024*8];		
		out.write(buffer);
		out.close();
	}
*/	
}
