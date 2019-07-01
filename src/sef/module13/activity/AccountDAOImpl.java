package sef.module13.activity;

import sef.module8.activity.AccountException;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class AccountDAOImpl implements AccountDAO {

	@SuppressWarnings("unused")
	private Connection conn;

	public AccountDAOImpl(Connection conn) {
		this.conn = conn;
	}

	public List<Account> findAccount(String firstName, String lastName)
			throws AccountDAOException {

		try {
			PreparedStatement pStmt = conn.prepareStatement("select * from Account where instr(UPPER(FIRST_NAME), ?) != 0 and LAST_NAME = ? order by ID asc");
			pStmt.setString(1, firstName);
			pStmt.setString(2, lastName);
			ResultSet rs = pStmt.executeQuery();
			List<Account> accountList = new ArrayList<Account>();
			while(rs.next()){
				Account temp = new AccountImpl(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				accountList.add(temp);
			}
			return accountList;
		}
		catch (Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}

	public Account findAccount(int id) throws AccountDAOException {

		try {
			PreparedStatement pStmt = conn.prepareStatement("select * from Account where ID = ?");
			pStmt.setInt(1, id);
			ResultSet rs = pStmt.executeQuery();
			if(rs.next()) {
				Account temp = new AccountImpl(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
				return  temp;
			}
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return null;
	}


	public boolean insertAccount(String firstName, String lastName, String email)
			throws AccountDAOException {

		try{
			PreparedStatement pStmt = conn.prepareStatement("insert into Account(ID, FIRST_NAME,LAST_NAME,E_MAIL) values (ACCOUNT_SEQ.NEXTVAL, ?,?,?)");
			pStmt.setString(1, firstName);
			pStmt.setString(2, lastName);
			pStmt.setString(3, email);
			pStmt.executeUpdate();
			return true;
		}
		catch(Exception e){
			System.out.println(e.getMessage());
		}
		return false;
	}

}
