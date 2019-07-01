package sef.module8.activity;


/**
 * This class represents a simple representation of an account encapsulating
 * a name 
 * 
 * @author John Doe
 *
 */
public class Account {



	/**
	 * Creates an Account object with the specified name.  If the accout name
	 * given violates the minimum requirements, then an AccountException is thrown
	 * 
	 * @param accountName
	 * @throws AccountException
	 */
	private String accountName;

	public  Account(String accountName) throws AccountException{

		if(accountName.length() < 4){
			throw new AccountException(AccountException.NAME_TOO_SHORT, accountName);
		}
		int numberChar = 0;
		for (char c: accountName.toCharArray() ) {
			if (!Character.isDigit(c)) {
				numberChar++;
			}
		}
		if(numberChar==accountName.length())
			throw new AccountException(AccountException.NAME_TOO_SIMPLE, accountName);
		this.accountName = accountName;
			
	}
	
	
	/**
	 * Returns the account name
	 * 
	 * @return the account name
	 */
	public String getName(){
		return accountName;
	}
}
