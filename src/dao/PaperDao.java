package dao;

import pojo.Paper;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PaperDao {
    private BaseDao baseDao;
    private static PaperDao instance;

    public static PaperDao getInstance(){
        if (instance == null){
            instance = new PaperDao();
        }
        return instance;
    }

    private PaperDao(){
        baseDao = BaseDao.getInstance();
    }

    public List<Paper> getPaperList() throws SQLException {
        List<Paper> papers = new ArrayList<>();

        final  String sql = "SELECT * FROM Paper";
        PreparedStatement preparedStatement = baseDao.getConnection().prepareStatement(sql);
        ResultSet resultSet = preparedStatement.executeQuery();
        while (resultSet.next()){
            Paper paper = this.getPaper(resultSet);
            papers.add(paper);
        }

        return papers;
    }

    private Paper getPaper(ResultSet resultSet) throws SQLException {
        Paper paper = new Paper();

        paper.setPaperId(resultSet.getString("id"));
        paper.setPaperAuthor(resultSet.getString("author"));
        paper.setPaperDate(resultSet.getString("date"));
        paper.setPaperTitle(resultSet.getString("title"));
        paper.setPaperContext(resultSet.getString("context"));

        return paper;
    }

    public void insert(Paper paper){
        final String sql = "INSERT INTO Paper (id,title,author,content,date) values(?,?,?,?,?)";

        try{
            PreparedStatement preparedStatement = baseDao.getConnection().prepareStatement(sql);
            preparedStatement.setString(1,paper.getPaperId());
            preparedStatement.setString(2,paper.getPaperTitle());
            preparedStatement.setString(3,paper.getPaperAuthor());
            preparedStatement.setString(4,paper.getPaperContext());
            preparedStatement.setString(5,paper.getPaperDate());

            preparedStatement.execute();


        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }


}
