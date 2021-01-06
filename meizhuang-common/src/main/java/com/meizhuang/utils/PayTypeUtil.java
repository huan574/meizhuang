package com.meizhuang.utils;

public class PayTypeUtil {
	
	public static String[] getPayTypeLogo(int payType) {
		String imageName[]=new String[2] ;
		switch (payType) {
		case 0:
			imageName[0]="weixin.png";
        	imageName[1]="微信";
			break;
        case 1:
            imageName[0]="alipay.png";
			imageName[1]="支付宝";
			break;
        case 3:
        	imageName[0]="qq.png";
        	imageName[1]="QQ钱包";
			break;
        case 4:
        	imageName[0]="jingdong.png";
        	imageName[1]="京东";
			break;
        case 6:
            imageName[0]="yunsanfu.png";
        	imageName[1]="云闪付";
			break;

		default:
			imageName[0]="alipay.png";
        	imageName[1]="支付宝";
			break;
		}
		return imageName;
	}

}
