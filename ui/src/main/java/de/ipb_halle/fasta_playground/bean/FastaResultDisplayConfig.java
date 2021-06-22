/*
 * fasta-playground
 * Copyright 2021 Leibniz-Institut für Pflanzenbiochemie
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *     http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * 
 */
package de.ipb_halle.fasta_playground.bean;

public class FastaResultDisplayConfig {
	/*
	 * maximum length of the nucleotide or amino acid residue alignment lines
	 */
	private int lineLength = 50;

	/*
	 * minimum number of space characters between the sequence start position number
	 * and the start of the alignment line
	 */
	private int prefixSpaces = 2;

	/*
	 * minimum number of space characters between the end of the alignment line and
	 * the sequence end position number
	 */
	private int suffixSpaces = 2;

	public int getLineLength() {
		return lineLength;
	}

	public void setLineLength(int lineLength) {
		if (lineLength <= 0) {
			throw new IllegalArgumentException("Line length must be greater than 0.");
		}
		this.lineLength = lineLength;
	}
	
	public int getPrefixSpaces() {
		return prefixSpaces;
	}
	
	public void setPrefixSpaces(int prefixSpaces) {
		if (prefixSpaces <= 0) {
			throw new IllegalArgumentException("Number of prefix spaces must be greater than 0.");
		}
		this.prefixSpaces = prefixSpaces;
	}
	
	public int getSuffixSpaces() {
		return suffixSpaces;
	}
	
	public void setSuffixSpaces(int suffixSpaces) {
		if (suffixSpaces <= 0) {
			throw new IllegalArgumentException("Number of suffix spaces must be greater than 0.");
		}
		this.suffixSpaces = suffixSpaces;
	}
}