/**
 * author: loserStar
 * date: 2019年4月8日下午5:41:21
 * email:362527240@qq.com
 * github:https://github.com/xinxin321198
 * remarks:
 */
package com.loserstar.viewResolver;

import java.io.File;
import java.util.Locale;

import org.springframework.web.servlet.view.InternalResourceView;

/**
 * author: loserStar
 * date: 2019年4月8日下午5:41:21
 * remarks: 自定义视图解析
 */
public class LoserStarViewResolver extends InternalResourceView{
	@Override
    public boolean checkResource(Locale locale) throws Exception {
        File file = new File(this.getServletContext().getRealPath("/") + getUrl());
        return file.exists();// 判断该页面是否存在
    }
}
