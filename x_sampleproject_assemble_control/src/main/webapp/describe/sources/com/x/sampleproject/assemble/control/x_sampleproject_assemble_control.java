package com.x.sampleproject.assemble.control;

import com.x.base.core.project.Deployable;
import com.x.base.core.project.annotation.Module;
import com.x.base.core.project.annotation.ModuleCategory;
import com.x.base.core.project.annotation.ModuleType;

@Module(type = ModuleType.ASSEMBLE, category = ModuleCategory.CUSTOM, name = "自定义项目示例", packageName = "com.x.sampleproject.assemble.control", containerEntities = {
		"com.x.sampleproject.core.entity.SampleEntityClassName" }, storeJars = { "x_organization_core_entity",
				"x_organization_core_express" }, customJars = { "x_sampleproject_core_entity" })
public class x_sampleproject_assemble_control extends Deployable {
}
