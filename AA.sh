#!/usr/bin/env bash

printf '\e[8;50;140t'
javac -cp "lanterna.jar:." Game.java
java -cp "lanterna.jar:." Game
