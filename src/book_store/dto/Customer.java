package book_store.dto;

public class Customer {

	private int id;
	private String customerName;
	private String email;
	private String password;
	private long phone;
	private double wallet;
	private String address;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public long getPhone() {
		return phone;
	}

	public void setPhone(long phone) {
		this.phone = phone;
	}

	public double getWallet() {
		return wallet;
	}

	public void setWallet(double wallet) {
		this.wallet = wallet;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Customer(int id, String customerName, String email, String password, long phone, double wallet,
			String address) {
		super();
		this.id = id;
		this.customerName = customerName;
		this.email = email;
		this.password = password;
		this.phone = phone;
		this.wallet = wallet;
		this.address = address;
	}

	public Customer() {
		super();
	}

	@Override
	public String toString() {
		return "Customer [id=" + id + ", customerName=" + customerName + ", email=" + email + ", password=" + password
				+ ", phone=" + phone + ", wallet=" + wallet + ", address=" + address + "]";
	}

}
