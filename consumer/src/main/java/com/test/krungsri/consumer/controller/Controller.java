package com.test.krungsri.consumer.controller;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.test.krungsri.consumer.config.JwtTokenUtil;
import com.test.krungsri.consumer.model.UserModel;
import com.test.krungsri.consumer.service.RegisterService;



@RestController
public class Controller {
	
	@Autowired
	private RegisterService registerService; 
	
	@Autowired
	private JwtTokenUtil jwtTokenUtil;
	
	@RequestMapping({ "/test" })
	public String testToken(@RequestHeader("Authorization") String auth) {
		String jwtToken = null;
		if (auth != null && auth.startsWith("Bearer ")) {
			jwtToken = auth.substring(7);
			jwtTokenUtil.getUsernameFromToken(jwtToken);
			System.out.println(jwtTokenUtil.getUsernameFromToken(jwtToken));
		}
		
		return null;
	}
	
	@RequestMapping(value = "/register", method = RequestMethod.POST)
	public String register(@RequestBody UserModel userModel) throws Exception { 
		
		Optional<UserModel> usermodel = registerService.findById(userModel.getUsername());
		if(!usermodel.isPresent()) {
			if(userModel.getSalary().compareTo(new BigDecimal(50000)) > 0) {
				userModel.setMemberType("Platinum");
			}else if(userModel.getSalary().compareTo(new BigDecimal(29999)) > 0 && userModel.getSalary().compareTo(new BigDecimal(50000)) <= 0) {
				userModel.setMemberType("Gold");
			}else if(userModel.getSalary().compareTo(new BigDecimal(30000)) < 0) {
				userModel.setMemberType("Silver");
			}else {
				throw new Exception("You salary is less than more standard");
			}
			
			userModel.setRegisterDate(new Date());
			SimpleDateFormat dateFormat = new SimpleDateFormat("YYYYMMDD");
			String referenceCode = dateFormat.format(userModel.getRegisterDate()).concat(userModel.getPhone().substring(6,10));
			userModel.setReferenceCode(referenceCode);
			
			registerService.saveModel(userModel);
			
			
		}
		
		return "Your welcome to us member";
	}

	

}
