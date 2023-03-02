package ru.clevertec.entity;

public class Info {
    private String name;
    private Long age;

    public Info(String name, Long age) {
        this.name = name;
        this.age = age;
    }

    public Info() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getAge() {
        return age;
    }

    public void setAge(Long age) {
        this.age = age;
    }

    @Override
    public String toString() {
        return "Info{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }
}
