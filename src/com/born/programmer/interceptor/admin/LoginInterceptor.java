package com.born.programmer.interceptor.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import net.sf.json.JSONObject;
/**
 * 后台登录拦截器
  * @author 15188
  *
 */
public class LoginInterceptor implements HandlerInterceptor {

	@Override
	public void afterCompletion(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1, Object arg2, ModelAndView arg3)
			throws Exception {
		// TODO Auto-generated method stub

	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object arg2) throws Exception {
		// TODO Auto-generated method stub
		String requestURI = request.getRequestURI();
		System.out.println("链接"+requestURI+"进入拦截器！");
		Object admin = request.getSession().getAttribute("admin");
		//未登录或登录失效时，重定向到登录
		if(admin==null) {
			//当请求头中有 X-Requested-with属性且值为XMLHttpRequest时，说明是ajax请求
			String header = request.getHeader("X-Requested-with");
			//如果是ajax请求，返回对应格式的错误信息
			if("XMLHttpRequest".equals(header)) {
				
				Map<String,String> result=new HashMap<>();
				result.put("type","error");
				result.put("msg","登录会话超时或还未登录，请重新登录！");
				response.getWriter().write(JSONObject.fromObject(result).toString()); 
			}
			//如果只是普通请求，直接重定向到登录页面
			response.sendRedirect(request.getServletContext().getContextPath()+"/system/login");
			return false;
		}
		return true;
	}

}
