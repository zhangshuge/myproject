package com.zc.route.console;

/**
 * 使用ConsoleTables打印实体类表格时，实体类需实现本接口
 * 
 * @author 田义锋Spock
 * @date 2019年4月26日
 * @version v1.0
 */
public interface FieldHandler {

	/**
	 * 获取实体类各个属性转成字符串的数组
	 * 
	 * @return 字符串的数组
	 */
	String[] getFieldStringArr();

}
