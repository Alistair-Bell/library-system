PACKAGE = alistairbell.xyz
SOURCES = $(wildcard src/*.java)
JA      = java
JC      = javac
OUT     = bin
ENTRY   = main
JCFLAGS = -d ${OUT} -Werror

all: build run

prepare:
	ls ${OUT} || mkdir ${OUT}

build: prepare
	${JC} ${JCFLAGS} ${SOURCES}

run:
	${JA} -classpath ${OUT} ${PACKAGE}.${ENTRY}

clean:
	$(RM) -r ${OUT}/**
	
