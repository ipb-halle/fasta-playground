# Archive notice
This GitHub repository is archived. Development of the artifacts will continue in the following projects:
* [fasta-search-service](https://github.com/ipb-halle/fasta-search-service): execution of fasta searches and result file parsing
* [CRIMSy](https://github.com/ipb-halle/CRIMSy): JSF frontend including display of alignments.

# fasta-playground

fasta-playground demonstrates sequence library searching via the [FASTA package](https://fasta.bioch.virginia.edu/fasta_www2/fasta_intro.shtml), result processing and visualization using Java Server Faces (JSF). This is a spin-off project of the [CRIMSy (Cloud Resource & Information Management System)](https://github.com/ipb-halle/CRIMSy).

UI design was inspired by [SequenceServer](https://sequenceserver.com) and [EBI's Sequence Similarity Searching Tools](https://www.ebi.ac.uk/Tools/sss/).

## Docker setup

Note: This is a [multi-stage build](https://docs.docker.com/develop/develop-images/multistage-build/) and requires Docker version ≥ 17.05.

Build the image: `docker build -t fasta-playground -f docker/Dockerfile .`

[Remove intermediate images from the build](https://stackoverflow.com/questions/50126741/how-to-remove-intermediate-images-from-a-build-after-the-build): `docker image prune --filter label=stage=fasta-playground-builder`

Start a container: `docker run -itp 8080:8080 --rm --name fasta-playground fasta-playground` (add `-d` for detached mode) and browse to [http://localhost:8080/Fasta-Playground/LibrarySearch.xhtml](http://localhost:8080/Fasta-Playground/LibrarySearch.xhtml).

## Non-Docker setup

Requires a JDK Version ≥ 8, Apache Maven, gcc, glibc and make.

Download and compile the [fasta36 package](https://github.com/wrpearson/fasta36) and alter the path to fasta36's *bin* directory in [SearchFactory.java](https://github.com/ipb-halle/fasta-playground/blob/main/ui/src/main/java/de/ipb_halle/fasta_playground/search/SearchFactory.java) (field `FASTA_BIN_DIRECTORY`) accordingly.

### Deployment via embedded Java EE application server

`cd ui`, run either `mvn clean package tomee-embedded:run` (embedded TomEE with MyFaces as JSF implementation) or `mvn clean package embedded-glassfish:run` (embedded GlassFish with Mojarra as JSF implementation) and browse to [http://localhost:8080/Fasta-Playground/LibrarySearch.xhtml](http://localhost:8080/Fasta-Playground/LibrarySearch.xhtml).

### Deployment on a Java EE application server

`cd ui`, run `mvn clean package` and deploy `target/Fasta-Playground.war` on a Java EE application server (requires at least Java EE 7 Web Profile with JSF 2.2).
