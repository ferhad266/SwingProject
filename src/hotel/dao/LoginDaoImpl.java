/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.dao;

import hotel.model.LoginUser;
import hotel.model.Role;
import hotel.util.JdbcUtility;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author ferha
 */
public class LoginDaoImpl implements LoginDao {

    @Override
    public LoginUser login(String username, String password) throws Exception {
        LoginUser loginUser = new LoginUser();
        Connection c = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        String sql = "SELECT LU.ID,LU.USERNAME,LU.NAME,LU.SURNAME,R.ID ROLE_ID,R.ROLE_NAME FROM LOGIN_USER LU\n"
                + "INNER JOIN ROLE R ON LU.ROLE_ID = R.ID\n"
                + "WHERE LU.ACTIVE = 1 AND LU.USERNAME = ? and LU.PASSWORD = ?";
        try {
            c = DBHelper.ConnectToDB();
            if (c != null) {
                ps = c.prepareStatement(sql);
                ps.setString(1, username);
                ps.setString(2, password);
                rs = ps.executeQuery();
                if(rs.next()){
                    loginUser.setUsername(rs.getString("USERNAME"));
                    loginUser.setName(rs.getString("NAME"));
                    loginUser.setSurname(rs.getString("SURNAME"));
                    Role role= new Role();
                    role.setId(rs.getLong("ROLE_ID"));
                    role.setRoleName(rs.getString("ROLE_NAME"));
                    loginUser.setRole(role);
                }else{
                    loginUser = null;
                }
            } else {
                System.out.println("Connetion is null");
            }
        } catch (Exception ex) {
            ex.printStackTrace();
        } finally {
            JdbcUtility.close(c, ps, rs);
        }

        return loginUser;
    }

}
