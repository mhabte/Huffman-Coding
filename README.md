# Huffman-Coding
The HuffmanCompressor class uses a HuffmanCode object to implement the Huffman coding algorithm to compress data so that the file with the compressed data takes up less space.

To test the HuffmanCode class, download all files and run HuffmanCompressor.java in an appropriate IDE while all the java files are in the same folder. You'll then be prompted to provide a txt file to read from, at which point you type simple.txt. From the options provided by the program, type the number 4, and the HuffmanCompressor class will have used a HuffmanCode object to compress and decompress the data in the simple.txt. You'll find two new files in your folder. A .code file which is the compressed code in the form of ascii value on the first line followed by the code on the next line, and it repeats that way for all the characters up to an ascii value of 255. The second file is a .new file with the decompressed data, and it should match the data from the original file.

(NOTE: HuffmanCompressor.java, BitInputStream.java, BitOutputStream.java, and simple.txt were not created by me and are simply provided as tools for you to test my program, HuffmanCode.java.)
