import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

public class Block {

	private String version;
	private Date Timestamp;
	private String hash;
	private String previousHash;
	private List<Transaction> datalist;
	private Integer increment_number=0;
	
	public Block(String version, Date timestamp, List<Transaction> datalist) {
		this.version = version;
		this.Timestamp = timestamp;
		this.datalist = datalist;
		this.hash = computeHash();
	}
	public Block(String version, Date timestamp, List<Transaction> datalist,String hash) {
		this.version = version;
		this.Timestamp = timestamp;
		this.datalist = datalist;
		this.hash = hash;
	}
	public Integer getIncrement_number() {
		return increment_number;
	}
	public void setIncrement_number(Integer increment_number) {
		this.increment_number = increment_number;
	}
	public String computeHash() throws NullPointerException{
		String inNum ="";
		try {
			inNum = this.getIncrement_number().toString();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		String dataToHash = "" + this.version + this.Timestamp + this.previousHash+inNum;
		
		MessageDigest digest;
		String encoded = null;
		
		try {
			digest = MessageDigest.getInstance("SHA-256");
			byte[] hash = digest.digest(dataToHash.getBytes(StandardCharsets.UTF_8));
			encoded = Base64.getEncoder().encodeToString(hash);
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		}
		
		this.hash = encoded;
		return encoded;
		
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public Date getTimestamp() {
		return Timestamp;
	}

	public void setTimestamp(Date timestamp) {
		Timestamp = timestamp;
	}

	public String getHash() {
		return hash;
	}

	public void setHash(String hash) {
		this.hash = hash;
	}

	public String getPreviousHash() {
		return previousHash;
	}

	public void setPreviousHash(String previousHash) {
		this.previousHash = previousHash;
	}

	public List<Transaction> getData() {
		return datalist;
	}

	public void setData(List<Transaction> datalist) {
		this.datalist = datalist;
	}
	
	
	
}
