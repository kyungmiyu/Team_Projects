package com.blackberry.s20240130103.lhs.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.blackberry.s20240130103.kph.model.KphTask;
import com.blackberry.s20240130103.kph.model.KphUsers;
import com.blackberry.s20240130103.kph.service.KphProjectService;
import com.blackberry.s20240130103.lhs.domain.User;
import com.blackberry.s20240130103.lhs.model.Address;
import com.blackberry.s20240130103.lhs.model.Schedule;
import com.blackberry.s20240130103.lhs.service.AddressService;
import com.blackberry.s20240130103.lhs.service.EvalService;
import com.blackberry.s20240130103.lhs.service.ScheduleService;
import com.blackberry.s20240130103.lhs.service.UserService;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.mail.internet.MimeMessage;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import jakarta.servlet.http.Part;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class LhsController {
	
	private final JavaMailSender mailSender;
	private final UserService userService;
	private final EvalService evalService;
	private final KphProjectService kphProjectService;
	private final AddressService addressService;
	private final ScheduleService scheduleService;
	
	@GetMapping(value = "loginForm")
	public String loginForm() {
		return "lhs/loginForm";
	}
	
	@GetMapping(value = "jointerms")
	public String joinTerms() {
		return "lhs/jointerms";
	}
	
	@GetMapping(value = "joinEmailForm")
	public String joinEmail() {
		return "lhs/joinemail";
	}
	
	@GetMapping("userChangePassword")
	public String changePass() {
		return "lhs/userChangePassword";
	}
	
	@GetMapping(value = "joinForm")
	public String joinForm(@RequestParam(name = "email") String email,Model model) {
		model.addAttribute("email",email);
		return "lhs/joinForm";
	}
	
	private void sendEmail(String title,String content,String email) {
		try {
			MimeMessage message = mailSender.createMimeMessage();
			MimeMessageHelper messageHelper = new MimeMessageHelper(message,true,"UTF-8");
			messageHelper.setFrom("dlgkstnrn@gmail.com");
			messageHelper.setTo(email);
			messageHelper.setSubject(title);
			messageHelper.setText(content);
			mailSender.send(message);
		}catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
	@ResponseBody
	@PostMapping(value = "emailChkAjax")
	public int emailChkAjax(@RequestParam(name = "email")String email) {
		StringBuilder sb = new StringBuilder();
		int chkNum = (int)(Math.random()*9000)+1000;
		sb.append("안녕하세요 반갑습니다.\n");
		sb.append("인증번호 : "+chkNum);
		System.out.println("인증번호 : " + chkNum);
		sendEmail("인증번호",sb.toString(),email);
		return chkNum;
	}
	
	@PostMapping(value = "userJoin")
	public String userJoin(HttpServletRequest request) throws IOException, ServletException {
		User user = new User();
		user.setUser_id(request.getParameter("user_id"));
		user.setUser_pw(request.getParameter("user_pw"));
		user.setUser_name(request.getParameter("user_name"));
		user.setUser_email(request.getParameter("user_email"));
		user.setUser_nic(request.getParameter("user_nic"));;
		user.setUser_phone(request.getParameter("user_phone"));
		user.setUser_delete_chk(0);
		Part part = request.getPart("user_profile");
		if(part.getSize()!=0) {
			String uploadPath = request.getSession().getServletContext().getRealPath("/upload/userImg/");
			user.setUser_profile(upLoadImg(part,request.getParameter("user_id"),uploadPath));
		}
		userService.joinUser(user);
		System.out.println("조인성공");
		return "lhs/loginForm";
	}
	
	//파일 업로드 부분
	private String upLoadImg(Part image,String id,String uploadPath) throws IOException {
		FileOutputStream outputStream = null;
		String savedName = null;
		try {
			InputStream is = image.getInputStream();
			String suffix = image.getSubmittedFileName().split("\\.")[1];
			File fileDirectory = new File(uploadPath);
			if(!fileDirectory.exists()) {
				fileDirectory.mkdirs();
			}
			savedName = id + "." + suffix;
			File imgFile = new File(uploadPath+savedName);
			outputStream = new FileOutputStream(imgFile);
			int read;
			byte[] bytes = new byte[1024];
			while((read=is.read(bytes))!=-1) {
				outputStream.write(bytes,0,read);
			}
			outputStream.flush();
		} catch (IOException e) {
			e.printStackTrace();
		}finally {
			if(outputStream!=null) {
				outputStream.close();
			}
		}
		return savedName;
	}
	
	@ResponseBody
	@PostMapping(value = "joinIdChk")
	public int joinIdChk(@RequestParam(name = "id")String id) {
		int result = userService.joinIdChk(id);
		return result;
	}
	
	@PostMapping("userLogin")
	public String userLogin(User user,HttpServletRequest request) {
		int result = userService.loginChk(user);
		if(result==1) {
			request.getSession().invalidate();
			HttpSession session = request.getSession(true);
			session.setAttribute("user_no", user.getUser_no());
			session.setAttribute("user_name", user.getUser_name());
			session.setAttribute("user_profile", user.getUser_profile());
			session.setAttribute("user_nic", user.getUser_nic());
			System.out.println("LHSController session user no : " + session.getAttribute("user_no"));
			System.out.println("LHSController session user name : " + session.getAttribute("user_name"));
			System.out.println("LHSController session user profile : " + session.getAttribute("user_profile"));
			System.out.println("LHSController session user nic : " + session.getAttribute("user_nic"));
			return "redirect:/main";
		}else if(result ==2){
			System.out.println("관리자계정 로그인");
			request.getSession().invalidate();
			HttpSession session = request.getSession(true);
			session.setAttribute("user_no", user.getUser_no());
			session.setAttribute("user_name", user.getUser_name());
			session.setAttribute("admin", true);
			return "redirect:/adminMain";
		}else {
			request.setAttribute("islogin", 0);
			return "lhs/loginForm";
		}
	}
	
	@GetMapping("userLogout")
	public String userLogout(HttpServletRequest request) {
		request.getSession().invalidate();
		return "thymeleaf/index";
	}
	
	@GetMapping("myPage")
	public String userMypage(HttpServletRequest request,Model model) {
		String userNo = request.getSession().getAttribute("user_no").toString();
		User user = userService.findUserByNo(userNo);
		double scoreavg = evalService.avgScoreByNo(userNo);
		model.addAttribute("user", user);
		model.addAttribute("user_date", java.sql.Timestamp.valueOf(user.getUser_date()));
		model.addAttribute("user_score", (int)scoreavg);
		return "lhs/userMypage";
	}
	
	@PostMapping("userUpdate")
	public String userUpdate(HttpServletRequest request) throws IOException, ServletException {
		String userNo = request.getSession().getAttribute("user_no").toString();
		User user = new User();
		user.setUser_name(request.getParameter("user_name"));
		user.setUser_nic(request.getParameter("user_nic"));;
		user.setUser_phone(request.getParameter("user_phone"));
		Part part = request.getPart("user_profile");
		if(part.getSize()!=0) {
			String uploadPath = request.getSession().getServletContext().getRealPath("/upload/userImg/");
			user.setUser_profile(upLoadImg(part,request.getParameter("user_id"),uploadPath));
		}
		userService.updateUser(user,userNo);
		HttpSession session = request.getSession();
		session.removeAttribute("user_name");
		session.setAttribute("user_name", user.getUser_name());
		if(user.getUser_profile() != null) {
			session.removeAttribute("user_profile");
			session.setAttribute("user_profile", user.getUser_profile());
		}
		return "redirect:/myPage";
	}
	
	@PostMapping("changePassword")
	public String userChangePassword(HttpServletRequest request,
									Model model,
									@RequestParam(name = "password")String oldPassword,
									@RequestParam(name = "newpassword")String newPassword) {
		String userNo = request.getSession().getAttribute("user_no").toString();
		int result = userService.updatePasswordUser(userNo,oldPassword,newPassword);
		if(result==1) {
			return "redirect:/myPage";
		}else {
			model.addAttribute("result",result);
			return "lhs/userChangePassword";
		}
	}
	
	@GetMapping("userLeaveForm")
	public String userLeaveForm() {
		return "lhs/userLeave";
	}
	
	@PostMapping("userLeave")
	public String userLeave(HttpServletRequest request,Model model,
				@RequestParam(name = "passwd1")String passwd) {
		String userNo = request.getSession().getAttribute("user_no").toString();
		int result = userService.chkPasswordUser(passwd,userNo);
		if(result==1) {
			request.getSession().invalidate();
		}
		model.addAttribute("result",result);
		return "lhs/userLeave";
	}
	
	@GetMapping("idSearchForm")
	public String userIdSearchForm() {
		return "lhs/idSearch";
	}
	
	@PostMapping("userFindId")
	public String userIdSearch(@RequestParam(name = "user_email")String email,Model model) {
		List<User> user = userService.findIdByemail(email);
		//User user = userService.findIdByemail(email);
		model.addAttribute("user",user);
		return "lhs/idSearchResult";
	}
	
	@GetMapping("passSearchForm")
	public String userPassSearchForm() {
		return "lhs/passSearch";
	}
	
	@PostMapping("userFindPass")
	public String userPassSearch(User user,Model model) {
		
		Optional<User> finduser = userService.findPassByIdEmail(user);
		if(finduser.isPresent()) {
			System.out.println(finduser.get());
			StringBuilder sb = new StringBuilder();
			sb.append("가입하신 아이디에 비밀번호 변경 주소입니다\n");
			sb.append("주소 : http://localhost:8989/passwordChangeForm?no=" +finduser.get().getUser_no());
			sendEmail("비밀번호 변경 주소",sb.toString(),finduser.get().getUser_email());
			model.addAttribute("result", 1);
		}else {
			model.addAttribute("result", 0);
		}
		return "lhs/passSearch";
	}
	
	@GetMapping("passwordChangeForm")
	public String userPassChangeForm(@RequestParam(name="no")String no,Model model) {
		model.addAttribute("user_no", no);
		return "lhs/passChangeForm";
	}
	
	@PostMapping("passwordChange")
	public String passwordChange(User user,Model model) {
		int result = userService.passwordChange(user);
		model.addAttribute("result", result);
		return "lhs/passChangeForm";
	}
	
	@GetMapping("address")
	public String addressForm() {	
		return "lhs/address";
	}
	
	@ResponseBody
	@GetMapping("addressList")
	public List<KphUsers> addressList(HttpServletRequest request){
		List<KphUsers> addressUserList = kphProjectService.addressUserList(Long.parseLong(request.getSession().getAttribute("user_no").toString()));
		return addressUserList;
	}
	
	@ResponseBody
	@GetMapping("addressRequestList")
	public List<KphUsers> addressRequestList(HttpServletRequest request){
		List<KphUsers> addressRequestList = addressService.addressRequestList(Long.parseLong(request.getSession().getAttribute("user_no").toString()));
		return addressRequestList;
	}
	
	@ResponseBody
	@GetMapping("addressResponseList")
	public List<KphUsers> addressResponseList(HttpServletRequest request){
		List<KphUsers> addressResponseList = addressService.addressResponseList(Long.parseLong(request.getSession().getAttribute("user_no").toString()));
		return addressResponseList;
	}
	
	@GetMapping("addressaddForm")
	public String addressaddForm() {
		return "lhs/addressaddForm";
	}
	
	@ResponseBody
	@PostMapping("addressIdSearch")
	public User addressIdSearch(HttpServletRequest request,
								@RequestParam(name = "user_id")String userId) {
		Long loginuserNo = (Long) request.getSession().getAttribute("user_no");
		Optional<User> user = userService.findUserById(userId);
		//Long Long 비교는 equals 써야한다?
		if(user.isPresent() && !(user.get().getUser_no().equals(loginuserNo))) {
			double scoreavg = evalService.avgScoreByNo(user.get().getUser_no().toString());
			user.get().setEval_score(scoreavg);
			Address address = new Address();
			address.setUser_no(loginuserNo);
			address.setRe_user_no(user.get().getUser_no());
			int addresschk = addressService.addresschkcnt(address);
			user.get().setAddress_chk(addresschk);
			return user.get();
		}else {
			return null;
		}
	}
	
	@GetMapping("addressAdd")
	public String addressAdd(@RequestParam("re_user_no")String re_user_no,
							HttpServletRequest request) {
		Long loginuserNo = (Long) request.getSession().getAttribute("user_no");
		Address address = new Address();
		address.setUser_no(loginuserNo);
		address.setRe_user_no(Long.parseLong(re_user_no));
		int result = addressService.addressAdd(address);
		if(result == 0) {
			return "redirect:/addressaddForm";
		}else {
			return "redirect:/address";
		}
	}
	
	@GetMapping("addressResponsePermit")
	public String addressResponsePermit(@RequestParam("user_no")String user_no,HttpServletRequest request) {
		Long re_user_no = (Long) request.getSession().getAttribute("user_no");
		Address address = new Address();
		address.setRe_user_no(re_user_no);
		address.setUser_no(Long.parseLong(user_no));
		int result = addressService.addressResponsePermit(address);
		return "redirect:/address";
	}
	
	@GetMapping("addressResponseDeny")
	public String addressResponseDeny(@RequestParam("user_no")String user_no,HttpServletRequest request) {
		Long re_user_no = (Long) request.getSession().getAttribute("user_no");
		Address address = new Address();
		address.setRe_user_no(re_user_no);
		address.setUser_no(Long.parseLong(user_no));
		int result = addressService.addressResponseDeny(address);
		return "redirect:/address";
	}
	
	@GetMapping("addressRequestDelete")
	public String addressRequestDelete(@RequestParam("re_user_no")String re_user_no,HttpServletRequest request) {
		Long user_no = (Long) request.getSession().getAttribute("user_no");
		Address address = new Address();
		address.setRe_user_no(Long.parseLong(re_user_no));
		address.setUser_no(user_no);
		System.out.println("addressRequestDelete address : " + address);
		int result = addressService.addressRequestDelete(address);
		return "redirect:/address";
	}
	
	@GetMapping("proejctSchedule")
	public String scheduleMain(Schedule schedule,Model model) {
		List<Schedule> scheduleList = scheduleService.scheduleList(schedule);
		model.addAttribute("project_no", schedule.getProject_no());
		model.addAttribute("scheduleList", scheduleList);
		return "lhs/scheduleMain";
	}
	
	@GetMapping("scheduleAddForm")
	public String scheduleAddForm(Schedule schedule,Model model) {
		model.addAttribute("project_no", schedule.getProject_no());
		return "lhs/scheduleAddForm";
	}
	
	@PostMapping("scheduleAdd")
	public String scheduleAdd(Schedule schedule,HttpServletRequest request,RedirectAttributes redirect) {
		schedule.setUser_no(Long.parseLong(request.getSession().getAttribute("user_no").toString()));
		int result = scheduleService.scheduleAdd(schedule);
		redirect.addAttribute("project_no", schedule.getProject_no());
		return "redirect:/proejctSchedule";
	}
	
	@GetMapping("scheduleDelete")
	public String scheduleDelete(Schedule schedule,RedirectAttributes redirect) {
		int result = scheduleService.scheduleDelete(schedule);
		redirect.addAttribute("project_no", schedule.getProject_no());
		return "redirect:/proejctSchedule";
	}
	
	@GetMapping("scheduleUpdateForm")
	public String scheduleUpdateForm(Schedule schedule,Model model) {
		Schedule sc = scheduleService.scheduleSelectOne(schedule);
		System.out.println(sc);
		model.addAttribute("schedule", sc);
		return "lhs/scheduleUpdateForm";
	}
	
	@PostMapping("scheduleUpdate")
	public String scheduleUpdate(Schedule schedule,RedirectAttributes redirect) {
		int result = scheduleService.scheduleUpdate(schedule);
		redirect.addAttribute("project_no", schedule.getProject_no());
		return "redirect:/proejctSchedule";
	}
	
	@PostMapping("getTaskAndSchedule")
	@ResponseBody
	public String scheduleAndTask(@RequestParam(name = "project_no") String project_no){
		Schedule schedule = new Schedule();
		schedule.setProject_no(Long.parseLong(project_no));
		List<Schedule> scheduleList = scheduleService.scheduleList(schedule);
		KphTask kphTask = new KphTask();
		kphTask.setProject_no(Long.parseLong(project_no));
		List<KphTask> taskList = (List<KphTask>)kphProjectService.detailProject(kphTask).get("taskList");
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("schedule", scheduleList);
		returnMap.put("task", taskList);
		Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd").create();
		String gsonStrValue = gson.toJson(returnMap);
		System.out.println("gson : " + gsonStrValue);
		return gsonStrValue;
	}
	
}
