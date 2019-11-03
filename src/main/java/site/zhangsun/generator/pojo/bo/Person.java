package site.zhangsun.generator.pojo.bo;

import java.util.ArrayList;
import java.util.List;

public class Person {

    public Person(String name, Integer age) {
        this.age = age;
        this.name = name;
        this.child = new ArrayList<>(3);
    }

    private String name;
    private Integer age;
    private List<Person> child;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public List<Person> getChild() {
        return child;
    }

    public void setChild(List<Person> child) {
        this.child = child;
    }

}
