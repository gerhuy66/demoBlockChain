import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
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
		
		System.out.println("pubicKey: "+publicKey);
		System.out.println("n: "+nNumber);
		
		BigInteger encrypInt = testInt.modPow(publicKey, nNumber);
		BigInteger decrypInt = encrypInt.modPow(privateKey, nNumber);
		if(decrypInt.intValue() == testInt.intValue()) {
			System.out.println("Transaction is valid !");
		}
		
		/*
		 * Mining Demo
		 * Mining A block that mean compute valid hash.
		 * 
		 */
		List<Transaction> frozenTransactions = new ArrayList<Transaction>();
		frozenTransactions.add(new Transaction("node A","node B",300,"11"));
		frozenTransactions.add(new Transaction("node B","node A",230,"7"));
		
		List<Transaction> dataList = new ArrayList<Transaction>();
		dataList.add(null);
		dataList.add(null);
		Collections.copy(dataList, frozenTransactions); ;
		Block tempBlock = new Block("0x200", new java.util.Date(),dataList);
		long start = System.nanoTime();
		String hashResult = MiningBlock(tempBlock,tcpCoin.getChain());
		while(isExistHash(hashResult,tcpCoin.getChain())) {
			hashResult=MiningBlock(tempBlock,tcpCoin.getChain());
		}
		
		long end = System.nanoTime()-start;
		System.out.println();
		System.out.println("Compute Hash Successfull !");
		System.out.println("Computed Hash: "+tempBlock.getHash());
		System.out.println("Total Time: "+end+" nano seconds");
		tempBlock.setHash(hashResult);
		tcpCoin.addBlock(tempBlock,tempBlock.getHash());
		frozenTransactions.removeAll(frozenTransactions);
		System.out.println();
		System.out.println("The new BlockChain is:");
		System.out.println();
		tcpCoin.displayChain();
		
	}
	
	public static String MiningBlock(Block tempBlock,List<Block> currentBlocks) {
		String rs_hash = tempBlock.getHash();
		while(!rs_hash.substring(0,3).equalsIgnoreCase("HUY")) {
			tempBlock.setIncrement_number(tempBlock.getIncrement_number()+1);
			rs_hash=tempBlock.computeHash();
		}
		return rs_hash;
	}
	public static Boolean isExistHash(String hash,List<Block> currentBlocks) {
		for (Block block : currentBlocks) {
			if(block.getHash().equals(hash)) {
				return true;
			}
		}
		return false;
	}
	
}
