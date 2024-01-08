# app.py
from flask import Flask, request, jsonify
import re

app = Flask(__name__)

students = [
    {"id": 1, "name": "Sven Svensson", "personnummer": "870223-9999", "courses_passed": ["DIT001", "DIT002", "DIT003", "DIT004", "DIT005", "DIT006", "DIT007", "DIT008", "DIT009", "DIT010"]},
    {"id": 2, "name": "Erik Svensson", "personnummer": "990111-9999", "courses_passed": ["DIT001", "DIT002", "DIT003", "DIT004", "DIT005", "DIT006", "DIT007"]},
    {"id": 3, "name": "Joel Svensson", "personnummer": "011030-9999", "courses_passed": []},
    {"id": 4, "name": "Yue Sakamoto", "personnummer": "980401-9999", "courses_passed": ["DIT101", "DIT102", "DIT103", "DIT104", "DIT105", "DIT106", "DIT107", "DIT108", "DIT109", "DIT110"]},
    {"id": 5, "name": "Ivan Faknamovich", "personnummer": "781128-9999", "courses_passed": ["DIT101", "DIT102", "DIT103", "DIT107"]},
    {"id": 6, "name": "Amy Pond", "personnummer": "030201-9999", "courses_passed": ["DIT001", "DIT002", "DIT003", "DIT004", "DIT005", "DIT006", "DIT007", "DIT008", "DIT009", "DIT010", "DIT104", "DIT105"]},
    {"id": 7, "name": "Rory Williams", "personnummer": "010203-9999", "courses_passed": ["DIT005", "DIT010", "DIT101", "DIT102", "DIT103", "DIT104", "DIT105", "DIT106", "DIT107", "DIT108", "DIT109", "DIT110"]},
    {"id": 8, "name": "Steve Rogers", "personnummer": "180704-9999", "courses_passed": ["DIT005", "DIT010", "DIT101", "DIT104", "DIT105", "DIT104"]},
    {"id": 9, "name": "Natasha Romanov", "personnummer": "861231-9999", "courses_passed": ["DIT001"]}
]

programs = [
    {"id": 1, "courses_required":["DIT001", "DIT002", "DIT003", "DIT004", "DIT005", "DIT006", "DIT007", "DIT008", "DIT009", "DIT010"]},
    {"id": 2, "courses_required":["DIT101", "DIT102", "DIT103", "DIT104", "DIT105", "DIT106", "DIT107", "DIT108", "DIT109", "DIT110"]}
]

def _find_next_id():
    return max(student["id"] for student in students) + 1

@app.get("/student")
def get_students():
    return jsonify(students)

@app.get("/student/<id>")
def get_student(id):
    try:
        int(id)
    except:
         return {"error": "Student ID " + id + " is not formatted correctly."}, 400

    for student in students:
        if student["id"] == int(id):
            return jsonify(student)
    return {"error": "Student ID " + id + " does not exist"}, 404 
 
@app.post("/create")
def add_student():
    if request.is_json:
        student = request.get_json()
        student["id"] = _find_next_id()

        # name, personnummer must exist
        # check format of both
        if "name" not in student:
            return {"error": "No name field"}, 400
        elif student["name"] == "" or student["name"] == None:
            return {"error": "Blank or null name"}, 400

        if "personnummer" not in student:
            return {"error": "No personnummer field"}, 400
        elif student["personnummer"] == "" or student["personnummer"] == None:
            return {"error": "Blank or null personnummer"}, 400

        pattern = re.compile(r'^\d{6}-\d{4}$')
        if pattern.match(student["personnummer"]):
            month = int(student["personnummer"][2:4])
            day = int(student["personnummer"][4:6])
            if month == 0 or month > 12:
                return {"error": "Invalid month in personnummer"}, 400
            if day == 0 or day > 31:
                return {"error": "Invalid day in personnummer"}, 400 
        else:
            return {"error": "Malformed personnummer " + student["personnummer"]}, 400
      
        for existing in students:
            if student["personnummer"] == existing["personnummer"]:
                return {"error": "Personnummer already belongs to student " + str(existing["id"])}, 400

        # check format of courses
        if "courses_passed" in student:
            for course in student["courses_passed"]:
                if course == "" or course == None:
                    return {"error": "Empty or null course ID"}, 400
                
                pattern = re.compile(r'^[a-zA-Z]{3}\d{3}$')
                if not pattern.match(course):
                    return {"error": "Malformed course ID: " + course}, 400 
        # no courses passed is ok, can be added as empty
        else:
            student["courses_passed"] = []

        # other fields scrubbed
        for key in student:
            if key != "id" and key != "personnummer" and key != "name" and key != "courses_passed":
                del student[key]

        return student, 201
    return {"error": "Request must be JSON-formatted"}, 415

