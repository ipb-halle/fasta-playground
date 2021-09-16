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
package de.ipb_halle.fasta_playground.search;

import java.io.File;
import java.io.IOException;

import de.ipb_halle.fasta_playground.display.ResultDisplayConfig;

public abstract class SearchFactory {
	private static final String FASTA_BIN_DIRECTORY = "/usr/local/fasta36/bin";

	protected static final String PARAM_PROTEIN_QUERY = "-p";
	protected static final String PARAM_NUCLEOTIDE_QUERY = "-n";
	protected static final String PARAM_TRANSLATION_TABLE_FORMAT = "-t %d";

	public abstract String getProgramName();

	public abstract String[] getParams();

	public abstract ResultDisplayConfig getDisplayConfig();

	public SearchFactory withTranslationTable(TranslationTable table) {
		return this;
	}

	public final String execSearch(File libraryFile, File queryFile, String[] params) throws IOException {
		String program = FASTA_BIN_DIRECTORY + "/" + getProgramName();

		ProgramExecutor exec = new ProgramExecutor();
		exec.addCommands(program, "-q", "-m", "10").addCommands(params).addCommands(getParams())
				.addCommands(queryFile.getAbsolutePath(), libraryFile.getAbsolutePath());
		return exec.execute();
	}

	public interface Builder {
		public SearchFactory build();
	}
}