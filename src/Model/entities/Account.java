package Model.entities;

import java.util.Date;

import Model.exceptions.IncorrectPasswordException;
import Model.exceptions.InsuficientBalanceException;
import Model.exceptions.LoanOverTheLimitException;
import Model.exceptions.NegativeDepositException;
import Model.factory.Person;

public class Account {

	private String number;
	private String accountPassword;
	private boolean type;
	private float balance;
	private float loanLimit;
	private Date creationDate;
	private Person person;
	
	
	public Account(String number,String accountPassword) {
		this.number = number;
		this.accountPassword = accountPassword;
	}

	public Account(String number, String accountPassword, boolean type, float balance, float loanLimit, Date creationDate,
			Person person) {
		this.number = number;
		this.accountPassword = accountPassword;
		this.type = type;
		this.balance = balance;
		this.loanLimit = loanLimit;
		this.creationDate = creationDate;
		this.person = person;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getAccountPassword() {
		return accountPassword;
	}

	public void setAccountPassword(String accountPassword) {
		this.accountPassword = accountPassword;
	}

	public boolean getType() {
		return type;
	}

	public void setType(boolean type) {
		this.type = type;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(float balance) {
		this.balance = balance;
	}

	public double getLoanLimit() {
		return loanLimit;
	}

	public void setLoanLimit(float loanLimit) {
		this.loanLimit = loanLimit;
	}

	public Date getCreationDate() {
		return creationDate;
	}

	public void setCreationDate(Date creationDate) {
		this.creationDate = creationDate;
	}
	
	public Person getPerson() {
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public String checkBalance() {
		return "Your balance is: " + getBalance();
	}
	
	public void makeDeposit(double value) throws NegativeDepositException {
		if (value <= 0) {
			throw new NegativeDepositException("It is not possible to deposit negative amounts!");
		}else {
		balance += value;
		}
	}
	
	public void makeWithdraw(double value) throws InsuficientBalanceException {
		if(value > balance) {
			throw new InsuficientBalanceException("Insucient balance!");
		} else {
			balance -= value;
		}
	}
	
	
	public void makeLoan(double value) throws NegativeDepositException, LoanOverTheLimitException {
		if(value > loanLimit) {
			throw new LoanOverTheLimitException("Loan above the allowed limit!");
		} else {
			makeDeposit(value);
		}
		
	}
	
	public void makeTransfer(Account destination, double value) throws InsuficientBalanceException, NegativeDepositException {
		makeWithdraw(value);
		destination.makeDeposit(value);
	}
	
	// M�todo que realiza transfer�ncias solicitando senha devido ao valor alto da transfer�ncia
	public void makeTransfer(Account destination, double value, String accountPassword) throws InsuficientBalanceException, NegativeDepositException, IncorrectPasswordException {
		if(accountPassword  != getAccountPassword()) {
			throw new IncorrectPasswordException("Incorrect password!");
		} else {
			makeWithdraw(value);
			destination.makeDeposit(value);
		}
	}
	
	public void generateHistory() {
	}
	
	public String toString() {
		return "Name: " +
				person.getName() +
				"\nAccount number: " +
				getNumber() +
				"\nAccount type: Physical" +
				"\nBalance: " +
				getBalance() +
				"\nLoan limit: " +
				getLoanLimit();
				
	}
	
}
