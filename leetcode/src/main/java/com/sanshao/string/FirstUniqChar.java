package com.sanshao.string;



import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

/**
 * @Description: 字符串中的第一个唯一字符
 * 给定一个字符串，找到它的第一个不重复的字符，并返回它的索引。如果不存在，则返回 -1。
 *
 *  
 *
 * 示例：
 *
 * s = "leetcode"
 * 返回 0
 *
 * s = "loveleetcode"
 * 返回 2
 *  
 *
 * 提示：你可以假定该字符串只包含小写字母。
 *
 * 链接：https://leetcode-cn.com/leetbook/read/top-interview-questions-easy/xn5z8r/
 * @Author: Liuys
 * @CreateDate: 2021/2/26 14:08
 * @Version: 1.0
 */
public class FirstUniqChar {
    /***
     * HashMap法，记录出现的次数
     * @param s
     * @return
     */
    public static int firstUniqChar1(String s){
        char[] chars = s.toCharArray();
        int len = s.length();
        Map<String, Integer> map = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            String str = s.substring(i, i+1);
            if(map.containsKey(str)){
                map.put(str,map.get(str) + 1);
                continue;
            }
            map.put(str,1);
        }
        for(int i = 0; i < s.length(); i++){
            String ch = s.substring(i,i+1);
            if(map.get(ch) == 1){
                return i;
            }
        }
        return -1;
    }

    public static int firstUniqChar2(String s){
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        for (char c : chars) {
            if(map.containsKey(c)){
                map.put(c,map.get(c) + 1);
                continue;
            }
            map.put(c,1);
        }

        for(int i = 0; i < chars.length; i++){
            if(map.get(chars[i]) == 1){
                return i;
            }
        }
        return -1;
    }

    /***
     * 把HashMap换成数组
     * @param s
     */
    public static int firstUniqChar3(String s){
        int[] array = new int[26];
        int n = s.length();
        for (int i = 0; i < n; i++) {
            array[s.charAt(i) - 'a']++;
        }
        for(int i = 0; i < n; i++){
            if(array[s.charAt(i) - 'a'] == 1){
                return i;
            }
        }
        return -1;

    }

    /***
     * 使用队列 offer，add 区别：
     *
     * 一些队列有大小限制，因此如果想在一个满的队列中加入一个新项，多出的项就会被拒绝。
     *
     * 这时新的 offer 方法就可以起作用了。它不是对调用 add() 方法抛出一个 unchecked 异常，而只是得到由 offer() 返回的 false。
     *
     * poll，remove 区别：
     *
     * remove() 和 poll() 方法都是从队列中删除第一个元素。remove() 的行为与 Collection 接口的版本相似， 但是新的 poll() 方法在用空集合调用时不是抛出异常，只是返回 null。因此新的方法更适合容易出现异常条件的情况。
     *
     * peek，element区别：
     *
     * element() 和 peek() 用于在队列的头部查询元素。与 remove() 方法类似，在队列为空时， element() 抛出一个异常，而 peek() 返回 null。
     * @param s
     * @return
     */
    public static int firstUniqChar4(String s){
        Map<Character, Integer> map = new HashMap<>();
        Queue<Pair> queue = new LinkedList<>();
        int n = s.length();
        for(int i = 0; i< n; i++){
            char ch = s.charAt(i);
            if(!map.containsKey(ch)){
                map.put(ch,i);
                queue.offer(new Pair(ch,i));
            }else{
                map.put(ch, -1);
                while(!queue.isEmpty() && map.get(queue.peek().ch) == -1){
                    queue.poll();
                }
            }
        }
        return queue.isEmpty() ? -1 :queue.peek().pos;
    }
    static class Pair{
        char ch;
        int pos;
        Pair(char ch, int pos){
            this.ch = ch;
            this.pos = pos;
        }
    }

    public static void main(String[] args){
        String s = "helloworldh";
        System.out.println(firstUniqChar4(s));
    }
}
