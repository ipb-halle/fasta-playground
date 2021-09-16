# fasta-playground JSF frontend

## Other *fasta36 -m 10* parser implementations

* Ruby: [BioRuby](https://github.com/bioruby/bioruby) ([parser implementation](https://github.com/bioruby/bioruby/blob/master/lib/bio/appl/fasta/format10.rb); [Hit documentation](http://bioruby.org/rdoc/Bio/Fasta/Report/Hit.html))
* Python: [Biopython](https://github.com/biopython/biopython) ([parser implementation](https://github.com/biopython/biopython/blob/master/Bio/SearchIO/FastaIO.py))
* Java: [BioJava-legacy](https://github.com/biojava/biojava-legacy) ([Cookbook](https://biojava.org/wiki/BioJava%3ACookBook%3AFasta%3AParser); [JavaDoc FastaSearchSAXParser](https://biojava.org/docs/api1.9.5/org/biojava/bio/program/sax/FastaSearchSAXParser.html); the [FastaSearchParser implementation](https://github.com/biojava/biojava-legacy/blob/master/blast/src/main/java/org/biojava/bio/program/sax/FastaSearchParser.java) is not very greedy when it sees unknown tokens)