package com.maizi.entity;

/**
 * @ClassName entity
 * @Description 用到的实体类
 * @Author Liuys
 * @Date 2019/7/10 10:26
 * @Verson 1.0
 **/
public class Person {
    private Integer id;
    private String name;
    private Integer age;

    public Person(String name) {
        this.name = name;
    }

    public Person() {

    }
    public Person(Integer id, String name, Integer age){
        this.id = id;
        this.name = name;
        this.age = age;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

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

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
    @Override
    public boolean equals(Object obj){
        if(obj == null || this.getClass() != obj.getClass()){
            return false;
        }
        if(this == obj){
            return true;
        }
        Person temp = (Person) obj;
        if(temp.getId().equals(this.id) && name.equals(temp.getName())){
            return true;
        }
        return false;
    }

    @Override
    public int hashCode(){
        if(id == null){
            id = 0;
        }
        return id + name.hashCode();
    }

}
