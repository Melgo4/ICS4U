package banking;

public class Account {

	protected String lastName;
	protected String firstName;
	protected float balance;

	private String fullNameCache;

	public Account() {
		this("", "", 0.0F);
	}

	public Account(String lastName, String firstName, float balance) {
		this.lastName = lastName;
		this.firstName = firstName;
		this.balance = balance;

		this.fullNameCache = this.firstName + " " + this.lastName;

		if(this.balance < 0.0F) {
			System.err.format("%s-- you cannot start an account with a negative balance of %f$, setting to 0.00$.\n", this.fullNameCache, this.balance);
			this.balance = 0.0F;
		}
	}

	public float getBalance() {
		return this.balance;
	}

	public String getName() {
		return this.fullNameCache;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public void credit(double amount) {
		this.balance += amount;
	}

	public void debit(double amount) {
		if(amount > this.balance) {
			System.err.format("%s-- you cannot withdraw %f$ from a balance of %f$.\n", this.fullNameCache, amount, this.balance);
			return;
		}

		this.balance -= amount;
	}

	@Override
	public String toString() {
		return "Account{" +
				"lastName='" + this.lastName + '\'' +
				", firstName='" + this.firstName + '\'' +
				", balance=" + this.balance +
				'}';
	}

}
