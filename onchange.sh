# fswatch -o *.java | xargs -n1 -I{} ./onchange.sh

!/bin/bash
clear
find . -name "*.java" > sources.txt
javac @sources.txt -d ./
java RayTracer 
echo "Updated"