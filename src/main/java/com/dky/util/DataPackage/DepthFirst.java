package com.dky.util.DataPackage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Stack;

import net.sf.json.JSONObject;

public class DepthFirst {

	public static void main(String[] args) {
		Map<String,Object> month=new HashMap<>();
		Map<String,Object> day=new HashMap<>();
		Map<String,Object> hour=new HashMap<>();
		List<String> hourvalues=new ArrayList<>();
		List<String> hourvalues2=new ArrayList<>();
		hourvalues.add("1");
		hourvalues.add("2");
		hourvalues.add("3");
		hourvalues.add("4");

		hourvalues2.add("1");
		hourvalues2.add("2");
		hourvalues2.add("3");
		hourvalues2.add("5");
		hour.put("hour", hourvalues);
		hour.put("hour2", hourvalues2);
		day.put("day", hour);
		month.put("month", day);
		depthFirst(month);


	}


	/*
	 * 	英文缩写为DFS即Depth First Search.其过程简要来说是对每一个可能的分支路径深入到不能再深入为止，而且每个节点只能访问一次。对于上面的例子来说深度优先遍历的结果就是：A,B,D,E,I,C,F,G,H.(假设先走子节点的的左侧)。
		深度优先遍历各个节点，需要使用到堆（Stack）这种数据结构。stack的特点是是先进后出。整个遍历过程如下：
		首先将A节点压入堆中，stack（A）;
		将A节点弹出，同时将A的子节点C，B压入堆中，此时B在堆的顶部，stack(B,C)；
		将B节点弹出，同时将B的子节点E，D压入堆中，此时D在堆的顶部，stack（D,E,C）；
		将D节点弹出，没有子节点压入,此时E在堆的顶部，stack（E，C）；
		将E节点弹出，同时将E的子节点I压入，stack（I,C）；
	 *
	 * */
	//深度优先遍历
	public static void depthFirst(Map<String, Object> parent) {
		Stack<Map<String, Object>> nodeStack = new Stack<Map<String, Object>>();
		nodeStack.add(parent);
		Map<String, Object> node = new HashMap<String, Object>();
		int count=1;
		while (!nodeStack.isEmpty()&nodeStack!=null) {
			node = nodeStack.pop();
			//获得节点的子节点，对于二叉树就是获得节点的左子结点和右子节点
			List<Map<String, Object>> children=new ArrayList<>();
			for (Object object:node.values()) {
				if(object.toString().contains("{")) {
					Map<String,Object> map=JsonToMap(object);

					children.add(map);
					System.out.println(children);
				}else {
					System.out.println(node);
					//System.out.println(object.toString());
				}


			}

			if (children != null && !children.isEmpty()) {
				for (Map child : children) {
					nodeStack.push(child);
				}
			}
//	        System.out.println(nodeStack);
		}

//	    System.out.println(nodeStack);
	}

	//深度优先遍历并返回最后一层map
	public static Map<String, Object> depthLastMap(Map<String, Object> parent) {
		Stack<Map<String, Object>> nodeStack = new Stack<Map<String, Object>>();
		nodeStack.add(parent);
		Map<String, Object> node = new HashMap<String, Object>();
		Map<String, Object> lastNode = new HashMap<String, Object>();
		int count=1;
		while (!nodeStack.isEmpty()&nodeStack!=null) {
			node = nodeStack.pop();
			//获得节点的子节点，对于二叉树就是获得节点的左子结点和右子节点
			List<Map<String, Object>> children=new ArrayList<>();
			for (Object object:node.values()) {
				if(object.toString().contains("{")) {
					Map<String,Object> map=JsonToMap(object);

					children.add(map);
					System.out.println(children);
				}else {
					lastNode=node;
					//System.out.println(object.toString());
				}


			}

			if (children != null && !children.isEmpty()) {
				for (Map child : children) {
					nodeStack.push(child);
				}
			}
//		        System.out.println(nodeStack);
		}

		return lastNode;
	}




	// 对象转map
	public static Map<String, Object> JsonToMap(Object jsonStr) {
		Map<String, Object> map = new HashMap<String, Object>();

		JSONObject jsonObject = JSONObject.fromObject(jsonStr);

		Iterator<String> keys = jsonObject.keys();// 定义迭代器

		String key = null;
		Object value = null;

		while (keys.hasNext()) {
			key = keys.next();
			value = jsonObject.get(key);

			map.put(key, value);
		}
//		System.out.println("map" + map);
		return map;
	}


}
