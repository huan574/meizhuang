package com.meizhuang.controller;

import java.awt.image.BufferedImage;
import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.meizhuang.result.JsonResult;
import com.meizhuang.utils.qrcode.QrCodeUtils;

@RestController
@RequestMapping(value = "/image")
public class ImageController {

	@RequestMapping(value = "/qrcode", produces = MediaType.IMAGE_JPEG_VALUE)
	public BufferedImage qrcode(@RequestParam(value = "text", required = true) String text) throws IOException {
		JsonResult<BufferedImage> result = QrCodeUtils.encode(text);
		if (result.isSuccess()) {
			return result.getData();
		}
		return null;
	}

}
