package com.zc.route.console;

import java.util.Arrays;
import java.util.List;

/**
 * 打印表格类，可以以表格的形式打印String数组。
 * 如果输出内容包含中文则仅在部分字体下有效，其他字体下排版会相对混乱，推荐使用YaHeiConsolasHybrid字体。
 * 本类所有方法都是protected，不可外部使用，推荐使用ConsoleTables类的静态方法打印内容
 * 
 * @author 田义锋Spock
 * @date 2019年4月26日
 * @version v1.0
 */
public class ConsoleTable {

	/**
	 * 表格标题
	 */
	private String[] title;

	/**
	 * 表格内容集合
	 */
	private List<String[]> list;

	/**
	 * 表格列数
	 */
	private Integer columns;

	/**
	 * 表格每列最大宽度
	 */
	private Integer[] fieldMaxLengthArr;

	/**
	 * 是否自动编号
	 */
	private boolean autoNum;

	/**
	 * 当前编号
	 */
	private Integer currentNum;

	/**
	 * protected构造函数
	 * 
	 * @param list    表格内容集合
	 * @param title   表格标题
	 * @param columns 表格列数
	 * @param autoNum 是否自动编号
	 */
	protected ConsoleTable(List<String[]> list, String[] title, Integer columns, boolean autoNum) {
		this.list = list;
		this.title = title;
		this.autoNum = autoNum;
		this.columns = columns;
		this.currentNum = 1;
		init();
	}

	/**
	 * 初始化
	 */
	private void init() {
		fieldMaxLengthArr = new Integer[columns];
		for (int i = 0; i < columns; i++) {
			fieldMaxLengthArr[i] = 0;
			if (list != null) {
				for (int j = 0; j < list.size(); j++) {
					if (list.get(j) != null && list.get(j)[i] != null) {
						int fieldLength = calculStrSpace(list.get(j)[i]);
						fieldMaxLengthArr[i] = fieldLength > fieldMaxLengthArr[i] ? fieldLength : fieldMaxLengthArr[i];
					}
				}
			}
			if (title != null) {
				if (title[i] != null) {
					int titleLength = calculStrSpace(title[i]);
					fieldMaxLengthArr[i] = titleLength > fieldMaxLengthArr[i] ? titleLength : fieldMaxLengthArr[i];
				}
			}
			fieldMaxLengthArr[i] += 4;
		}
		if (autoNum) {
			fieldMaxLengthArr = Arrays.copyOf(fieldMaxLengthArr, fieldMaxLengthArr.length + 1);
			for (int i = fieldMaxLengthArr.length - 1; i > 0; i--) {
				fieldMaxLengthArr[i] = fieldMaxLengthArr[i - 1];
			}
			fieldMaxLengthArr[0] = 8;
			columns += 1;
		}
	}

	/**
	 * 打印表格
	 */
	protected void print() {
		if (title != null) {
			System.out.println(getTitleLine());
			System.out.println(getTitleString());
		}
		System.out.println(getTitleLine());
		list.forEach(strArr -> {
			System.out.println(getRowString(strArr));
		});
		System.out.println(getTitleLine());
		currentNum = 1;
	}

	/**
	 * 获取表头横线
	 * 
	 * @return 表头横线字符串
	 */
	protected StringBuilder getTitleLine() {
		StringBuilder str = new StringBuilder();
		for (int i = 0; i < columns; i++) {
			int maxLength = this.fieldMaxLengthArr[i];
			str.append("+");
			for (int j = 0; j < maxLength; j++) {
				str.append("-");
			}
		}
		str.append("+");
		return str;
	}

	/**
	 * 获取表格标题
	 * 
	 * @return 表格标题字符串
	 */
	protected StringBuilder getTitleString() {
		if (title == null) {
			return null;
		}
		if (autoNum) {
			title = Arrays.copyOf(title, title.length + 1);
			for (int i = title.length - 1; i > 0; i--) {
				title[i] = title[i - 1];
			}
			title[0] = "序号";
		}
		StringBuilder str = new StringBuilder("|");
		for (int i = 0; i < columns; i++) {
			int maxLength = this.fieldMaxLengthArr[i];
			int length = calculStrSpace(title[i]);
			for (int j = 0; j < maxLength; j++) {
				if (j < (maxLength - length) / 2) {
					str.append((char) 32);
				} else if (j == (maxLength - length) / 2) {
					if (title[i] != null) {
						str.append(title[i]);
					} else {
						str.append("    ");
					}
				} else if (j >= (maxLength + length) / 2) {
					str.append((char) 32);
				}
			}
			str.append("|");
		}
		return str;
	}

	/**
	 * 根据一个String数组生成表格一行的内容
	 * 
	 * @param strArr 行数组
	 * @return 表格一行内容的字符串
	 */
	protected StringBuilder getRowString(String[] strArr) {
		int[] fieldLengthArr = new int[columns];
		if (autoNum) {
			strArr = Arrays.copyOf(strArr, columns + 1);
			fieldLengthArr = Arrays.copyOf(fieldLengthArr, columns + 1);
			for (int i = columns - 1; i > 0; i--) {
				strArr[i] = strArr[i - 1];
				fieldLengthArr[i] = fieldLengthArr[i - 1];
			}
			strArr[0] = String.valueOf(this.currentNum);
			fieldLengthArr[0] = calculStrSpace(String.valueOf(this.currentNum));
			currentNum++;
		}
		for (int i = 0; i < columns; i++) {
			fieldLengthArr[i] = calculStrSpace(strArr[i]);
		}
		StringBuilder str = new StringBuilder("|");
		for (int i = 0; i < columns; i++) {
			int maxLength = this.fieldMaxLengthArr[i];
			int length = fieldLengthArr[i];
			for (int j = 0; j < maxLength; j++) {
				if (j < (maxLength - length) / 2) {
					str.append((char) 32);
				} else if (j == (maxLength - length) / 2) {
					if (strArr[i] == null) {
						str.append("    ");
					} else if (strArr[i].length() == 0) {
						str.append((char) 32);
					} else {
						str.append(strArr[i]);
					}
				} else if (j >= (maxLength + length) / 2) {
					str.append((char) 32);
				}
			}
			str.append("|");
		}
		return str;
	}

	/**
	 * 计算字符串输出时占用长度，该长度表示的是字串符在输出时占用的长度，一个汉字及汉文标点占两个单位，其他占一个单位
	 * 
	 * @param str 字符串
	 * @return 占用长度
	 */
	public static int calculStrSpace(String str) {
		if (str == null) {
			return 4;
		}
		int count = 0;
		for (int i = 0; i < str.length(); i++) {
			char c = str.charAt(i);
			Character.UnicodeBlock ub = Character.UnicodeBlock.of(c);
			if (ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS
					|| ub == Character.UnicodeBlock.CJK_COMPATIBILITY_IDEOGRAPHS
					|| ub == Character.UnicodeBlock.CJK_UNIFIED_IDEOGRAPHS_EXTENSION_A
					|| ub == Character.UnicodeBlock.GENERAL_PUNCTUATION
					|| ub == Character.UnicodeBlock.CJK_SYMBOLS_AND_PUNCTUATION
					|| ub == Character.UnicodeBlock.HALFWIDTH_AND_FULLWIDTH_FORMS) {
				count += 2;
			} else {
				count += 1;
			}
		}
		return count;
	}

}
