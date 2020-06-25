import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class TestBlockchain {

	public static void main(String args[]) {
		
		Blockchain tcpCoin = new Blockchain();
		
		
		List<Transaction> a_tranList = new ArrayList<Transaction>();
		List<Transaction> b_tranList = new ArrayList<Transaction>();
		List<Transaction> c_tranList = new ArrayList<Transaction>();
		
		a_tranList.add(new Transaction("Node A","Node B",30,"11"));
		b_tranList.add(new Transaction("Node A","Node B",-10,"11"));
		b_tranList.add(new Transaction("Node B","Node A",10,"7"));
		c_tranList.add(new Transaction("Node B","Node A",70,"7"));
		
		Block a = new Block("0x200", new java.util.Date(),a_tranList);
		Block b = new Block("0x200", new java.util.Date(),b_tranList);
		Block c = new Block("0x200", new java.util.Date(),c_tranList);
		
		tcpCoin.addBlock(a);
		tcpCoin.addBlock(b);
		tcpCoin.addBlock(c);
		
		//tcpCoin.getLatestBlock().setPreviousHash("ABCDEFG");
		
		tcpCoin.displayChain();
		
		System.out.println("Check BlockChain Validation !");
		tcpCoin.isValid();
		
		
		/*public key : 7
		 *private key: 6201229938866637031
		 *n Number: 7234768267404405787
		 */
		System.out.println("Checking Authentication Block A's Transactions...");
		BigInteger testInt = new BigInteger("3");
		
		BigInteger publicKey = new BigInteger(a_tranList.get(0).getSigning().trim());
		BigInteger privateKey = new BigInteger("7230963958846794419");
		BigInteger nNumber = new BigInteger("13256767265197684783");
		
		System.out.println("pub: "+publicKey);
		System.out.println("pri: "+privateKey);
		System.out.println("n: "+nNumber);
		
		BigInteger encrypInt = testInt.modPow(publicKey, nNumber);
		BigInteger decrypInt = encrypInt.modPow(privateKey, nNumber);
		if(decrypInt.intValue() == testInt.intValue()) {
			System.out.println("Transaction is valid !");
		}
		
		
	}
	
}
