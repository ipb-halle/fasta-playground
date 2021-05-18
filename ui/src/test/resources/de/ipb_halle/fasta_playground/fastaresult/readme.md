## Result files from FASTA runs ##

Example sequence data was taken from [renzok/docker-ncbi-blast-demo](https://github.com/renzok/docker-ncbi-blast-demo/blob/master/bet_blaSHV.fasta).

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