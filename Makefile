PACKAGE = alistairbell.xyz
SOURCES = src/book.java src/entry.java
JA      = java
JC      = javac
OUT     = bin
JCFLAGS = -d ${OUT} -Werror

all: build run

prepare:
	file ${OUT} || mkdir ${OUT}

build: prepare
	${JC} ${JCFLAGS} ${SOURCES}

run:
	${JA} -classpath ${OUT} ${PACKAGE}.entry

clean:
	$(RM) -r ${OUT}/**
	
