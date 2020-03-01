package com.dky.util.DataPackage;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import com.dky.util.ReadProperties;
import org.apache.commons.collections.map.HashedMap;

public class DataPack {

	private Map<String, Object> TempMap = new HashedMap();

	public static void main(String[] args) {
		DataPack data = new DataPack();
		data.correlationpack();
	}

	public void correlationpack() {
		ReadProperties.readProperties("/dict.properties","compareData");

		Map<String, Object> rowMapdata = new HashedMap();
		Map<String, Object> rowCompareData = new HashedMap();// 作为存储比较数据的map

		String[] relation_type = { "温度", "降雨", "舒适度", "湿度" };

		List<List<String>> rowData = new ArrayList<>();
		String rowTimeFormat = "yyyymmdd";
		// 横向数据,封装时从第三位封装
		for (int i2 = 0; i2 < 24; i2++) {
			List<String> rowList = new ArrayList<>();
			rowList.add("57");
			rowList.add("57");
			if (i2 < 10) {
				rowList.add("2018-01-30-0" + i2);
			} else {
				rowList.add("2018-01-30-" + i2);
			}
			for (int i = 0; i < 24; i++) {
				rowList.add(i + 3, String.valueOf(i));
			}
			rowData.add(rowList);
		}

		for (List<String> data : rowData) {
			packMap(rowMapdata, 0, data.get(2).split("-"), data.subList(3, data.size()));
		}
		// System.out.println(rowMapdata);
		String area = rowData.get(0).get(0);
		String id = rowData.get(0).get(0);
		String areaname = "福州";
		String mdate = rowData.get(0).get(2);

		// 纵向数据
		String lineTimeFormat = "yyyymmddhh";
		List<List<String>> lineList = new ArrayList<>();
		Map<String, Object> linedata = new HashedMap();
		Map<String, Object> lineCompareData = new HashedMap();// 作为存储比较map的Data
		// for (int i2 = 29; i2 <= 30; i2++) {

		for (int i = 0; i < 20; i++) {
			List<String> insideList = new ArrayList<>();
			String date = "2018-01-30";
			if (i < 10) {
				date += "-0" + i;
			} else {
				date += "-" + i;
			}
			insideList.add(date);
			insideList.add(String.valueOf(i));
			lineList.add(insideList);

		}
		// }

		for (int i = 0; i < lineList.size(); i++) {
			packMap(linedata, 0, lineList.get(i).get(0).split("-"), lineList.get(i).get(1));

			// System.out.println(
			// packDateMap(lineList.get(i).get(0),lineList.get(i).get(1)));
		}
		// System.out.println(linedata);
		if (lineList.get(0).get(0).length() == rowData.get(0).get(2).length()) {
			System.out.println("时间长度相同");
			System.out.println(compareMap(rowMapdata, linedata));

		} else {
			System.out.println(lineList.get(0).get(0).length());
			System.out.println(rowData.get(0).get(2).length());
			System.out.println("时间长度不同");
		}

	}

	// map1放横向数据，map2放竖向数据，与map2做对比,返回修改后的map1数据
	@SuppressWarnings("unlikely-arg-type")
	public Map<String, Object> compareMap(Map<String, Object> map1, Map<String, Object> map2) {
		Map<String,Object> tempMap1=new HashedMap();
		// 第一层键值遍历
		Iterator<Entry<String, Object>> iter1 = map1.entrySet().iterator();

		while (iter1.hasNext()) {

			Map.Entry<String, Object> entry1 = iter1.next();
			// 判断第一层key是否存在
			if (map2.containsKey(entry1.getKey())) {

				// 判断{的原因是要判断数据是否为map
				if (entry1.getValue().toString().contains("{")) {
					Map<String, Object> map1Value = DepthFirst.JsonToMap(entry1.getValue());
					Iterator<Entry<String, Object>> iter2 = map1Value.entrySet().iterator();
					while (iter2.hasNext()) {
						// 第一层value的遍历
						Map.Entry<String, Object> entryTemp1 = iter2.next(); //此处容易出错
						// value层级的key遍历
						if (DepthFirst.JsonToMap(map2.get(entry1.getKey())).containsKey(entryTemp1.getKey())) {
							tempMap1.put(entry1.getKey(), compareMap(map1Value, DepthFirst.JsonToMap(map2.get(entry1.getKey()))));
						}
					}
				}else {
					if (map2.containsKey(entry1.getKey())) {
						//System.out.println(entry1.getKey());
						tempMap1.put(entry1.getKey(), entry1.getValue());
						//System.out.println(tempMap1);
					}

				}

			}
		}

		return tempMap1;

	}

	// 数据点为横向\竖向时均可转换
	public Map<String, Object> packMap(Map<String, Object> temp1, int index, String[] date, Object data) {
		// temp1传入的map，index传入的下标，date传入的时间数组，data传入的集合数据
		Map<String, Object> temp = new HashMap<>();

		if (index + 1 != date.length) {
			String key = date[index];
			String value = date[index + 1];

			if (!temp1.containsKey(key)) {
				temp1.put(key, packMap(temp, index + 1, date, data));
			} else if (temp1.containsKey(key)) {
				Map<String, Object> map = new HashMap<>();

				if (DepthFirst.JsonToMap(temp1.get(key)).containsKey(value)) {
					Map<String, Object> temp2 = new HashMap<>();
					temp2 = DepthFirst.JsonToMap(temp1.get(key));
					temp1.put(key, packMap(temp2, index + 1, date, data));

				} else {
					Map<String, Object> temp2 = new HashMap<>();
					Map<String, Object> temp3 = new HashMap<>();
					temp2 = DepthFirst.JsonToMap(temp1.get(key));

					temp1.put(key, packMap(temp2, index + 1, date, data));
				}
			}

		} else {
			temp1.put(date[index], data);
		}
		return temp1;
	}

	public Map<String, Object> packDateMap(String date, Object data) {

		Map<String, Object> temp = new HashedMap();
		if (date.indexOf("-") != -1) {
			String key = date.substring(0, date.indexOf("-"));
			String value = date.substring(date.indexOf("-") + 1, date.indexOf("-") + 3);
			temp.put(key, packDateMap(date.substring(date.indexOf("-") + 1), data));

		} else {
			temp.put(date, data);
		}

		return temp;
	}

	// 判断传入的时间到哪一级别
	public int layerNumber(String format) {
		String[] temp = { "yyyy", "mm", "dd", "hh", "mi", "ss" };
		if (format.contains("ss")) {
			return 6;
		} else if (format.contains("mi")) {
			return 5;
		} else if (format.contains("hh")) {
			return 4;
		} else if (format.contains("dd")) {
			return 3;
		} else if (format.contains("mm")) {
			return 2;
		} else if (format.contains("yyyy")) {
			return 1;
		}

		return 0;
	}

}
