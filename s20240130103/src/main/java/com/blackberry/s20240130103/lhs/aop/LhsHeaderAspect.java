package com.blackberry.s20240130103.lhs.aop;

import java.util.List;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.blackberry.s20240130103.kdw.model.Message;
import com.blackberry.s20240130103.lhs.model.Address;
import com.blackberry.s20240130103.lhs.service.HeaderMessageService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;

@Aspect
@Component
@RequiredArgsConstructor
public class LhsHeaderAspect {
	
	private final HeaderMessageService headerMessageService;
	
	@Around("execution(* com.blackberry.s20240130103..controller.*.*(..))")
	public Object getNewMessage(ProceedingJoinPoint joinpoint) throws Throwable {
		ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.currentRequestAttributes();
        HttpServletRequest request = attributes.getRequest();
        HttpSession session = request.getSession();
        session.removeAttribute("headerList");
        session.removeAttribute("messageCnt");
        session.removeAttribute("addressRequestCnt");
        session.removeAttribute("addressRequestUserName");
        if(session.getAttribute("user_no") != null) {
        	int noreadMessageCnt = headerMessageService.selectNoReadMessageCnt(session.getAttribute("user_no").toString());
        	List<Message> headerThreeMessageList = headerMessageService.getThreeMessage(session.getAttribute("user_no").toString());
        	
        	int noreadAddressRequestCnt = headerMessageService.selectNoReadAddressRequestCnt(session.getAttribute("user_no").toString());
        	List<String> headerFourAddressRequestUserName = headerMessageService.getFourAddressRequestUserName(session.getAttribute("user_no").toString());
        	
        	session.setAttribute("headerList", headerThreeMessageList);
        	session.setAttribute("messageCnt", noreadMessageCnt);
        	session.setAttribute("addressRequestCnt", noreadAddressRequestCnt);
        	session.setAttribute("addressRequestUserName", headerFourAddressRequestUserName);
        }
        return joinpoint.proceed();
	}
}
