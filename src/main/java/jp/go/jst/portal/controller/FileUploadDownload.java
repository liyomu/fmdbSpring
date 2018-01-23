package jp.go.jst.portal.controller;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;

import javax.servlet.http.HttpServletResponse;

import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class FileUploadDownload {

	@RequestMapping("upload")
	public String viewUpload(@RequestParam String status,Model model) {
		// 入口をチェック
		if (!"00".equals(status)) {
			status = "99";
		}
		// 画面上に返す
		model.addAttribute("status", status);
		return "fileUpload";
	}

	/**
	 * ファイルアップロード用メソッド
	 * @param status ファイルアップロード状態
	 * @param file アップロードファイル
	 * @param model モジュール
	 * @return アップロード成功後のURL遷移
	 * @throws IOException IO例外
	 */
	@RequestMapping(value = "uploadFile", method = RequestMethod.POST)
	public String uploadFile(@RequestParam String status, @RequestParam MultipartFile file, Model model) throws IOException {
		System.out.println(status);
		// chatからアップロードするとき、ステータスを閉じるに変更する。
		if ("00".equals(status)) {
			status = "01";
		}
		// 保存先のファイルパス
		String dir = "C:\\tmp\\";
		// 保存先のファイル名
		String fileName = file.getOriginalFilename();
		
		// アップロードファイルのStream
		InputStream in = file.getInputStream();
		// ファイルを作成
		File saveFile = new File(dir + fileName);
		// ファイル内容を複写
		Files.copy(in, saveFile.toPath());
		System.out.println(file.getSize());
		model.addAttribute("status", status);
		return "fileUpload";
	}
	/**
	 * ファイルアップロード用メソッド
	 * @param status ファイルアップロード状態
	 * @param file アップロードファイル
	 * @param model モジュール
	 * @return アップロード成功後のURL遷移
	 * @throws Exception 例外
	 */
	@RequestMapping(value = "downloadFile")
	public void downloadFile(HttpServletResponse response) throws Exception {
		Resource file = new FileSystemResource("C:\\tmp\\file_ISMS2017.pdf");
		response.setContentType("text/plain");
		response.setContentLength((int)file.getFile().length());
		response.setHeader("Content-Disposition","attachment; filename=\"" + file.getFile().getName() +"\"");
		FileCopyUtils.copy(file.getInputStream(), response.getOutputStream());
	}
}
