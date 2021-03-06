package com.x.sampleproject.core.entity;

import com.x.base.core.project.Compilable;
import com.x.base.core.project.annotation.Module;
import com.x.base.core.project.annotation.ModuleCategory;
import com.x.base.core.project.annotation.ModuleType;

/**
 * 应用实体类工程信息
 * packageName 实体类包路径
 * @author sword
 */
@Module(type = ModuleType.ENTITY, category = ModuleCategory.CUSTOM, name = "示例项目实体类", packageName = "com.x.sampleproject.core.entity")
public class x_sampleproject_core_entity extends Compilable {

}
