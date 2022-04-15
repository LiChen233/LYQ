import com.spire.ms.System.Collections.ICollection;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author zhy
 * @email 2434017367@qq.com
 * @date 2022/4/7 9:33
 */
public class Test {

    /**
     * 根据成绩进行升序排序并输出 “姓名：成绩”
     */
    @org.junit.Test
    public void test1() {
        List<Student> studentList = new ArrayList<>();
        studentList.add(new Student("张三", 80));
        studentList.add(new Student("李四", 60));
        studentList.add(new Student("王五", 81));
        studentList.add(new Student("张伟", 51));
        studentList.add(new Student("赵四", 90));
    }

    @org.junit.Test
    public void test2() {
        Integer i1 = new Integer(1000);
        Integer i2 = new Integer(1000);
        // i1是否等于i2
    }

    public void test3() {
        // ArrayList 和 LinkedList 有什么区别

        // HashMap 和 TreeMap 有什么区别
    }

}

/**
 * 学生
 */
class Student {

    /**
     * 姓名
     */
    private String name;

    /**
     * 成绩
     */
    private Integer result;

    public Student(String name, Integer result) {
        this.name = name;
        this.result = result;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getResult() {
        return result;
    }

    public void setResult(Integer result) {
        this.result = result;
    }
}
