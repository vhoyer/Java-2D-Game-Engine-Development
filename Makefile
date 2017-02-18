bin=./bin
src=./src
# SRC := $(wildcard src/*.java)
SRC := $(shell find -type f -name '*.java')
BIN := $(shell find -type f -name '*.class')
CLASSPATH=-classpath .:./bin:./assets:./res:./src:

list := $(SRC:$(src)/%.java=$(bin)/%.class)


run: $(list)
	java $(CLASSPATH) io.github.vhoyer.game.Game

$(bin)/%.class: $(src)/%.java
	javac -g -d $(bin) $(CLASSPATH) $^

clean:
	rm bin/*.class

jar: $(list)
	cd bin
	jar -cfmv ../SpermRace.jar ../MANIFEST.MF .

build:
	javac -g -d $(bin) $(CLASSPATH) $(src)/*.java

rebuild:
	-mkdir $(bin) || -rm $(bin)/*.class
	javac -g -d $(bin) $(CLASSPATH) $(src)/*.java

.PHONY: run clean rebuild build jar help
