package com.dky.util;

import com.dky.entity.Dbarea;
import com.dky.entity.vo.TreeDataTemp;

import java.util.ArrayList;
import java.util.List;

/**
 * 根据结果集生成不同树状菜单的工具类
 * @author YangSL
 */
public class TreeData {
    /**
     * 全部节点都生成树状菜单
     * @param list 数据库的结果集
     * @param parentId 父节点id
     * @return 返回树状菜单结果集
     */
    public static List<TreeDataTemp> createTreeData(List<Dbarea> list,String parentId){
        List<TreeDataTemp> tempList = new ArrayList<>();
        for (Dbarea dbarea : list) {
            if (dbarea.getParentId().equals(parentId)) {
                String nodeNum = dbarea.getNodeNum();
                String last1 = nodeNum.substring(nodeNum.length()-2,nodeNum.length());
                String last2 = nodeNum.substring(nodeNum.length()-4,nodeNum.length()-2);
                TreeDataTemp treeDataTemp = new TreeDataTemp();
                treeDataTemp.setArea(dbarea.getArea());
                treeDataTemp.setName(dbarea.getDname());
                if(last1.equals("00")){
                    if (!last2.equals("00")){
                        treeDataTemp.setSpread(true);//这个地方本来是false，2018-07-26改成了true
                    } else {
                        treeDataTemp.setSpread(true);
                    }
                } else {
                    treeDataTemp.setSpread(true);//这个地方本来是false，2018-07-26改成了true
                }
                treeDataTemp.setChildren(createTreeData(list,treeDataTemp.getArea().toString()));
                tempList.add(treeDataTemp);
            }
        }
        if (tempList.size()==0){
            return null;
        }
        return tempList;
    }

    /**
     * 生成到省级截止的树状菜单
     * @param list 数据库的结果集
     * @param parentId 父节点id
     * @return 返回树状菜单结果集
     */
    public static List<TreeDataTemp> createProvinceTreeData(List<Dbarea> list,String parentId){
        List<TreeDataTemp> tempList = new ArrayList<>();
        for (Dbarea dbarea : list) {
            if (dbarea.getParentId().equals(parentId)) {
                String nodeNum = dbarea.getNodeNum();
                String thirdNode = nodeNum.substring(6,8);
                if (!thirdNode.equals("00")){
                    return null;
                }
                TreeDataTemp treeDataTemp = new TreeDataTemp();
                treeDataTemp.setArea(dbarea.getArea());
                treeDataTemp.setName(dbarea.getDname());
                treeDataTemp.setSpread(true);
                treeDataTemp.setChildren(createProvinceTreeData(list,treeDataTemp.getArea().toString()));
                tempList.add(treeDataTemp);
            }
        }
        if (tempList.size()==0){
            return null;
        }
        return tempList;
    }



}
