import java.util.*;
import java.io.*;

//This class compresses the data of text via the huffman coding
public class HuffmanCode {
	private HuffmanNode root;
	
	//Given an array of integer frequencies, this constructor compresses the data
	//based off the frequencies from the array
	public HuffmanCode(int[] frequencies) {
		Queue<HuffmanNode> freqOrder = new PriorityQueue<>();
		
		for(int i = 0; i < frequencies.length; i++) {
			if(frequencies[i] > 0) {
				freqOrder.add(new HuffmanNode(i, frequencies[i]));
			}
		}
		
		HuffmanNode hold;
		
		while(freqOrder.size() > 1) {
			hold = freqOrder.remove();
			freqOrder.add(new HuffmanNode(0, hold.frequency + freqOrder.peek().frequency, hold,
					freqOrder.remove())); //this line builds the tree while reorganizing the priority queue
		}
		
		this.root = freqOrder.remove();
	}
	
	//Given a scanner reading from an input, this constructor compresses
	//from the information obtained from the input
	public HuffmanCode(Scanner input) {
		this.root = new HuffmanNode(0, 0);
		
		while(input.hasNextLine()) {
			HuffmanNode current = new HuffmanNode(Integer.parseInt(input.nextLine()), -1);
			huffmanBuilder(input, current);
		}
	}
	
	//Given a scanner reading from an input and a huffman node, this method
	//assists the HuffmanCode method in compressing the information obtained from the input
	private void huffmanBuilder(Scanner input, HuffmanNode current) {
		String code = input.nextLine();
		HuffmanNode traverse = this.root;
		
		for(int i = 0; i < code.length(); i++) {
			if(code.charAt(i) == '0') {
				if(traverse.left == null) {
					traverse.left = new HuffmanNode(current.ascii, 0);
				}
					traverse = traverse.left;
			}else {
				if(traverse.right == null) {
					traverse.right = new HuffmanNode(current.ascii, 0);
				}
					traverse = traverse.right;
			}
		}
	}
	
	//Given an output to print to, this method writes the current code of each character
	//into the output
	public void save(PrintStream output) {
		saveHuffman(output, this.root, "");
	}
	
	//Given an output to print to, a huffman node, and a string this method assists the save method
	//with writing the current code of each character into the output
	private void saveHuffman(PrintStream output, HuffmanNode current, String code) {
		if(current.left == null && current.right == null) {
			output.println(current.ascii);
			output.println(code);
		}else {
			saveHuffman(output, current.left, code + "0");
			saveHuffman(output, current.right, code + "1");
		}
	}
	
	//Given a input to read bits from, and an output to write to, this method
	//writes text to the output using the huffman code to find and print the characters
	public void translate(BitInputStream input, PrintStream output) {
		while(input.hasNextBit()) {
			translateHuffman(input, output, this.root);
		}
	}
	
	//Given a input to read bits from, an output to write to, and a huffman node this method
	//assists the translate method with writing text to the output using the huffman
	//code to find and print the characters
	private void translateHuffman(BitInputStream input, PrintStream output, HuffmanNode current) {
		if(current.left == null && current.right == null) {
			output.print((char) current.ascii);
		}else {
			if(input.nextBit() == 0) {
				translateHuffman(input, output, current.left);
			}else {
				translateHuffman(input, output, current.right);
			}
		}
	}
	
	//This class implements the comparable interface and represents a single huffman node
	private class HuffmanNode implements Comparable<HuffmanNode>{
		public int ascii;
		public int frequency;
		public HuffmanNode left;
		public HuffmanNode right;
		
		//Given two integer values representing the ascii value of a character and
		//the frequency of that character in a specific piece of text
		//this constructor creates a leaf huffman node
		public HuffmanNode(int ascii, int frequency){
			this(ascii, frequency, null, null);
		}
		
		////Given two integer values representing the ascii value of a character,
		//the frequency of that character in a specific piece of text, and two huffman nodes
		//this constructor creates a huffman node with two children huffman nodes represented
		//by the two nodes given
		public HuffmanNode(int ascii, int frequency, HuffmanNode left, HuffmanNode right) {
			this.ascii = ascii;
			this.frequency = frequency;
			this.left = left;
			this.right = right;
		}
		
		//This method compares two huffman nodes based on frequency of character
		//and returns an int with a positive number representing a higher frequency
		//which means "being greater than"
		public int compareTo(HuffmanNode other) {
			return this.frequency - other.frequency;
		}
	}
}
