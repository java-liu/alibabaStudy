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
                + "{id: \"001\", pid: \"root\"},"
                + "{id: \"002\", pid: \"001\"},"
                + "{id: \"003\", pid: \"002\"},"
                + "{id: \"004\", pid: \"003\"},"
                + "{id: \"005\", pid: \"001\"},"
                + "{id: \"006\", pid: \"002\"},"
                + "{id: \"007\", pid: \"005\"}"
                + "]";
        JSONArray arr = JSON.parseArray(str);
        Map<String, TreeNode<JSONObject>> map = new HashMap<>();
        TreeNode _root = null;
        for (int i = 0; i < arr.size(); i++) {
            JSONObject jo = arr.getJSONObject(i);
            TreeNode<JSONObject> node = new TreeNode<>(jo);
            String id = jo.getString("id");
            String pid = jo.getString("pid");
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

            System.out.println(node.getBindData().get("id"));
            return false;

        }, Tree.TraversalType.LOOP);
    }

}
