package cosmos.cms.common.tags;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.jsp.JspException;

import org.apache.log4j.Logger;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.tags.SecureTag;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import cosmos.cms.common.shiro.SysUserRealm;
import cosmos.cms.common.web.model.ResourcesModel;

@SuppressWarnings("serial")
public class MenuResourcesTag extends SecureTag {

	private Logger log = Logger.getLogger(getClass());

	private SysUserRealm sysUserRealm;

	private Subject subject;

	@Override
	public int onDoStartTag() throws JspException {
		// 获取到SysUserRealm对象
		//TODO 调查注入失败原因
		initMyRealm();
		PrincipalCollection principals = subject.getPrincipals();
		try {
			Map<Object, List<Object>> resourcesCacheInfo = sysUserRealm.getResourcesCacheInfo(principals);
			/*
			 * <!-- left menu starts -->
				<div class="col-sm-2 col-lg-2">
					<div class="sidebar-nav">
						<div class="nav-canvas">
							<div class="nav-sm nav nav-stacked"></div>
							<ul class="nav nav-pills nav-stacked main-menu">
								<li class="nav-header">Main</li>
								<li><a class="ajax-link" href="index.html"><i class="glyphicon glyphicon-home"></i><span>Dashboard</span></a></li>
								<li><a class="ajax-link" href="ui.html"><i class="glyphicon glyphicon-eye-open"></i><span>UI Features</span></a></li>
								<li><a class="ajax-link" href="form.html"><i class="glyphicon glyphicon-edit"></i><span>Forms</span></a></li>
								<li><a class="ajax-link" href="chart.html"><i class="glyphicon glyphicon-list-alt"></i><span>Charts</span></a></li>
								<li><a class="ajax-link" href="typography.html"><i class="glyphicon glyphicon-font"></i><span>Typography</span></a></li>
								<li><a class="ajax-link" href="gallery.html"><i class="glyphicon glyphicon-picture"></i><span>Gallery</span></a></li>
								<li class="nav-header hidden-md">Sample Section</li>
								<li><a class="ajax-link" href="table.html"><i class="glyphicon glyphicon-align-justify"></i><span>Tables</span></a></li>
								<li class="accordion">
									<a href="#"><i class="glyphicon glyphicon-plus"></i><span>Accordion Menu</span></a>
									<ul class="nav nav-pills nav-stacked">
										<li><a href="#">Child Menu 1</a></li>
										<li><a href="#">Child Menu 2</a></li>
									</ul>
								</li>
								<li><a class="ajax-link" href="calendar.html"><i class="glyphicon glyphicon-calendar"></i><span>Calendar</span></a></li>
								<li><a class="ajax-link" href="grid.html"><i class="glyphicon glyphicon-th"></i><span>Grid</span></a></li>
								<li><a href="tour.html"><i class="glyphicon glyphicon-globe"></i><span> Tour</span></a></li>
								<li><a class="ajax-link" href="icon.html"><i class="glyphicon glyphicon-star"></i><span>Icons</span></a></li>
								<li><a href="error.html"><i class="glyphicon glyphicon-ban-circle"></i><span>Error Page</span></a></li>
								<li><a href="login.html"><i class="glyphicon glyphicon-lock"></i><span>Login Page</span></a></li>
							</ul>
							<label id="for-is-ajax" for="is-ajax"><input id="is-ajax" type="checkbox">Ajax on menu</label>
						</div>
					</div>
				</div>
				<!--/span-->
				<!-- left menu ends -->
			 */
			
			
			
			String menuHTML = compositeMenu(resourcesCacheInfo);
			System.out.println("菜单html：" + menuHTML);
			this.pageContext.getOut().write(menuHTML);
		} catch (Exception e) {
			e.printStackTrace();
			log.error("tag中获取资源数据出现错误了");
		}
		return EVAL_PAGE;
	}

	private String compositeMenu(Map<Object, List<Object>> resourcesCacheInfo) {
		//TODO 需要进行排序处理
		StringBuffer menuHTML = new StringBuffer();
		menuHTML.append("<div class=\"col-sm-2 col-lg-2\">");
		menuHTML.append("	<div class=\"sidebar-nav\">");
		menuHTML.append("		<div class=\"nav-canvas\">");
		menuHTML.append("			<div class=\"nav-sm nav nav-stacked\"></div>");
		menuHTML.append("			<ul class=\"nav nav-pills nav-stacked main-menu\">");
		
		menuHTML.append("<li class=\"nav-header\">Main</li>");
		if(null != resourcesCacheInfo) {
			Set<Object> keySet = resourcesCacheInfo.keySet();
			for(Iterator<Object> iter = keySet.iterator(); iter.hasNext(); ) {
				Object parentObj = iter.next();
				if(parentObj instanceof ResourcesModel) {
					ResourcesModel parent = (ResourcesModel) parentObj;
					String sysResourcesPermission = parent.getSysResourcesPermission();
					if(subject != null && subject.isPermitted(sysResourcesPermission)) {
						menuHTML.append("<li class=\"accordion\">");
						menuHTML.append("<a href=\"#\"><i class=\"glyphicon ")
						.append(parent.getSysResourcesIcon())
						.append("\"></i><span>")
						.append(parent.getSysResourcesName())
						.append("</span></a>");
						menuHTML.append("<ul class=\"nav nav-pills nav-stacked\">");
						List<Object> childObjList = resourcesCacheInfo.get(parentObj);
						for(Object childObj : childObjList) {
							if(childObj instanceof ResourcesModel) {
								ResourcesModel child = (ResourcesModel) childObj;
								menuHTML.append("<li><a href=\"#\">").append(child.getSysResourcesName()).append("</a></li>");
							}
						}
						menuHTML.append("</ul>");
						menuHTML.append("</li>");
					}
				}
			}
		}

		menuHTML.append("			</ul>");
		menuHTML.append("		</div>");
		menuHTML.append("	</div>");
		menuHTML.append("</div>");
		return menuHTML.toString();
	}

	private void initMyRealm() {
		WebApplicationContext requiredWebApplicationContext =
		WebApplicationContextUtils.getWebApplicationContext(this.pageContext.getServletContext());
		this.sysUserRealm = requiredWebApplicationContext.getBean("sysUserRealm", SysUserRealm.class);
		this.subject = getSubject();
	}

}
