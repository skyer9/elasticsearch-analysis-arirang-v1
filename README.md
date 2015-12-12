# elasticsearch-analysis-arirang-v1

$ git clone https://github.com/skyer9/arirang.morph.git
$ cd arirang.morph
$ mvn package
$ mvn install:install-file -Dfile=target/arirang-morph-1.0.0.jar -DpomFile=pom.xml

$ git clone https://github.com/skyer9/arirang.lucene-analyzer-v4.git
$ cd arirang.lucene-analyzer-v4
$ mvn package
$ mvn install:install-file -Dfile=target/arirang-analyzer-4.7.2.jar -DpomFile=pom.xml

$ git clone https://github.com/skyer9/elasticsearch-analysis-arirang-v1.git
$ cd elasticsearch-analysis-arirang-v1
$ mvn package
