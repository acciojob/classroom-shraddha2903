package com.driver;

import java.sql.Struct;
import java.util.*;

public class StudentRepository {
    private Map<String,Student> students = new HashMap<>();
    private Map<String,Teacher> teachers = new HashMap<>();
    private Map<String, List<String>> studentTeacherPair= new HashMap<>();
    public void addStudent(Student student) {
        students.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher) {
        teachers.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String student, String teacher)
    {
       List<String> list = studentTeacherPair.getOrDefault(teacher,new ArrayList<>());
       list.add(student);
       studentTeacherPair.put(teacher,list);
    }

    public Student getStudentByName(String name) {
//        Student student=null;
//        for(Map.Entry<String,Student> hm : students.entrySet())
//        {
//            if(hm.getKey().equals(name))
//            {
//                student=hm.getValue();
//            }
//        }
        return students.get(name);
    }

    public Teacher getTeacherByName(String name) {
//        Teacher teacher = null;
//        for(Map.Entry<String,Teacher> hm : teachers.entrySet())
//        {
//            if(hm.getKey().equals(name))
//            {
//                teacher=hm.getValue();
//            }
//        }
        if(teachers.containsKey(name)) {
            return teachers.get(name);
        }
        return null;
    }

    public List<String> getStudentByTeachername(String teacher) {
        List<String> studentList = studentTeacherPair.getOrDefault(teacher,new ArrayList<>());
//        for(Map.Entry<String,List<String>> hm : studentTeacherPair.entrySet())
//        {
//            if(hm.getKey().equals(teacher))
//            {
//                studentList = hm.getValue();
//            }
//        }
        //return studentTeacherPair.get(teacher);
        return  studentList;
    }

    public List<String> getAllStudent() {
        return new ArrayList<>(students.keySet());
    }

    public void deleteTeacherByName(String teacher) {
        teachers.remove(teacher);
        studentTeacherPair.remove(teacher);
    }

//    public void deleteAllTeacher() {
//        teachers.clear();
//        studentTeacherPair.clear();
//    }

    public Optional<Student> getStudent(String student) {
        if(students.containsKey(student))
        {
            return  Optional.of(students.get(student));
        }
        return Optional.empty();
    }

    public Optional<Teacher> getTeacher(String teacher) {
        if(teachers.containsKey(teacher))
        {
            return  Optional.of(teachers.get(teacher));
        }
        return Optional.empty();
    }

    public void deletStudentByName(String stud) {
        students.remove(stud);
    }

    public List<String> getAllTeacher() {
        return new ArrayList<>(teachers.keySet());
    }
}
