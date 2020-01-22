/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hotel.service;

import hotel.dao.LoginDao;
import hotel.model.LoginUser;

/**
 *
 * @author ferha
 */
public class LoginServiceImpl implements LoginService{
    
    private LoginDao loginDao;

    public LoginServiceImpl(LoginDao loginDao) {
        this.loginDao = loginDao;
    }

    @Override
    public LoginUser login(String username, String password) throws Exception {
        return loginDao.login(username, password);
    }
    
}
