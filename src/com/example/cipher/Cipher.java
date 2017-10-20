package com.example.cipher;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Scanner;

public class Cipher {
	private String word;
	private char[] A;
	private char[] B;

	/**
	 * Initialize word with parameter key and create an array of 26 elements for A
	 * and also for B
	 * 
	 * @param key
	 *            a String
	 */
	public Cipher(String key) {

		this.word = key.toUpperCase();
		A = new char[26];

		B = new char[26];

	}

	public void InitArrayA() {

		for (int i = 0; i < 26; i++) {
			A[i] = (char) ('A' + i);
		}
	}

	/**
	 * Check and see if letter ch can be found among the first num elements of array
	 * B.
	 * 
	 * @param ch
	 *            the target letter
	 * @param num
	 *            the number of elements to search Return 1 if ch is found,
	 *            otherwise -1.
	 */
	public boolean foundInB(char ch, int num) {

		for (int i = 0; i < num; i++) {
			if (ch == B[i]) {
				return true;
			}

		}

		return false;
	}

	/**
	 * Initialize array B, first with letters in instance variable word then with
	 * letters ‘Z’ through ‘A’. Note that there are no duplicate letters in B; that
	 * is, ignore the letter under consideration if it is already in the array.
	 */
	public void InitArrayB() {
		int pos = 0; // the position in which the next
						// letter is stored

		// loop through each letter in instance variable
		// word and store it in array B if it is not there

		for (int i = 0; i < word.length(); i++) {
			if (!(foundInB(word.charAt(i), i))) {
				B[pos] = word.charAt(i);

				pos++;
			}

		}

		// loop through each letter from ‘Z’ to ‘A’ and store
		// it in array B if it is not there already
		
		for (int j = pos; j < (B.length + pos); j++) {

			if (!foundInB((char) ('Z' - (j - pos)), pos)) {
				B[pos] = (char) ('Z' - (j - pos));
				pos++;

			}

		}}

	public void encryptStream(InputStream in, OutputStream out) throws IOException {
		Scanner scanner = new Scanner(System.in);
		boolean done = false;
		System.out.println("Do you want to Encrypt of Decrypt. If Encryption  Press 1 otherwise Press 2:");
		int input = scanner.nextInt();

		while (!done) {

			int next = in.read();

			if (next == -1) {
				done = true;
			}
			char b = (char) next;

			if (input == 1) {
				char c = encrypt(b);
				out.write(c);
			} else {
				char c = decrypt(b);
				out.write(c);
			}

		}

		scanner.close();

	}

	public char decrypt(char c) {
		char hold = '\0';
		if (Character.isUpperCase(c)) {
			for (int i = 0; i < B.length; i++) {
				if (c == B[i]) {
					hold = A[i];
				}
			}

		}

		else if (Character.isLowerCase(c)) {

			String s = Character.toString(c);
			for (int i = 0; i < B.length; i++) {
				if (s.toUpperCase().charAt(0) == B[i]) {
					String s1 = Character.toString(A[i]);
					hold = s1.toLowerCase().charAt(0);

				}

			}

		}

		return hold;

	}

	public char encrypt(char c) {
		char hold = '\0';

		if (Character.isUpperCase(c)) {
			for (int i = 0; i < A.length; i++) {
				if (c == A[i]) {
					hold = B[i];
				}
			}

		}

		else if (Character.isLowerCase(c)) {
			String s = Character.toString(c);
			for (int i = 0; i < A.length; i++) {
				if (s.toUpperCase().charAt(0) == A[i]) {
					String s1 = Character.toString(B[i]);
					hold = s1.toLowerCase().charAt(0);

				}

			}
		} 

		return hold;

	}

	/**
	 * Return a string representation of an object of this class
	 * 
	 * @Override
	 */
	public String toString() {
		int i;
		String s = word + "\n";

		s += "A array is ";
		for (i = 0; i < A.length; i++)
			s += A[i] + " ";
		s += "\n";

		s += "B array  is ";
		for (i = 0; i < B.length; i++)
			s += B[i] + " ";
		return s;
	}

}
