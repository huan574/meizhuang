package com.meizhuang.job;



import java.util.Date;
import java.util.List;

import javax.annotation.PostConstruct;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.meizhuang.entity.User;
import com.meizhuang.utils.common.DateUtil;
import com.meizhuang.utils.support.NetworkUtils;

/**   
* 
*/
@Repository
public class LingDianJob implements Job {

	private static final Logger log = LoggerFactory.getLogger(LingDianJob.class);
	
	private static LingDianJob job;
	
	//@Autowired
	//private UserService userService;
	
	@PostConstruct
	public void init() {
		job = this;
		//job.userService = this.userService;
	}

	
	@Override
	public void execute(JobExecutionContext context) throws JobExecutionException {
		String localHost = NetworkUtils.getLocalHost();
		log.info("服务器【" + localHost + "】定时执行0点清数据开始时间：" + DateUtil.sdf.format(new Date()));
		
//		try{
//			job.cardService.clearTransferBankCard();
//		}catch(Exception e){
//			log.error(e.toString());
//		}
//		
//		try {
//
//			// 会员升级
//			EntityWrapper<UserLevelRequirement> wrapper = new EntityWrapper<UserLevelRequirement>();
//			List<UserLevelRequirement> list = job.userLevelRequirementService.selectList(wrapper);
//
//			List<UserGrabInfo> grabInfoList = job.userGrabInfoService.selectList(new EntityWrapper<UserGrabInfo>());
//
//			List<User> userList = job.userService.selectList(new EntityWrapper<User>());
//
//			for (User user : userList) {
//				boolean update = false;
//				for (UserGrabInfo grabInfo : grabInfoList) {
//					if (user.getUid().intValue() == grabInfo.getUid()) {
//						int grabCount = grabInfo.getPersonalGrabCount();
//						int inviteCount = grabInfo.getPersonalInviteCount() ;
//						double grabAmount = grabInfo.getPersonalGrabAmount().doubleValue();
//						for (UserLevelRequirement ulr : list) {
//							if (inviteCount >= ulr.getInviteCount() && 
//									grabCount >= ulr.getGrabCount()
//									&& grabAmount >= ulr.getGrabAmountCount().doubleValue()) {
//
//								if (user.getUserLevel().intValue() < ulr.getUserLevel()) {
//									update = true;
//									user.setUserLevel(ulr.getUserLevel().byteValue());
//								}
//
//							}
//						}
//
//						break;
//					}
//				}
//
//				if (update) {
//					job.userService.updateById(user);
//				}
//			}
//
//		} catch (Exception e) {
//			log.error(e.toString());
//		}
//		
		
		log.info("服务器【" + localHost + "】定时执行0点处理结束时间：" + DateUtil.sdf.format(new Date()));
	}

}
