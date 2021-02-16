package com.zc.route.console;

import java.util.ArrayList;
import java.util.List;

/**
 * 打印表格工具类，可以以表格的形式打印任何实现了FieldHandler接口的实体类及String数组。
 * 如果输出内容包含中文则仅在部分字体下有效，其他字体下排版会相对混乱，推荐使用YaHei Consolas Hybrid字体。
 *
 * @author 田义锋Spock
 * @date 2019年4月26日
 * @version v1.0
 */
public class ConsoleTables {

	/**
	 * 私有化构造函数
	 */
	private ConsoleTables() {
	}

	/**
	 * 打印单行无标题的表格
	 * 
	 * @param strArr  一行内容
	 * @param columns 表格列数
	 * @param autoNum 是否自动编号
	 */
	public static void printTable(String[] strArr, int columns, boolean autoNum) {
		if (strArr == null) {
			return;
		}
		List<String[]> list = new ArrayList<String[]>();
		list.add(strArr);
		ConsoleTable consoleTable = new ConsoleTable(list, null, columns, autoNum);
		consoleTable.print();
	}

	/**
	 * 打印单行有标题的表格
	 * 
	 * @param strArr  一行内容
	 * @param title   表格标题
	 * @param columns 表格列数
	 * @param autoNum 是否自动编号
	 */
	public static void printTable(String[] strArr, String[] title, int columns, boolean autoNum) {
		if (strArr == null) {
			return;
		}
		List<String[]> list = new ArrayList<String[]>();
		list.add(strArr);
		ConsoleTable consoleTable = new ConsoleTable(list, title, columns, autoNum);
		consoleTable.print();
	}

	/**
	 * 打印无标题的表格
	 * 
	 * @param list    表格内容，每个String[]占一行;
	 * @param columns 表格列数
	 * @param autoNum 是否自动编号
	 */
	public static void printTable(List<String[]> list, int columns, boolean autoNum) {
		if (list == null) {
			return;
		}
		ConsoleTable consoleTable = new ConsoleTable(list, null, columns, autoNum);
		consoleTable.print();
	}

	/**
	 * 打印有标题的表格
	 * 
	 * @param list    表格内容，每个String[]占一行;
	 * @param title   表格标题
	 * @param columns 表格列数
	 * @param autoNum 是否自动编号
	 */
	public static void printTable(List<String[]> list, String[] title, int columns, boolean autoNum) {
		if (list == null) {
			return;
		}
		ConsoleTable consoleTable = new ConsoleTable(list, title, columns, autoNum);
		consoleTable.print();
	}

	/**
	 * 打印单行无标题表格
	 * 
	 * @param <E>     实体类类型，需实现FieldHandler接口
	 * @param e       实体类对象，占一行;
	 * @param columns 表格列数
	 * @param autoNum 是否自动编号
	 */
	public static <E extends FieldHandler> void printObjectTable(E e, int columns, boolean autoNum) {
		if (e == null) {
			return;
		}
		List<String[]> strList = new ArrayList<String[]>();
		strList.add(e.getFieldStringArr());
		ConsoleTable consoleTable = new ConsoleTable(strList, null, columns, autoNum);
		consoleTable.print();
	}

	/**
	 * 打印单行有标题的表格
	 * 
	 * @param <E>     实体类类型，需实现FieldHandler接口
	 * @param e       实体类对象，占一行;
	 * @param title   表格标题
	 * @param columns 表格列数
	 * @param autoNum 是否自动编号
	 */
	public static <E extends FieldHandler> void printObjectTable(E e, String[] title, int columns, boolean autoNum) {
		if (e == null) {
			return;
		}
		List<String[]> strList = new ArrayList<String[]>();
		strList.add(e.getFieldStringArr());
		ConsoleTable consoleTable = new ConsoleTable(strList, title, columns, autoNum);
		consoleTable.print();
	}

	/**
	 * 打印无标题表格
	 * 
	 * @param <E>     实体类类型，需实现FieldHandler接口
	 * @param list    表格内容，每个E占一行;
	 * @param columns 表格列数
	 * @param autoNum 是否自动编号
	 */
	public static <E extends FieldHandler> void printObjectTable(List<E> list, int columns, boolean autoNum) {
		if (list == null) {
			return;
		}
		List<String[]> strList = new ArrayList<String[]>();
		list.forEach(e -> {
			strList.add(e.getFieldStringArr());
		});
		ConsoleTable consoleTable = new ConsoleTable(strList, null, columns, autoNum);
		consoleTable.print();
	}

	/**
	 * 打印有标题的表格
	 * 
	 * @param <E>     实体类类型，需实现FieldHandler接口
	 * @param list    表格内容，每个E占一行;
	 * @param title   表格标题
	 * @param columns 表格列数
	 * @param autoNum 是否自动编号
	 */
	public static <E extends FieldHandler> void printObjectTable(List<E> list, String[] title, int columns,
			boolean autoNum) {
		if (list == null) {
			return;
		}
		List<String[]> strList = new ArrayList<String[]>();
		list.forEach(e -> {
			strList.add(e.getFieldStringArr());
		});
		ConsoleTable consoleTable = new ConsoleTable(strList, title, columns, autoNum);
		consoleTable.print();
	}

}
