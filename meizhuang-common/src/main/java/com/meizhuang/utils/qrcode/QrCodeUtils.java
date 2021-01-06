package com.meizhuang.utils.qrcode;

import java.awt.BasicStroke;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Shape;
import java.awt.geom.RoundRectangle2D;
import java.awt.image.BufferedImage;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.util.HashMap;
import java.util.Hashtable;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.BinaryBitmap;
import com.google.zxing.DecodeHintType;
import com.google.zxing.EncodeHintType;
import com.google.zxing.LuminanceSource;
import com.google.zxing.MultiFormatReader;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.Result;
import com.google.zxing.client.j2se.BufferedImageLuminanceSource;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.common.HybridBinarizer;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import com.meizhuang.exception.BizExceptionEnum;
import com.meizhuang.exception.BussinessException;
import com.meizhuang.result.JsonResult;
import com.meizhuang.utils.file.OssClient;
import com.meizhuang.utils.request.HttpClientUtil;

import cn.hutool.core.io.FileUtil;

/**
 * 二维码工具类
 */
public class QrCodeUtils {

	private static Logger logger = Logger.getLogger(QrCodeUtils.class);
	
	private static final String CHARSET = "utf-8";

	private static final String FORMAT = "JPG";
	// 二维码尺寸
	private static final int QRCODE_SIZE = 300;
	// LOGO宽度
	private static final int LOGO_WIDTH = 60;
	// LOGO高度
	private static final int LOGO_HEIGHT = 60;

	public static JsonResult<BufferedImage> encode(String content) {
		try {
			BufferedImage image = QrCodeUtils.createImage(content, null, false);
			return JsonResult.buildSuccess(image);
		} catch (Exception e) {
			logger.error(e);
			throw new BussinessException(BizExceptionEnum.FILE_SAVE_ERROR);
		}
	}

	public static JsonResult<String> encodeToYun(String content) {
		try {
			BufferedImage image = QrCodeUtils.createImage(content, null, false);
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			ImageIO.write(image, FORMAT, os);
			InputStream is = new ByteArrayInputStream(os.toByteArray());
			return OssClient.uploadFile(is, FORMAT);
		} catch (Exception e) {
			logger.error(e);
			throw new BussinessException(BizExceptionEnum.FILE_SAVE_ERROR);
		}
	}

	public static JsonResult<String> encode(String content, String destPath, String fileName) {
		return encode(content, null, destPath, fileName, false);
	}

	/**
	 * 生成二维码(内嵌LOGO) 调用者指定二维码文件名
	 * 
	 * @param content内容
	 * @param logoPathLOGO地址
	 * @param destPath存放目录
	 * @param fileName二维码文件名
	 * @param needCompress是否压缩LOGO
	 */
	public static JsonResult<String> encode(String content, String logoPath, String destPath, String fileName, boolean needCompress) {
		try {
			BufferedImage image = QrCodeUtils.createImage(content, logoPath, needCompress);
			FileUtil.mkdir(destPath);
			fileName = fileName.substring(0, fileName.indexOf(".") > 0 ? fileName.indexOf(".") : fileName.length()) + "." + FORMAT.toLowerCase();
			File file = new File(destPath + "/" + fileName);
			ImageIO.write(image, FORMAT, file);
			return JsonResult.buildSuccess("成功", file.getCanonicalPath());
		} catch (Exception e) {
			logger.error(e);
			throw new BussinessException(BizExceptionEnum.FILE_SAVE_ERROR);
		}
	}

