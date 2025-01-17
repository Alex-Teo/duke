@ECHO OFF

REM create bin directory if it doesn't exist
if not exist ..\bin mkdir ..\bin

REM delete output from previous run
del ACTUAL.TXT

REM compile the code into the bin folder
javac  -cp ..\src -Xlint:none -d ..\bin ..\src\main\java\Duke.java
IF ERRORLEVEL 1 (
    echo ********** BUILD FAILURE **********
    exit /b 1
)
REM no error here, errorlevel == 0

REM run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ..\bin Duke < input.txt > ACTUAL.TXT

REM compare the output to the expected output
FC ACTUAL.TXT EXPECTED.TXT

runtest.sh:

#!/usr/bin/env bash

# create bin directory if it doesn't exist
if [ ! -d "../bin" ]
then
    mkdir ../bin
fi

# delete output from previous run
if [ -e "./ACTUAL.TXT" ]
then
    rm ACTUAL.TXT
fi

# compile the code into the bin folder, terminates if error occurred
if ! javac -cp ../src -Xlint:none -d ../bin ..\C:\Users\axisr\IdeaProjects\duke\src\main\java\Deadline.java ..\C:\Users\axisr\IdeaProjects\duke\src\main\java\Duke.java ..\C:\Users\axisr\IdeaProjects\duke\src\main\java\Deadline.java ..\C:\Users\axisr\IdeaProjects\duke\src\main\java\Event.java ..\C:\Users\axisr\IdeaProjects\duke\src\main\java\Parser.java ..\C:\Users\axisr\IdeaProjects\duke\src\main\java\Storage.java ..\C:\Users\axisr\IdeaProjects\duke\src\main\java\Task.java ..\C:\Users\axisr\IdeaProjects\duke\src\main\java\Todo.java ..\C:\Users\axisr\IdeaProjects\duke\src\main\java\Ui.java ..\C:\Users\axisr\IdeaProjects\duke\src\main\java\UnknownException.java ..\
then
    echo "********** BUILD FAILURE **********"
    exit 1
fi

# run the program, feed commands from input.txt file and redirect the output to the ACTUAL.TXT
java -classpath ../bin C:\Users\axisr\IdeaProjects\duke\text-ui-test\Duke < input.txt > ACTUAL.TXT

# compare the output to the expected output
diff ACTUAL.TXT EXPECTED.TXT
if [ $? -eq 0 ]
then
    echo "Test result: PASSED"
    exit 0
else
    echo "Test result: FAILED"
    exit 1
fi