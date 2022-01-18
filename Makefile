include config.mk

PACKAGE = alistairbell.xyz
SOURCES = $(wildcard src/*.java)
ENTRY   = main

all: build run

prepare:
	ls ${OUT} || mkdir ${OUT}

build: prepare
	${JC} ${JCFLAGS} ${SOURCES}

run:
	${JA} -classpath ${OUT} ${PACKAGE}.${ENTRY}

clean:
	$(RM) -r ${OUT}/**
	
