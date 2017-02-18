bin=./bin
src=./src
# SRC := $(wildcard src/*.java)
SRC := $(shell find -type f -name '*.java')
CLASSPATH=-classpath .:./bin:./assets:./res:./src:

list := $(SRC:$(src)/%.java=$(bin)/%.class)


run: $(list)
	java $(CLASSPATH) io.github.vhoyer.game.Game

build: $(list)

$(bin)/%.class: $(src)/%.java
	javac -g -d $(bin) $(CLASSPATH) $^

clean:
	rm bin/*.class

jar: $(list)
	cd bin
	jar -cfmv ../SpermRace.jar ../MANIFEST.MF .

rebuild:
	rm -rf $(bin)/*
	javac -g -d $(bin) $(CLASSPATH) $(SRC)

.PHONY: run clean rebuild build jar
