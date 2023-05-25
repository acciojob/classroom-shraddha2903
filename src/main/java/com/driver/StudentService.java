package com.driver;

import java.util.List;

public class StudentService {

    StudentRepository studentRepository = new StudentRepository();
    public void addStudent(Student student)
    {
        studentRepository.addStudent(student);
    }

    public void addTeacher(Teacher teacher) {
        studentRepository.addTeacher(teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) {
        studentRepository.addStudentTeacherPair(student,teacher);
    }

    public Student getStudentByName(String name) throws  StudentNotFoundException {
        Student student = studentRepository.getStudentByName(name);
        if(student!=null)
            return student;
        throw new StudentNotFoundException("Student Not Found");
    }

    public Teacher getTeacherByName(String name) throws TeacherNotFoundException {
        Teacher teacher = null;
        teacher = studentRepository.getTeacheByName(name);
        if(teacher!=null)
        {
            return teacher;
        }
        throw new TeacherNotFoundException("Teacher Not Found with name:"+name);
    }

    public List<String> getStudentByteachername(String teacher) {
        List<String> studentList = null;
        if(getTeacherByName(teacher)!=null)
        {
            studentList = studentRepository.getStudentByTeachername(teacher);
        }
        return  studentList;
    }

    public List<String> getAllStudent() {
        List<String> studentList  = null;
        studentList = studentRepository.getAllStudent();
        if(!studentList.isEmpty())
            return studentList;
        throw new StudentNotFoundException("There is no student available");
    }

    public void deleteTeacheByName(String teacher) {
        studentRepository.deleteTeacheByName(teacher);
    }

    public void deleteAllTeacher() {
        studentRepository.deleteAllTeacher();
    }
}
