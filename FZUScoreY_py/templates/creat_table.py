import xlrd
import pymysql

connect = pymysql.connect(host="localhost", port=3306, user="root", passwd="HRYYCFLqswslhk1", charset="utf8", db='mytest')
cur = connect.cursor()
book = xlrd.open_workbook(r"C:\Users\CY\Documents\tencent files\1034088383\filerecv\成绩表格 (1).xls")


sheet = book.sheet_by_name("大二下")
query = "insert into student_b2 (id, name, a_score, corganization_score, cnetwork_score, probility_score, alanguage_score, marx_score, average_score)values (%s, %s, %s, %s, %s, %s, %s, %s, %s)"
for i in range(1, sheet.nrows):
    id = sheet.cell(i, 0).value
    name = sheet.cell(i, 1).value
    a_score = sheet.cell(i, 2).value
    coranization_score = sheet.cell(i, 3).value
    cnetwork_score = sheet.cell(i, 4).value
    probility_score = sheet.cell(i, 5).value
    alanguage_score = sheet.cell(i, 6).value
    marx_score = sheet.cell(i, 7).value
    average_score = str((sheet.cell(i, 2).value+sheet.cell(i, 3).value+sheet.cell(i, 4).value+sheet.cell(i, 5).value+sheet.cell(i, 6).value+sheet.cell(i, 7).value)*1.0/6)
    values = (id, name, a_score, coranization_score, cnetwork_score, probility_score, alanguage_score, marx_score, average_score)
    try:
        cur.execute(query, values)
    except:
        pass
    connect.commit()
row = str(sheet.nrows-1)
print("已导入"+row+"行数据到数据库\n")


sheet = book.sheet_by_name("大一上")
query = "insert into student_a1 (id, name, cintroduction_score, practice_score, mathb1_score, pdesign_score, ppractice_score, lalgebra_score, average_score)values (%s, %s, %s, %s, %s, %s, %s, %s, %s)"
for i in range(1, sheet.nrows):
    id = sheet.cell(i, 0).value
    name = sheet.cell(i, 1).value
    cintroduction_score = sheet.cell(i, 2).value
    practice_score = sheet.cell(i, 3).value
    mathb1_score = sheet.cell(i, 4).value
    pdesign_score = sheet.cell(i, 5).value
    ppractice_score = sheet.cell(i, 6).value
    lalgebra_score = sheet.cell(i, 7).value
    average_score = str((sheet.cell(i, 2).value + sheet.cell(i, 3).value + sheet.cell(i, 4).value + sheet.cell(i, 5).value+sheet.cell( i, 6).value + sheet.cell(i, 7).value) * 1.0 / 6)
    values = (id, name, cintroduction_score, practice_score, mathb1_score, pdesign_score, ppractice_score, lalgebra_score, average_score)
    try:
        cur.execute(query, values)
    except:
        pass
    connect.commit()
row = str(sheet.nrows-1)
print("已导入"+row+"行数据到数据库\n")


sheet = book.sheet_by_name("大一下")
query = "insert into student_a2 (id, name, physicsA1_score, pexperimentA1_score, mathb2_score, circuitry_score, cexperiment_score, oop_score, average_score)values (%s, %s, %s, %s, %s, %s, %s, %s, %s)"
for i in range(1, sheet.nrows):
    id = sheet.cell(i, 0).value
    name = sheet.cell(i, 1).value
    physicsA1_score = sheet.cell(i, 2).value
    pexperimentA1_score = sheet.cell(i, 3).value
    mathb2_score = sheet.cell(i, 4).value
    circuitry_score = sheet.cell(i, 5).value
    cexperiment_score = sheet.cell(i, 6).value
    oop_score = sheet.cell(i, 7).value
    average_score = str((sheet.cell(i, 2).value + sheet.cell(i, 3).value + sheet.cell(i, 4).value + sheet.cell(i, 5).value+sheet.cell( i, 6).value + sheet.cell(i, 7).value) * 1.0 / 6)
    values = (id, name, physicsA1_score, pexperimentA1_score, mathb2_score, circuitry_score, cexperiment_score, oop_score, average_score)
    try:
        cur.execute(query, values)
    except:
        pass
    connect.commit()


sheet = book.sheet_by_name("大二上")
query = "insert into student_b1 (id, name, physicsA2_score, pexperimentA2_score, discrete_score, dcircuitry_score, dexperiment_score, algorithmnt_score, average_score)values (%s, %s, %s, %s, %s, %s, %s, %s, %s)"
for i in range(1, sheet.nrows):
    id = sheet.cell(i, 0).value
    name = sheet.cell(i, 1).value
    physicsA2_score = sheet.cell(i, 2).value
    pexperimentA2_score = sheet.cell(i, 3).value
    discrete_score = sheet.cell(i, 4).value
    dcircuitry_score = sheet.cell(i, 5).value
    dexperiment_score = sheet.cell(i, 6).value
    algorithmnt_score = sheet.cell(i, 7).value
    average_score = str((sheet.cell(i, 2).value + sheet.cell(i, 3).value + sheet.cell(i, 4).value + sheet.cell(i, 5).value+sheet.cell( i, 6).value + sheet.cell(i, 7).value) * 1.0 / 6)
    values = (id, name, physicsA2_score, pexperimentA2_score, discrete_score, dcircuitry_score, dexperiment_score, algorithmnt_score, average_score)
    try:
        cur.execute(query, values)
    except:
        pass
    connect.commit()
query = r"desc student_a1" #####
x=cur.execute(query)
print(x)
info = cur.fetchall()
for i in range (0, x):
    print(info[i][0])
connect.close()
def get_score(username, term):
    connect = pymysql.connect(host="localhost", port=3306, user="root", passwd="HRYYCFLqswslhk1", charset="utf8",
                              db='mytest')
    cur = connect.cursor()
    query = "desc student_"+term
    cur.execute(query)
    all = cur.fetchall()
    s1 = all[2][0]
    s2 = all[3][0]
    s3 = all[4][0]
    s4 = all[5][0]
    s5 = all[6][0]
    s6 = all[7][0]
    query = "select * from student_"+term+" where id="+username
    cur.execute(query)
    info = cur.fetchone()
    query = "select average_score from student_" + term + " where id=" + username
    cur.execute(query)
    average = cur.fetchone()[0]
    query = "select average_score from student_" + term
    cur.execute(query)
    all_score = cur.fetchall()
    rank = 1
    student_num = cur.execute(query)
    for i in range(0, student_num):
        if average < all_score[i][0]:
            rank += 1
    percentage = round(rank * 1.0 / student_num * 100, 2)
    connect.close()
    cur.close()
    return {
        'stu_name':info[1],
        'name_1':s1,
        'score_1':info[2],
        'name_2':s2,
        'score_2':info[3],
        'name_3':s3,
        'score_3':info[4],
        'name_4':s4,
        'score_4':info[5],
        'name_5':s5,
        'score_5':info[6],
        'name_6':s6,
        'score_6':info[7],
        'average':info[8],
        'rank': rank,
        'percentage': str(percentage)+"%"
        }


row = str(sheet.nrows-1)   ######
print("已导入"+row+"行数据到数据库\n")
cur.close()

