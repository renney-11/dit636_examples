# app.py
from flask import Flask, request, jsonify

app = Flask(__name__)

# 1 has all courses for Program 1
# 2 has subset of courses for Program 1
# 3 is an empty list of courses
# 4 has all for program 2
# 5 has subset for program 2
# 6 has all for 1 + some from 2
# 7 has all from 2 + some from 1
# 8 has subset from both
# 9 has a single course

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
        students.append(student)
        return student, 201
    return {"error": "Request must be JSON-formatted"}, 415

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