	private static BufferedImage createImage(String content, String logoPath, boolean needCompress) throws Exception {
		Hashtable<EncodeHintType, Object> hints = new Hashtable<EncodeHintType, Object>();
		hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.H);
		hints.put(EncodeHintType.CHARACTER_SET, CHARSET);
		hints.put(EncodeHintType.MARGIN, 1);
		BitMatrix bitMatrix = new MultiFormatWriter().encode(content, BarcodeFormat.QR_CODE, QRCODE_SIZE, QRCODE_SIZE, hints);
		int width = bitMatrix.getWidth();
		int height = bitMatrix.getHeight();
		BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				image.setRGB(x, y, bitMatrix.get(x, y) ? 0xFF000000 : 0xFFFFFFFF);
			}
		}
		if (logoPath == null || "".equals(logoPath)) {
			return image;
		}
		// 插入图片
		QrCodeUtils.insertImage(image, logoPath, needCompress);
		return image;
	}

	/**
	 * 插入LOGO
	 */
	private static void insertImage(BufferedImage source, String logoPath, boolean needCompress) throws Exception {
		File file = new File(logoPath);
		if (!file.exists()) {
			throw new Exception("logo file not found.");
		}
		Image src = ImageIO.read(new File(logoPath));
		insertImage(source, src, needCompress);
	}

	private static void insertImage(BufferedImage source, Image src, boolean needCompress) {
		int width = src.getWidth(null);
		int height = src.getHeight(null);
		if (needCompress) { // 压缩LOGO
			if (width > LOGO_WIDTH) {
				width = LOGO_WIDTH;
			}
			if (height > LOGO_HEIGHT) {
				height = LOGO_HEIGHT;
			}
			Image image = src.getScaledInstance(width, height, Image.SCALE_SMOOTH);
			BufferedImage tag = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
			Graphics g = tag.getGraphics();
			g.drawImage(image, 0, 0, null); // 绘制缩小后的图
			g.dispose();
			src = image;
		}
		// 插入LOGO
		Graphics2D graph = source.createGraphics();
		int x = (QRCODE_SIZE - width) / 2;
		int y = (QRCODE_SIZE - height) / 2;
		graph.drawImage(src, x, y, width, height, null);
		Shape shape = new RoundRectangle2D.Float(x, y, width, width, 6, 6);
		graph.setStroke(new BasicStroke(3f));
		graph.draw(shape);
		graph.dispose();
	}

	public static JsonResult<String> decodeFile(String filePath) {
		try {
			File file = new File(filePath);
			BufferedImage image = ImageIO.read(file);
			return decode(image);
		} catch (Exception e) {
			logger.error(e);
			throw new BussinessException(BizExceptionEnum.FILE_READING_ERROR);
		}
	}

	public static JsonResult<String> decodeURL(String urlPath) {
		try {
			URL url = new URL(urlPath);
			BufferedImage image = ImageIO.read(url);
			return decode(image);
		} catch (Exception e) {
			logger.error(e);
			throw new BussinessException(BizExceptionEnum.FILE_READING_ERROR);
		}
	}

	public static JsonResult<String> decodeURLByCLI(String urlPath) {
		try {
			String url = "https://cli.im/Api/Browser/deqr";
			Map<String, String> mapParams = new HashMap<String, String>();
			mapParams.put("data", urlPath);
			logger.info("mapParams:"+mapParams);
			JsonResult<String> result = HttpClientUtil.sendPaymentPostByParams(url, mapParams);
			logger.info(" result:"+ result.getData());
			if (!result.isSuccess()) {
				return JsonResult.buildError(BizExceptionEnum.FILE_READING_ERROR);
			}
			JSONObject resJson = JSON.parseObject(result.getMsg());
			String status = resJson.getString("status");
			JSONObject data = resJson.getJSONObject("data");
			if (!"1".equals(status) || data == null) {
				return JsonResult.buildError(BizExceptionEnum.FILE_READING_ERROR);
			}
			String qrUrl = data.getString("RawData");
			if (StringUtils.isBlank(qrUrl)) {
				return JsonResult.buildError(BizExceptionEnum.FILE_READING_ERROR);
			}
			return JsonResult.buildSuccess("成功", qrUrl);
		} catch (Exception e) {
			logger.error(e);
			throw new BussinessException(BizExceptionEnum.FILE_READING_ERROR);
		}
	}

	public static JsonResult<String> decodeURLByOsChina(String urlPath) {
		try {
			String url = "http://tool.oschina.net/action/qrcode/decode";
			Map<String, String> mapParams = new HashMap<String, String>();
			mapParams.put("data", "on");
			mapParams.put("url", urlPath);
			JsonResult<String> result = HttpClientUtil.sendPaymentPostByParams(url, mapParams);
			if (!result.isSuccess()) {
				return JsonResult.buildError(BizExceptionEnum.FILE_READING_ERROR);
			}
			JSONArray array = JSON.parseArray(result.getMsg());
			JSONObject object = array.getJSONObject(0);
			String qrUrl = object.getString("text");
			if (StringUtils.isBlank(qrUrl)) {
				return JsonResult.buildError(BizExceptionEnum.FILE_READING_ERROR);
			}
			return JsonResult.buildSuccess("成功", qrUrl);
		} catch (Exception e) {
			logger.error(e);
			throw new BussinessException(BizExceptionEnum.FILE_READING_ERROR);
		}
	}

	private static JsonResult<String> decode(BufferedImage image) {
		if (image == null) {
			return JsonResult.buildError(BizExceptionEnum.FILE_READING_ERROR);
		}
		try {
			LuminanceSource source = new BufferedImageLuminanceSource(image);
			BinaryBitmap bitmap = new BinaryBitmap(new HybridBinarizer(source));
			Result result;
			Hashtable<DecodeHintType, Object> hints = new Hashtable<DecodeHintType, Object>();
			// 解码设置编码方式为：utf-8，
			hints.put(DecodeHintType.CHARACTER_SET, CHARSET);
			// 优化精度
			hints.put(DecodeHintType.TRY_HARDER, Boolean.TRUE);
			// 复杂模式，开启PURE_BARCODE模式
			hints.put(DecodeHintType.PURE_BARCODE, Boolean.TRUE);
			result = new MultiFormatReader().decode(bitmap, hints);
			String resultStr = result.getText();
			return JsonResult.buildSuccess("成功", resultStr);
		} catch (Exception e) {
			logger.error(e);
			e.printStackTrace();
			throw new BussinessException(BizExceptionEnum.FILE_READING_ERROR);
		}
	}

	public static void main(String[] args) throws Exception {
		JsonResult<String> result = null;
		String text = "test";
		result = QrCodeUtils.encode(text, "D:\\", "qrcode");
		if (result.isSuccess()) {
			System.out.println(result.getData());
		}

		String url = "https://lazyzero.oss-cn-hongkong.aliyuncs.com/28181e61-1521-4543-9986-9f82f25ea40b.png";
		result = QrCodeUtils.decodeURLByOsChina(url);
		if (result.isSuccess()) {
			System.out.println(result.getData());
		}
	}

}
