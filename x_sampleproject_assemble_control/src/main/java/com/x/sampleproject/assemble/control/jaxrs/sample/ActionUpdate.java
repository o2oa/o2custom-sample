package com.x.sampleproject.assemble.control.jaxrs.sample;


import javax.servlet.http.HttpServletRequest;

import com.x.base.core.project.exception.ExceptionEntityNotExist;
import org.apache.commons.lang3.StringUtils;

import com.google.gson.JsonElement;
import com.x.base.core.container.EntityManagerContainer;
import com.x.base.core.container.factory.EntityManagerContainerFactory;
import com.x.base.core.entity.JpaObject;
import com.x.base.core.entity.annotation.CheckPersistType;
import com.x.base.core.project.bean.WrapCopier;
import com.x.base.core.project.bean.WrapCopierFactory;
import com.x.base.core.project.http.ActionResult;
import com.x.base.core.project.http.EffectivePerson;
import com.x.base.core.project.jaxrs.WoId;
import com.x.base.core.project.logger.Logger;
import com.x.base.core.project.logger.LoggerFactory;
import com.x.sampleproject.core.entity.SampleEntityClassName;

/**
 * 示例数据信息更新服务
 * @author sword
 */
public class ActionUpdate extends BaseAction {

	private static  Logger logger = LoggerFactory.getLogger( ActionUpdate.class );

	protected ActionResult<Wo> execute( HttpServletRequest request, EffectivePerson effectivePerson, String id, JsonElement jsonElement ) throws Exception {
		ActionResult<Wo> result = new ActionResult<>();
		Wi wi = this.convertToWrapIn( jsonElement, Wi.class );
		if( StringUtils.isEmpty( wi.getName() )) {
			throw new ExceptionSampleEntityClassNameEmpty();
		}
		try (EntityManagerContainer emc = EntityManagerContainerFactory.instance().create()) {
			SampleEntityClassName sampleEntityClassName = emc.find( id, SampleEntityClassName.class );
			if( sampleEntityClassName == null ){
				throw new ExceptionEntityNotExist(id, SampleEntityClassName.class);
			}
			Wi.copier.copy(wi, sampleEntityClassName);
			//启动事务
			emc.beginTransaction( SampleEntityClassName.class );
			//校验对象
			emc.check( sampleEntityClassName, CheckPersistType.all );
			//提交事务
			emc.commit();

			Wo wo = new Wo(id);
			result.setData(wo);
		}
		return result;
	}

	/**
	 * 用于接受前端传入的对象型参数的帮助类
	 *
	 */
	public static class Wi extends SampleEntityClassName{

		public static final WrapCopier<Wi, SampleEntityClassName> copier = WrapCopierFactory.wi( Wi.class, SampleEntityClassName.class, null, JpaObject.FieldsUnmodify );

	}

	/**
	 * 用于输出响应内容的帮助类
	 *
	 */
	public static class Wo extends WoId {
		public Wo( String id ) {
			setId( id );
		}
	}
}
