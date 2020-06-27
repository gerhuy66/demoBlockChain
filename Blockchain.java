import java.util.ArrayList;
import java.util.List;

public class Blockchain {

	private List<Block> chain;

	public Blockchain() {
		chain = new ArrayList<Block>();
		chain.add(generateGenesis());
	}

	public List<Block> getChain() {
		return chain;
	}

	public void setChain(List<Block> chain) {
		this.chain = chain;
	}

	private Block generateGenesis() {
		List<Transaction> tranList = new ArrayList<Transaction>();
		tranList.add(new Transaction("A", "A", 0, "000"));
		Block genesis = new Block("0x200", new java.util.Date(), tranList);
		genesis.setPreviousHash(null);
		genesis.computeHash();
		return genesis;
	}

	public void addBlock(Block blk) {
		Block newBlock = blk;
		newBlock.setPreviousHash(chain.get(chain.size() - 1).getHash());
		newBlock.computeHash();
		this.chain.add(newBlock);
	}

	public void addBlock(Block blk, String hash) {
		Block newBlock = blk;
		newBlock.setPreviousHash(chain.get(chain.size() - 1).getHash());
		newBlock.setHash(hash);
		this.chain.add(newBlock);
	}

	public void displayChain() {

		for (int i = 0; i < chain.size(); i++) {
			if (i > 0) {
				System.out.println("------------------------");
				System.out.println("************************");
			}
			System.out.println("Block: " + i);
			System.out.println("Version: " + chain.get(i).getVersion());
			System.out.println("Timestamp: " + chain.get(i).getTimestamp());
			System.out.println("PreviousHash: " + chain.get(i).getPreviousHash());
			System.out.println("Hash: " + chain.get(i).getHash());
			System.out.println("---------Transactions-------");

			for (Transaction t : chain.get(i).getData()) {
				System.out.println(t.toString());
			}
			// System.out.println("T: " + chain.get(i).getHash());
			System.out.println();
		}

	}

	public Block getLatestBlock() {
		return this.chain.get(chain.size() - 1);
	}

	public void isValid() {

		for (int i = chain.size() - 1; i > 0; i--) {
			if (!(chain.get(i).getHash().equals(chain.get(i).computeHash()))) {
				System.out.println("Chain is not valid");
				return;
			}

			if (!(chain.get(i).getPreviousHash().equals(chain.get(i - 1).computeHash()))) {
				System.out.println("Chain is not valid");
				return;
			}
		}

		System.out.println("Chain is valid.");

	}

}
