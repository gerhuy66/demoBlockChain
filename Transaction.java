
public class Transaction {
	private String sourceAddress;
	private String destinationAddress;
	private Integer value;
	private String signing;
	public String getSourceAddress() {
		return sourceAddress;
	}
	public Transaction(String sourceAddress, String destinationAddress, Integer value, String signing) {
		super();
		this.sourceAddress = sourceAddress;
		this.destinationAddress = destinationAddress;
		this.value = value;
		this.signing = signing;
	}
	public String getSigning() {
		return this.signing;
	}
	public void setSigning(String signing) {
		this.signing = signing;
	}
	public void setSourceAddress(String sourceAddress) {
		this.sourceAddress = sourceAddress;
	}
	public String getDestinationAddress() {
		return destinationAddress;
	}
	public void setDestinationAddress(String destinationAddress) {
		this.destinationAddress = destinationAddress;
	}
	public Integer getValue() {
		return value;
	}
	public void setValue(Integer value) {
		this.value = value;
	}
	@Override
	public String toString() {
		return "Transaction [sourceAddress=" + sourceAddress + ", destinationAddress=" + destinationAddress + ", value="
				+ value + ", signing=" + signing + "]";
	}
	
	
	
}
