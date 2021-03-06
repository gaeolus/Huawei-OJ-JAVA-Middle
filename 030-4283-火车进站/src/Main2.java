import java.util.*;

/**
 * Author: 王俊超
 * Date: 2016-02-03 08:17
 * CSDN: http://blog.csdn.net/derrantcm
 * Github: https://github.com/Wang-Jun-Chao
 * Declaration: All Rights Reserved !!!
 */
public class Main2 {
    public static void main(String[] args) {
//        Scanner scanner = new Scanner(System.in);
        Scanner scanner = new Scanner(Main2.class.getClassLoader().getResourceAsStream("data.txt"));
        while (scanner.hasNext()) {
            int n = scanner.nextInt();
            String[] ss = new String[n];
            for (int i = 0; i < n; i++) {
                ss[i] = scanner.next();
            }

            System.out.println(trainOut(ss));
        }

        scanner.close();
    }

    private static String trainOut(String[] ss) {

//        Arrays.sort(ss);

        List<List<String>> result = new ArrayList<List<String>>();
        List<String> out = new ArrayList<String>(ss.length);
        List<String> in = new ArrayList<String>(ss.length);
        trainOut(0, ss, out, in, result);

        Collections.sort(result, new Comparator<List<String>>() {

            @Override
            public int compare(List<String> a, List<String> b) {

                int min = a.size() < b.size() ? a.size() : b.size();


                for (int i = 0; i < min; i++) {
                    String as = a.get(i);
                    String bs = b.get(i);
                    if (as.compareTo(bs) != 0) {
                        return as.compareTo(bs);
                    }
                }
                return a.size() - b.size();
            }
        });


        StringBuilder builder = new StringBuilder(256);
        for (List<String> list : result) {
            StringBuilder b = new StringBuilder(64);
            for (String s : list) {
                b.append(s).append(' ');
            }
            b.setCharAt(b.length() - 1, '\n');

            builder.append(b);
        }

        return builder.toString();
    }

    /**
     * 火车进站
     *
     * @param i      火车编号
     * @param ss     所有的火车
     * @param out    火车已经出站的序列
     * @param in  火车还未出站的序列
     * @param result 保存所有可能的结果
     */
    private static void trainOut(int i, String[] ss, List<String> out, List<String> in, List<List<String>> result) {

        // 所有的火车已经进站
        if (i >= ss.length) {
            List<String> list = new ArrayList<String>();
            for (String s : out) {
                list.add(s);
            }

            // 先进后出
            for (int j = in.size() - 1; j >= 0; j--) {
                list.add(in.get(j));
            }

            result.add(list);

            return;
        }

        // 第i辆车进来就出去了
        out.add(ss[i]);
        trainOut(i + 1, ss, out, in, result);
        // 还原
        out.remove(out.size() - 1);

        // 第i辆车进来没有出去
        in.add(ss[i]);
        trainOut(i + 1, ss, out, in, result);
        // 还原
        in.remove(in.size() - 1);
    }
}
