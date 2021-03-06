package com.group.jsp;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class DbInstance {
    /******************** Authentication***************************/
    // Get uid corresponds to a certain username.
    public static long getUid(String username) {
        return 0;
    }

    // Get uid by a **valid** email.
    // Should be used by 'email' module.
    public static long getUidByEmail(String email) {
        return 0;
    }

    // Get uid by a **valid** token.
    public static long getUidByToken(long token) {
        return 0;
    }

    // Judge whether a username already exists.
    // Should be used in **SignUp**.
    public static boolean usernameExist(String username) {
        return false;
    }

    // Judge whether a email already exists.
    // Should be used in **SignUp**, **isSignIn**.
    public static boolean emailExist(String email) throws SQLException {
        String query = String.format("SELECT * FROM users WHERE email = '%s'", email);
        return DbAccessor.getData(query, ResultSet::next);
    }

    private static <T> IResultSetHandler<T> nullRet(IResultSetHandler<T> f) throws SQLException {
        return rs -> {
            if (rs.next()) {
                return f.f(rs);
            } else {
                return null;
            }
        };
    }

    public static Question getQuestionById(int id) throws SQLException {
        String select =
                String.format("SELECT * FROM question where id = '%d'", id);
        return DbAccessor.getData(select, nullRet(DbInstance::getQuestionByRs));
    }

    public static Answer getAnswerById(int id) throws SQLException {
        String select =
                String.format("SELECT * FROM answer where id = '%d'", id);
        return DbAccessor.getData(select, nullRet(DbInstance::getAnswerByRs));
    }

    public static List<Answer> getAnswerListByQuestionId(int id) throws SQLException {
        String select =
                String.format("SELECT * FROM answer where question_id = '%d'", id);
        return DbAccessor.getDataList(select, DbInstance::getAnswerByRs);
    }

    public static List<Question> getQuestionListByClassId(int id) throws SQLException {
        String select =
                String.format("SELECT * FROM question where class_id = %d", id);
        return DbAccessor.getDataList(select, DbInstance::getQuestionByRs);
    }

    public static List<Question> getTop10Question() throws SQLException {
        String select = "SELECT * FROM question ORDER BY response_count DESC LIMIT 10";
        return DbAccessor.getDataList(select, DbInstance::getQuestionByRs);
    }

    public static Class getClassById(int id) throws SQLException {
        String select =
                String.format("SELECT * FROM class WHERE id = %s", id);
        return DbAccessor.getData(select, nullRet(DbInstance::getClassByRs));
    }

    public static List<Class> getClassList() throws SQLException {
        String select = "SELECT * FROM class";
        return DbAccessor.getDataList(select, DbInstance::getClassByRs);
    }

    public static User getUserById(int id) throws SQLException {
        String select = String.format("SELECT * FROM users WHERE id = %s", id);
        return DbAccessor.getData(select, nullRet(DbInstance::getUserByRs));
    }

    public static User getUserByEmail(String email) throws SQLException {
        String select = String.format("SELECT * FROM users WHERE email = '%s'", email);
        return DbAccessor.getData(select, nullRet(DbInstance::getUserByRs));
    }

    public static void insertUser(User u) throws SQLException {
        String insert = "INSERT INTO users VALUES(?, ?, ?, ?, ?)";
        DbAccessor.execPrepare(insert,
                p -> {
                    p.setInt(1, u.id);
                    p.setString(2, u.name);
                    p.setString(3, u.email);
                    p.setString(4, u.pass);
                    p.setInt(5, u.type.getValue());
                });
    }

    public static void insertAnswer(Answer a) throws SQLException {
        String insert = "INSERT INTO answer VALUES(?, ?, ?, ?, ?)";
        DbAccessor.execPrepare(insert,
                p -> {
                    p.setInt(1, a.id);
                    p.setInt(2, a.question_id);
                    p.setDate(3, new java.sql.Date(a.date.getTime()));
                    p.setInt(4, a.user_id);
                    p.setString(5, a.content);
                });
    }

    public static void insertQuestion(Question q) throws SQLException {
        String insert = "INSERT INTO question VALUES(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        DbAccessor.execPrepare(insert,
                p -> {
                    p.setInt(1, q.id);
                    p.setInt(2, q.class_id);
                    p.setString(3, q.title);
                    p.setString(4, q.content);
                    p.setString(5, q.makeTags());
                    p.setInt(6, q.type.getValue());
                    p.setDate(7, new java.sql.Date(q.create_date.getTime()));
                    p.setDate(8, new java.sql.Date(q.modified_date.getTime()));
                    p.setInt(9, q.response_count);
                    p.setInt(10, q.view_count);
                    p.setInt(11, q.good_question_count);
                    p.setBoolean(12, q.teacher_ask);
                    p.setBoolean(13, q.teacher_answer);
                });
    }

    public static void insertClass(Class c) throws SQLException {
        String insert = "INSERT INTO class VALUES(?, ?, ?, ?, ?, ?)";
        DbAccessor.execPrepare(insert,
                p -> {
                    p.setInt(1, c.id);
                    p.setString(2, c.name);
                    p.setString(3, c.description);
                    p.setDate(4, new java.sql.Date(c.date_begin.getTime()));
                    p.setDate(5, new java.sql.Date(c.date_end.getTime()));
                    p.setInt(6, c.teacher_id);
                });
    }

    public static void add1ResponseCount(int questionId) throws SQLException {
        String update = "UPDATE question SET response_count = response_count + 1 WHERE id = ?";
        DbAccessor.execPrepare(update, p -> { p.setInt(1, questionId); });
    }

    public static void add1ViewCount(int questionId) throws SQLException {
        String update = "UPDATE question SET view_count = view_count + 1 WHERE id = ?";
        DbAccessor.execPrepare(update, p -> { p.setInt(1, questionId); });
    }

    public static UploadFile getUploadFileById(int id) throws SQLException {
        String select = String.format("SELECT * FROM file WHERE id = %d", id);
        return DbAccessor.getData(select, nullRet(DbInstance::getUploadFileByRs));
    }

    public static List<UploadFile> getUploadFileList() throws SQLException {
        String select = "SELECT * FROM file";
        return DbAccessor.getDataList(select, DbInstance::getUploadFileByRs);
    }

    public static void insertUploadFile(UploadFile f) throws SQLException {
        String insert = "INSERT INTO file VALUES(?, ?, ?)";
        DbAccessor.execPrepare(insert, p -> {
            p.setInt(1, f.id);
            p.setString(2, f.url);
            p.setString(3, f.name);
        });
    }

    public static int newSafeFileId() throws SQLException {
        int rnd;
        do {
            rnd = Utils.makeId();
        } while ( getUploadFileById(rnd) != null );
        return rnd;
    }

    private static Question getQuestionByRs(ResultSet rs) throws SQLException {
        return new Question(
                rs.getInt(1),
                rs.getInt(2),
                rs.getString(3),
                rs.getString(4),
                Question.parseTags(rs.getString(5)),
                Question.parseType(rs.getInt(6)),
                rs.getDate(7),
                rs.getDate(8),
                rs.getInt(9),
                rs.getInt(10),
                rs.getInt(11),
                rs.getBoolean(12),
                rs.getBoolean(13));
    }

    private static User getUserByRs(ResultSet rs) throws SQLException {
        return new User(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                User.parseTypes(rs.getInt(5)));
    }

    private static Answer getAnswerByRs(ResultSet rs) throws SQLException {
        return new Answer(
                rs.getInt(1),
                rs.getInt(2),
                rs.getDate(3),
                rs.getInt(4),
                rs.getString(5));
    }

    private static Class getClassByRs(ResultSet rs) throws SQLException {
        return new Class(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3),
                rs.getDate(4),
                rs.getDate(5),
                rs.getInt(6));
    }

    private static UploadFile getUploadFileByRs(ResultSet rs) throws SQLException {
        return new UploadFile(
                rs.getInt(1),
                rs.getString(2),
                rs.getString(3)
        );
    }
}
