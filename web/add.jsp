<%--
  Created by IntelliJ IDEA.
  User: xujingling
  Date: 2020/11/23
  Time: 22:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<script type="text/javascript" src="laydate/laydate.js"></script>
<html>
<head>
    <title>上传论文</title>
</head>
<body>
<form method="post" action="/upload">
    学号：<input type="text" name="stuId"><br>
    学生姓名： <input type="text" name="stuName"><br>
    学生所在行政班：<input type="text" name="stuClass"><br>
    <br>
    论文题目：<input type="text" name="paperTitle"><br>
    论文作者补充：<input type="text" placeholder="默认为学生本人" name="paperAuthor"><br>
    论文发表日期：<input type="text" name="paperDate" id="date">
    <input type="file" name="paperfile" accept=".doc,.docx">
    <script>
        lay('#version').html('-v'+ laydate.v);
        //执行一个laydate实例
        laydate.render({
            elem: '#date' //指定元素
        });
    </script>
    <br>
    <input type="submit" value="提交">
</form>

</body>
</html>
