package com.driver;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StudentRepository {
    Map<String,Student> students = new HashMap<>();
    Map<String,Teacher> teachers = new HashMap<>();
    Map<String, List<String>> studentTeacherPair= new HashMap<>();
    public void addStudent(Student student) {
        students.put(student.getName(),student);
    }

    public void addTeacher(Teacher teacher) {
        teachers.put(teacher.getName(),teacher);
    }

    public void addStudentTeacherPair(String student, String teacher)
    {
        List<String> studentList;
        if(!studentTeacherPair.isEmpty())
        {
            if(studentTeacherPair.containsKey(teacher))
            {
                studentList=new ArrayList<>(studentTeacherPair.get(teacher));
                studentTeacherPair.put(teacher,studentList);
            }
        }
        else{
            studentList=new ArrayList<>();
            studentList.add(student);
            studentTeacherPair.put(teacher,studentList);
        }
    }

    public Student getStudentByName(String name) {
        Student student=null;
        for(Map.Entry<String,Student> hm : students.entrySet())
        {
            if(hm.getKey().equals(name))
            {
                student=hm.getValue();
            }
        }
        return student;
    }

    public Teacher getTeacheByName(String name) {
        Teacher teacher = null;
        for(Map.Entry<String,Teacher> hm : teachers.entrySet())
        {
            if(hm.getKey().equals(name))
            {
                teacher=hm.getValue();
            }
        }
        return teacher;
    }

    public List<String> getStudentByTeachername(String teacher) {
        return studentTeacherPair.get(teacher);
    }

    public List<String> getAllStudent() {
        List<String> studentList=new ArrayList<>();
        for(Map.Entry<String,Student> hm : students.entrySet())
        {
            studentList.add(hm.getValue().getName());
        }
        return  studentList;
    }

    public void deleteTeacheByName(String teacher) {
        teachers.remove(teacher);
        studentTeacherPair.remove(teacher);
    }

    public void deleteAllTeacher() {
        teachers.clear();
        studentTeacherPair.clear();
    }
}
