package com.driver;

import java.util.List;
import java.util.Optional;

public class StudentService {

    private StudentRepository studentRepository = new StudentRepository();
    public void addStudent(Student student)
    {
        studentRepository.addStudent(student);
    }

    public void addTeacher(Teacher teacher) {
        studentRepository.addTeacher(teacher);
    }

    public void addStudentTeacherPair(String student, String teacher) throws TeacherNotFoundException,StudentNotFoundException{
        Optional<Student> student1=studentRepository.getStudent(student);
        Optional<Teacher> teacher1 = studentRepository.getTeacher(teacher);

        if(!student1.isPresent())
        {
            throw new StudentNotFoundException("student not found");
        }
        if(!teacher1.isPresent())
        {
            throw new TeacherNotFoundException("Teacher not found");
        }
        //updating number of Students in teacher class
        Teacher teacherObj = teacher1.get();
        teacherObj.setNumberOfStudents(teacherObj.getNumberOfStudents()+1);
        studentRepository.addTeacher(teacherObj);
        studentRepository.addStudentTeacherPair(student,teacher);


    }

    public Student getStudentByName(String name) throws  StudentNotFoundException {
        Optional<Student> student = studentRepository.getStudent(name);
        if(!student.isEmpty())
            return student.get();
        throw new StudentNotFoundException("Student Not Found");
    }

    public Teacher getTeacherByName(String name) throws TeacherNotFoundException {
//        Teacher teacher = null;
//        teacher = studentRepository.getTeacheByName(name);
//        if(teacher!=null)
//        {
//            return teacher;
//        }
        Optional<Teacher> teacher = studentRepository.getTeacher(name);
        if(!teacher.isEmpty())
            return teacher.get();
        throw new TeacherNotFoundException("Teacher Not Found with name: "+name);
    }

    public List<String> getStudentByTeachername(String teacher) throws TeacherNotFoundException{
//        List<String> studentList = null;
//        if(getTeacherByName(teacher)!=null)
//        {
//            studentList = studentRepository.getStudentByTeachername(teacher);
//        }
//        return  studentList;
        List<String> studentList = studentRepository.getStudentByTeachername(teacher);
        if(!studentList.isEmpty())
        {
            return  studentList;
        }
        throw new TeacherNotFoundException("Not Found");
    }

    public List<String> getAllStudents() {
        List<String> studentList  = null;
        studentList = studentRepository.getAllStudent();
        if(!studentList.isEmpty())
            return studentList;
        throw new StudentNotFoundException("There is no student available");
    }

    public void deleteTeacherByName(String teacher) {
        List<String> student = getStudentByTeachername(teacher);
        studentRepository.deleteTeacherByName(teacher);
        for(String stud : student)
        {
            studentRepository.deletStudentByName(stud);
        }
    }

    public void deleteAllTeachers() {
        //studentRepository.deleteAllTeacher();
        List<String> tech = studentRepository.getAllTeacher();
        for(String teach : tech)
        {
            deleteTeacherByName(teach);
        }
    }
}
