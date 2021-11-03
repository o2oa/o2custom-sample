package com.x.sampleproject.assemble.control.jaxrs.sample;

import javax.servlet.http.HttpServletRequest;

import com.x.base.core.container.EntityManagerContainer;
import com.x.base.core.container.factory.EntityManagerContainerFactory;
import com.x.base.core.entity.annotation.CheckRemoveType;
import com.x.base.core.project.exception.ExceptionEntityNotExist;
import com.x.base.core.project.http.ActionResult;
import com.x.base.core.project.http.EffectivePerson;
import com.x.base.core.project.jaxrs.WoId;
import com.x.base.core.project.logger.Logger;
import com.x.base.core.project.logger.LoggerFactory;
import com.x.sampleproject.core.entity.SampleEntityClassName;

/**
 * 信息数据删除服务
 * @author sword
 */
public class ActionDelete extends BaseAction {

	private static  Logger logger = LoggerFactory.getLogger( ActionDelete.class );

	protected ActionResult<Wo> execute( HttpServletRequest request, EffectivePerson effectivePerson, String id ) throws Exception {
		ActionResult<Wo> result = new ActionResult<>();

		try (EntityManagerContainer emc = EntityManagerContainerFactory.instance().create()) {
			SampleEntityClassName sampleEntityClassName = emc.find( id, SampleEntityClassName.class );
			if( sampleEntityClassName == null ){
				throw new ExceptionEntityNotExist(id, SampleEntityClassName.class);
			}
			//启动事务
			emc.beginTransaction( SampleEntityClassName.class );
			//删除对象
			emc.remove( sampleEntityClassName, CheckRemoveType.all );
			//提交事务
			emc.commit();
			result.setData( new Wo( id ));
		}
		return result;
	}

	public static class Wo extends WoId {
		public Wo( String id ) {
			setId( id );
		}
	}
}
