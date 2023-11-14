package com.std.sbb;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.List;


@Controller
public class PersonController {
    List<Person> people;

    PersonController() {
        people = new ArrayList<>();
    }

    @GetMapping("/home/addPerson")
    @ResponseBody
    public String addPerson(String name, int age) {
        Person p = new Person(name, age);
        people.add(p);

        return "%d번 사람이 추가되었습니다.".formatted(p.getId());
    }

    @GetMapping("/home/people")
    @ResponseBody
    public List<Person> showPeople() {

        return people;
    }

    @GetMapping("/home/removePerson")
    @ResponseBody
    public String removePeroson(int id) {
//     people.remove(id - 1);
        boolean removed = people.removeIf(person -> person.getId() == id);

        if (!removed) {
            return id + "번 사람이 존재하지 않습니다.";
        }

        return id + "번 사람이 삭제되었습니다.";
    }

//    @GetMapping("/home/modifyPerson")
//    @ResponseBody
//    public String modifyPerson(int id, String name, int age) {
//        Person p = new Person(id, name, age);
//        people.removeIf(person -> person.getId() == id);
//
//
//        return id + "번 사람이 수정되었습니다.";
//    }

}

@Getter
@AllArgsConstructor
class Person {
    private static int lastId;

    static {
        lastId = 0;
    }

    private final int id;
    private final String name;
    private final int age;

    Person(String name, int age) {
        this.id = ++lastId;
        this.name = name;
        this.age = age;
    }

}
