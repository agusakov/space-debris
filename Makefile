.PHONY: run
run: classes
	java -cp bin view.View

.PHONY: classes
classes:
	mkdir -p bin
	javac -d bin src/controller/*.java src/model/*.java src/view/*.java

.PHONY: clean
clean:
	rm -r bin

.PHONY: javadoc
javadoc: 
	mkdir -p docs
	javadoc -d docs src/controller/*.java src/model/*.java src/view/*.java