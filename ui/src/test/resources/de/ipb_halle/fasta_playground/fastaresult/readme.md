## Result files from FASTA runs ##

Some example sequence data was taken from [renzok/docker-ncbi-blast-demo](https://github.com/renzok/docker-ncbi-blast-demo/blob/master/bet_blaSHV.fasta).

### (1) results1.txt ###
* normal fasta36 run
* `$FASTA_PATH/bin/fasta36 -m 10 query1.fasta data1.fasta > results1.txt`

### (2) results2.txt ###
* missing description fields for the query and subject sequences
* `$FASTA_PATH/bin/fasta36 -m 10 query2.fasta data2.fasta > results2.txt`

### (3) results3.txt ###
* like (2), but with interchanged the query and subject sequences
* `$FASTA_PATH/bin/fasta36 -m 10 query3.fasta data3.fasta > results3.txt`

### (4) results4.txt ###
* like (2), but with missing name and description in query and subject sequence, i.e. only ">" in the header line
* `$FASTA_PATH/bin/fasta36 -m 10 query4.fasta data4.fasta > results4.txt`

### (5) results5.txt ###
* empty header line in the subject sequence
* gives empty result set
* `$FASTA_PATH/bin/fasta36 -m 10 query5.fasta data5.fasta > results5.txt`

### (6) results6.txt ###
* empty header line in the query sequence
* fasta36 logs an error
* `$FASTA_PATH/bin/fasta36 -m 10 query6.fasta data6.fasta > results6.txt`

### (7) results7.txt ###
* normal fasta36 run, but with different library (7 sequences)
* result set contains 9 results, i.e. two library sequences have two hits
* Note: fasta36 prints the correct scores for the subsequent hits after applying a patch, see [GitHub issue #30](https://github.com/wrpearson/fasta36/issues/30#issuecomment-844255758).
* `$FASTA_PATH/bin/fasta36 -m 10 query7.fasta data7.fasta > results7.txt`