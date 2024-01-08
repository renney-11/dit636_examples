# Student Management System

The primary purpose of this system is to check whether students are ready to graduate from a particular degree program. 

This system has a REST API that surfaces the following functions:

- *Endpoint, REST Method, Example URL, Description*
- /student, GET, http://127.0.0.1:5000/student, Get a list of all students, with their IDs.
- /student/{student_id}, GET, http://127.0.0.1:5000/student/6, Get data for a single student.
- /create, POST, http://127.0.0.1:5000/create, Create a new student record in the database, if a record does not already exist for that personnummer.
- /update/{student_id}, PUT, http://127.0.0.1:5000/update/2, Update a student record. Note that changes to personnummer are not allowed.
- /delete/{student_id}, DELETE, http://127.0.0.1:5000/delete/5, Delete a student record.
- /program, GET, http://127.0.0.1:5000/programm Get a list of all programs, with their IDs.
- /program/{program_id}, GET, http://127.0.0.1:5000/program/1, Get a list of required courses for a particular program.
- /finished/{student_id}/{program_id}, GET, http://127.0.0.1:5000/finished/1/1, Checks whether a particular student is ready to graduate from a particular program.

The POST and PUT methods require, as input, a JSON structure representing a student. The allowed records in this structure include:

- name (string)
- personnummer (string, format YYMMDD-NNNN)
- courses_passed (a list of courses, each an ID represented by a string of format “XXXNNN”, where “XXX” is a three letter department ID and NNN is a three number course ID).

The student records stored in the app also include the field:

- student_id (integer).

A student_id is not needed for the create method, as it is assigned by the system. 

A degree program is represented by a list of courses, where - again - each has a course_id. 

All input is validated by the system. If you provide invalid or malformed input - either in the endpoint URL or the JSON bodies for the create and update methods - you should expect an appropriate error. 

Note that the POST/PUT/DELETE methods do not actually make permanent changes in this example. You will get an appropriate response, but the record will not actually be created, updated, or deleted. You can use the result body to verify the results of running these functions.

To deploy the system locally:

- Check out the repository: https://github.com/Greg4cr/dit636_examples.git 
- In a terminal:
    - Enter the directory src/as1-webapi/ 
    - Install the Python package flask: python -m pip install flask 
    - Set the following environmental variables: 
        - export FLASK_APP=app.py
        - export FLASK_ENV=development
        - (on Windows, “set” instead of “export”)
    - Start flask: flask run

Once the system is deployed, you can interact with the system using curl, Postman, or other utilities that can send requests to the endpoints defined above. 

See the following tutorial for more information: https://realpython.com/api-integration-in-python/#rest-and-python-tools-of-the-trade 
