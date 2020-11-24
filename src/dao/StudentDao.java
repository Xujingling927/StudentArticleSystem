package dao;

import pojo.Paper;
import pojo.Student;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StudentDao {
    private BaseDao baseDao;
    private static StudentDao instance;

    public static StudentDao getInstance(){
        if (instance == null){
            instance = new StudentDao();
        }
        return instance;
    }

    private StudentDao(){
        baseDao = BaseDao.getInstance();
    }

    public List<Student> getStudentList() throws SQLException {
        List<Student> students = new ArrayList<>();

        final  String sql = "SELECT * FROM Student";
        PreparedStatement preparedStatement = baseDao.getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Student student= this.getStudent(resultSet);
            students.add(student);
        }

        return students;
    }

    private Student getStudent(ResultSet resultSet) throws SQLException {
        Student student = new Student();

        student.setStudentId(resultSet.getString("id"));
        student.setStudentName(resultSet.getString("name"));
        student.setStudentClass(resultSet.getString("class"));

        return student;
    }

    public void insert(Student student){
        final String sql = "INSERT INTO Student (id,name,class) values(?,?,?)";

        try{
            PreparedStatement preparedStatement = baseDao.getConnection().prepareStatement(sql);
            preparedStatement.setString(1,student.getStudentId());
            preparedStatement.setString(2,student.getStudentName());
            preparedStatement.setString(3,student.getStudentClass());

            preparedStatement.execute();


        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }


}
