package cn.webro.util;

import org.nutz.ioc.loader.annotation.IocBean;
import org.nutz.mvc.ActionContext;
import org.nutz.mvc.ActionFilter;
import org.nutz.mvc.View;
import org.nutz.mvc.view.ServerRedirectView;

import javax.servlet.http.HttpSession;

@IocBean
public class CheckUsersaaaaaa implements ActionFilter {

	@Override
	public View match(ActionContext actionContext) {
		HttpSession session = actionContext.getRequest().getSession();
		if (session.getAttribute("users") != null
				|| session.getAttribute("shanghu") != null) {
			return null;// 执行方法
		}
		// 验证不通过时跳转到别的页面！
		return new ServerRedirectView("/index.jsp");
	}

}
