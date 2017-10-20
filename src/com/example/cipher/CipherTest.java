package com.example.cipher;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Scanner;

/**
 * This program encrypts a file, using the Caesar cipher.
 */
public class CipherTest {
	public static void main(String[] args) {
		Scanner in = new Scanner(System.in);
		try {
			System.out.print("Enter the Input file: ");
			String inFile = in.next();
			System.out.print("Enter the Output file: ");
			String outFile = in.next();
			System.out.print("Enter the Encryption word: ");
			String key = in.next();

			InputStream inStream = new FileInputStream(inFile);
			OutputStream outStream = new FileOutputStream(outFile);

			Cipher cipher = new Cipher(key);
			cipher.InitArrayA();
			cipher.InitArrayB();
			cipher.encryptStream(inStream, outStream);
			System.out.println(cipher.toString());
			inStream.close();
			outStream.close();
		} catch (IOException exception) {
			System.out.println("Error processing file: " + exception);
		}
	}
}
