# fswatch -o *.java | xargs -n1 -I{} ./onchange.sh

!/bin/bash
clear
javac *.java
java RayTracer
echo "Updated"