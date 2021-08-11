package cn.ljava.jvm.loader;


/***
 * -XX:-DoEscapeAnalysis -XX:-EliminateAllocations -XX:-UseTLAB 不使用栈上分配
 */
public class TestTLAB {
    class User{
        int id;
        String name;
        public User(int id, String name){
            this.id = id;
            this.name = name;
        }
    }
    void alloc(int i){
        new User(i, "name" + i);
    }

    public static void main(String[] args){
        TestTLAB t = new TestTLAB();
        long start = System.currentTimeMillis();
        for (int i = 0; i < 10000000; i++) {
            t.alloc(i);
        }
        long end = System.currentTimeMillis();
        System.out.println(end - start);
    }
}
