package com.maizi.tree;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Liuys
 * @version V1.0
 * @Package com.maizi.tree
 * @date 2019/12/23 11:39
 */
public class TreeTest {
    public static void main(String[] args){
        String str = "["
                + "{id: \"001\", pid: \"root\",count: \"3\"},"
                + "{id: \"002\", pid: \"001\",count: \"1\"},"
                + "{id: \"003\", pid: \"002\",count: \"2\"},"
                + "{id: \"004\", pid: \"003\",count: \"4\"},"
                + "{id: \"005\", pid: \"001\",count: \"3\"},"
                + "{id: \"006\", pid: \"002\",count: \"2\"},"
                + "{id: \"007\", pid: \"005\",count: \"100\"}"
                + "]";
        JSONArray arr = JSON.parseArray(str);
        Map<String, TreeNode<JSONObject>> map = new HashMap<>();
        TreeNode _root = null;
        for (int i = 0; i < arr.size(); i++) {
            JSONObject jo = arr.getJSONObject(i);
            TreeNode<JSONObject> node = new TreeNode<>(jo);
            String id = jo.getString("id");
            String pid = jo.getString("pid");
            String count = jo.getString("count");
            if (map.containsKey(pid)) {
                TreeNode<JSONObject> parentNode = map.get(pid);
                parentNode.appendChildNode(node);
            }
            map.put(id, node);
            if (_root == null) {
                if ("root".equals(pid)) {
                    _root = node;
                }
            }
        }
        map.clear();
        Tree<JSONObject> tree = new Tree(_root);
        tree.traversal((TreeNode<JSONObject> node) -> {
            count(node);
            System.out.println(node.getBindData().get("id") + ":" + node.getSumTotal());
            //System.out.println(node.getSumTotal());
            return false;

        }, Tree.TraversalType.RECURSIVE);
    }
    private static TreeNode count(TreeNode<JSONObject> root){
        int total = Integer.parseInt((String) root.getBindData().get("count"));
        if(root.getChildren().size() < 1){
            root.setSumTotal(total);
            return root;
        }
        int sum = total;
        for(TreeNode<JSONObject> child : root.getChildren()){
            TreeNode<JSONObject> tmp = count(child);
            sum += tmp.getSumTotal();
        }
        root.setSumTotal(sum);
        return root;
    }

}
