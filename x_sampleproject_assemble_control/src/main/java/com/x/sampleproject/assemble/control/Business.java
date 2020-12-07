package com.x.sampleproject.assemble.control;

import com.google.gson.JsonObject;
import com.x.base.core.container.EntityManagerContainer;
import com.x.base.core.project.config.Config;
import com.x.organization.core.express.Organization;
import com.x.sampleproject.assemble.control.factory.SampleEntityClassNameFactory;
import org.apache.commons.lang3.StringUtils;

public class Business {

	//自定义应用配置文件名称，根据实际配置修改，文件要求为json格式
	private final static String CUSTOM_CONFIG_NAME = "o2oa_custom";

	private EntityManagerContainer emc;

	public Business(EntityManagerContainer emc) throws Exception {
		this.emc = emc;
	}

	public EntityManagerContainer entityManagerContainer() {
		return this.emc;
	}

	//读取自定义应用配置
	public static Object readConfig(String property) throws Exception{
		if(StringUtils.isNotBlank(property)) {
			JsonObject jsonObject = Config.customConfig(CUSTOM_CONFIG_NAME);
			if (jsonObject!=null){
				if(jsonObject.has(property)){
					//返回类型视实际配置修改
					return jsonObject.get(property).getAsString();
				}
			}
		}
		return null;
	}

	//组织架构管理相关的工厂服务类
	private Organization organization;

	//示例数据表工厂服务类
	private SampleEntityClassNameFactory sampleEntityClassNameFactory;


	public SampleEntityClassNameFactory sampleEntityClassNameFactory() throws Exception {
		if (null == this.sampleEntityClassNameFactory) {
			this.sampleEntityClassNameFactory = new SampleEntityClassNameFactory( this );
		}
		return sampleEntityClassNameFactory;
	}

	public Organization organization() throws Exception {
		if (null == this.organization) {
			this.organization = new Organization(ThisApplication.context());
		}
		return organization;
	}

}
