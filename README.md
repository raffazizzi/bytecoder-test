# JINGJS

## To compile

```shell
  mvn assembly:assembly -DdescriptorId=jar-with-dependencies
```

## To test

```shell
  java -cp target/jingjs-1.0-SNAPSHOT-jar-with-dependencies.jar jingjs.App
```

## To compile with bytecoder

```shell
  java -jar bytecoder-cli-2019-12-08-executable.jar -classpath=target/jingjs-1.0-SNAPSHOT-jar-with-dependencies.jar -mainclass=jingjs.App -builddirectory=. -backend=js -minify=false
```
