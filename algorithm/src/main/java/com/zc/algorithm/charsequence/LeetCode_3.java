package com.zc.algorithm.charsequence;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class LeetCode_3 {
    public static void main(String[] args) {
        LeetCode_3.solution("abcabcbd");
        LeetCode_3.solution("abba");
    }

    public static void solution(String str) {
        Map<Character, Integer> map = new HashMap<>(16);
        //用于记录最大不重复字符串的常长度
        int maxLen = 0;
        //滑动窗口左指针
        int left = 0;
        for (int i = 0; i < str.length(); i++) {
            /*
             * 判断map是否包含当前字符，如果不包括将其存入map。
             * key为具体字符，value为字符对应的下标。
             * 此时map中没有重复字符指针不需要移动。
             *
             * 如果map中包含当前字符，有两种情况：
             *    第一种情况：当前字符包含在当前有效的子段中。如：abca，当我们遍历到第二个a，当前有效最长子段是 abc，我们又遍历到a，
             *              那么此时更新 left 为 map.get(a)+1=1，当前有效子段更新为 bca；
             *    第二种情况：当前字符不包含在当前有效的子段中。如：abba，我们先将ab放进map中，由于没有重复key所以此时left=0.当我们再次添加
             *              b时，发现map中已经存在b了，而且是在最长的有效子段中也就是第一种情况，按照正常逻辑我们会通过left=map.get(b)+1
             *              来更新b的索引下标，也就是key=b,value=2.此时map.get(a)=0.即key=a,value=0.
             *              map：{
             *                  'a':0,
             *                  'b':2
             *              }
             *              继续遍历，当第二个a进来时，发现map中已经有a了且value=0,如果我们还是按照第一种情况来处理，那么left=map.get(a)+1，
             *              而实际上此时left应该保持不变，保持在下标2处，子段变成ba才对。
             *    为了处理以上2类情况，我们每次更新left时都需要用left原来下标的大小和当前要调整的字符对应下标的大小进行比较，left取最大值。
             *    left=Math.max(left, map.get(ch)+1).另外，更新left后，不管原来的s.charAt(i)是否在最长子段中，我们都要将s.charAt(i)
             *    的位置更新为当前的i，因此此时新的s.charAt(i)已经进入到当前最长的子段中！
             */
            if (map.containsKey(str.charAt(i))) {
                //移动指针下标
                left = Math.max(left, map.get(str.charAt(i)) + 1);
            }
            //不管是否更新left，都要更新 s.charAt(i) 的位置！
            map.put(str.charAt(i), i);
            maxLen = Math.max(maxLen, i - left + 1);
        }
        System.out.println(str + "最大不重复子串长度为：" + maxLen);
    }

    public static void solution2(){

    }
}
