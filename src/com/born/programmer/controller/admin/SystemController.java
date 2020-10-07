package com.born.programmer.controller.admin;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.born.programmer.entity.admin.User;
import com.born.programmer.service.admin.IUserService;
import com.born.programmer.util.CpachaUtil;

/**
 * 系统操作类控制器
 * 
 * @author 15188
 *
 */
@Controller
@RequestMapping("/system")
public class SystemController {


	@Autowired
	private IUserService userService;
	
	

	@RequestMapping(value = "index", method = RequestMethod.GET)
	public ModelAndView index(ModelAndView model) {
		model.setViewName("/system/index");
		model.addObject("nameByModelAndView", "modelAndView测试方法");
		return model;
	}
	
	
	
	/**
	 * 登录后跳转到欢迎页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "welcome", method = RequestMethod.GET)
	public ModelAndView welcome(ModelAndView model) {
		model.setViewName("system/welcome");
		return model;
	}
	

	/**
	 * 跳转到登录页面
	 * 
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "login", method = RequestMethod.GET)
	public ModelAndView login(ModelAndView model) {
		model.setViewName("system/login");
		return model;
	}

	/**
	 * 验证表单，登录系统
	 * 
	 * 先对实体类对象、用户名、密码、验证码做参数有效性校验，错误返回error 
	 * 校验session中是否保存了验证码，如果没有，判定为会话超时，返回error
	 * 查询用户名是否存在，查询该用户名的密码是否匹配，出错则返回error 
	 * 到达这一步，验证信息通过，此时查询用户的角色和权限信息，将其放入session
	 * 
	 * @param user 接收用户名和密码
	 * @param cpacha 接收用户输入的验证码
	 * @return 包含type和message的map，用于通知前端页面处理的结果和提示信息
	 */
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	@ResponseBody
	public Map<String,String> loginAction(User user, String cpacha, HttpServletRequest request) {
		Map<String,String> resultMap = new HashMap<String,String>(2);
		HttpSession session = request.getSession();
		//实际上对象不会为null，因为这是由mybatis自动创建的，未接到参数时只是一个各项值为空的对象而已
//		if (user == null) {
//			resultMap.put("type","error");
//			resultMap.put("msg","请填写用户信息！");
//			return resultMap;
//		}
		if (StringUtils.isEmpty(cpacha)) {
			resultMap.put("type","error");
			resultMap.put("msg","请填写验证码！");
			return resultMap;
		}
		if (StringUtils.isEmpty(user.getUsername())) {
			resultMap.put("type","error");
			resultMap.put("msg","请填写用户名！");
			return resultMap;
		}
		if (StringUtils.isEmpty(user.getPassword())) {
			resultMap.put("type","error");
			resultMap.put("msg","请填写密码！");
			return resultMap;
		}
		Object loginCpacha = session.getAttribute("loginCpacha");
		if(loginCpacha == null){
			resultMap.put("type", "error");
			resultMap.put("msg", "会话超时，请刷新页面！");
			return resultMap;
		}
		if(!cpacha.toUpperCase().equals(loginCpacha.toString().toUpperCase())) {
			resultMap.put("type", "error");
			resultMap.put("msg", "验证码错误！");
			return resultMap;
		}
		
		User findByUsername = userService.findByUsername(user.getUsername());
		if(findByUsername==null) {
			resultMap.put("type", "error");
			resultMap.put("msg", "该用户名不存在！");
			return resultMap;
		}
		if(!findByUsername.getPassword().equals(user.getPassword())) {
			resultMap.put("type", "error");
			resultMap.put("msg", "密码错误！");
			return resultMap;
		}
		session.setAttribute("admin", findByUsername);
		resultMap.put("type", "success");
		resultMap.put("msg", "登录成功！");
		return resultMap;
	}

	
	
	
	
	
	/**
	 * 生成验证码 设定本系统的验证码都用该函数生成
	 * 
	 * @param vcodeLen   验证码长度，非必传，默认值4
	 * @param width      验证码宽度，非必传，默认值100
	 * @param height     验证码高度，非必传，默认值30
	 * @param cpachaType 不同的生成位置使用该字符串参数作区分，默认值是loginCpacha，该参数必传
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = "/get_cpacha", method = RequestMethod.GET)
	public void generateCpacha(
			// name会作为请求URL中的参数，所修饰的变量在当前方法使用
			@RequestParam(name = "v1", required = false, defaultValue = "4") Integer vcodeLen,
			@RequestParam(name = "w", required = false, defaultValue = "100") Integer width,
			@RequestParam(name = "h", required = false, defaultValue = "30") Integer height,
			@RequestParam(name = "type", required = true, defaultValue = "loginCpacha") String cpachaType,
			HttpServletRequest request, HttpServletResponse response) {
		CpachaUtil cpachaUtil = new CpachaUtil(vcodeLen, width, height);
		String generatorVCode = cpachaUtil.generatorVCode();
		request.getSession().setAttribute(cpachaType, generatorVCode);
		BufferedImage generatorVCodeImage = cpachaUtil.generatorVCodeImage(generatorVCode, true);
		try {
			ImageIO.write(generatorVCodeImage, "gif", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
// 使用String返回视图字符串方式等价于使用ModelAndView的setViewName(视图字符串);后返回ModelAndView对象，
// ModelAndView好处在于可以更方便地设置值，而且ModelAndView对象是SpringMVC自动为我们注入的
// @RequestMapping(value="/index",method=RequestMethod.GET)
// public String index() {
// return "/system/index";
// }