@app.put("/update/<id>")
def update_student(id):
    try:
        int(id)
    except:
         return {"error": "Student ID " + id + " is not formatted correctly."}, 400

    if request.is_json:
        student = request.get_json()
        student["id"] = id

        # name, personnummer must exist
        # check format of both
        if "name" not in student:
            return {"error": "No name field"}, 400
        elif student["name"] == "" or student["name"] == None:
            return {"error": "Blank or null name"}, 400

        if "personnummer" not in student:
            return {"error": "No personnummer field"}, 400
        elif student["personnummer"] == "" or student["personnummer"] == None:
            return {"error": "Blank or null personnummer"}, 400

        pattern = re.compile(r'^\d{6}-\d{4}$')
        if pattern.match(student["personnummer"]):
            month = int(student["personnummer"][2:4])
            day = int(student["personnummer"][4:6])
            if month == 0 or month > 12:
                return {"error": "Invalid month in personnummer"}, 400
            if day == 0 or day > 31:
                return {"error": "Invalid day in personnummer"}, 400 
        else:
            return {"error": "Malformed personnummer " + student["personnummer"]}, 400
      
        # check format of courses
        if "courses_passed" in student:
            for course in student["courses_passed"]:
                if course == "" or course == None:
                    return {"error": "Empty or null course ID"}, 400
                
                pattern = re.compile(r'^[a-zA-Z]{3}\d{3}$')
                if not pattern.match(course):
                    return {"error": "Malformed course ID: " + course}, 400 
        # no courses passed is ok, can be added as empty
        else:
            student["courses_passed"] = []

        # other fields scrubbed
        for key in student:
            if key != "id" and key != "personnummer" and key != "name" and key != "courses_passed":
                del student[key]

        # Passed format checks. Find the correct record and update.
        found = False
        for existing in students:
            if existing["id"] == int(id):
                found = True
                if existing["personnummer"] != student["personnummer"]:
                    return {"error": "Changes to personnummer are not allowed."}, 400
            elif student["personnummer"] == existing["personnummer"]:
                return {"error": "Personnummer already belongs to student " + str(existing["id"])}, 400

        if found:
            return student, 200
        else: 
            return {"error": "Student ID " + id + " does not exist"}, 404 
    return {"error": "Request must be JSON-formatted"}, 415

@app.delete("/delete/<id>")
def delete_student(id):
    try:
        int(id)
    except:
         return {"error": "Student ID " + id + " is not formatted correctly."}, 400

    for student in students:
        if student["id"] == int(id):
            return {"deleted": id}
    return {"error": "Student ID " + id + " does not exist"}, 404 

@app.get("/program")
def get_programs():
    return jsonify(programs)

@app.get("/program/<id>")
def get_program(id):
    try:
        int(id)
    except:
         return {"error": "Program ID " + id + " is not formatted correctly."}, 400

    for program in programs:
        if program["id"] == int(id):
            return jsonify(program)
    return {"error": "Program ID " + id + " does not exist"}, 404 
       
@app.get("/finished/<student_id>/<program_id>")
def is_finished(student_id, program_id):
    try:
        int(student_id)
    except:
         return {"error": "Student ID " + student_id + " is not formatted correctly."}, 400
    try:
        int(program_id)
    except:
         return {"error": "Program ID " + program_id + " is not formatted correctly."}, 400

    for student in students:
        if student["id"] == int(student_id):
            for program in programs:
                if program["id"] == int(program_id):
                    matching_courses = 0
                    all_courses = True
                    for course in program["courses_required"]:
                        if course in student["courses_passed"]:
                            matching_courses += 1
                        else:
                            all_courses = False

                    return {"status": all_courses, "completed_courses" : matching_courses}, 200
            return {"error": "Program ID " + program_id + " does not exist"}, 404 
    return {"error": "Student ID " + student_id + " does not exist"}, 404
