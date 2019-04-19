from flask import Flask, jsonify
from requests import request
import pymysql


def is_exist(username):
    connect = pymysql.connect(host="localhost", port=3306, user="root", passwd="HRYYCFLqswslhk1", charset="utf8",
                              db='mytest')
    cur = connect.cursor()
    query = "select * from student_a1 where id= "+username
    if cur.execute(query):
        connect.close()
        cur.close()
        return True
    else:
        connect.close()
        cur.close()
        return False


def check_password(username, password):
    if(is_exist(username)):
        connect = pymysql.connect(host="localhost", port=3306, user="root", passwd="HRYYCFLqswslhk1", charset="utf8",
                              db='mytest')
        cur = connect.cursor()
        query = "select password from student_a1 where id= "+username
        if cur.execute(query)>0:
            real_pw = cur.fetchone()[0]
            if real_pw == password:
                connect.close()
                cur.close()
                return True
        else:
            connect.close()
            cur.close()
            return False
    else:
        return False


def get_info(username, term):
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
    query = "select password from student_a2 where id=031799158"
    cur.fetchone(query)
    password=cur.fetchone()[0]
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
    return jsonify(
        {
        'id':username,
        'stu_name':info[1],
        'name_1': s1,
        'score_1': info[2],
        'name_2': s2,
        'score_2': info[3],
        'name_3':s3,
        'score_3': info[4],
        'name_4':s4,
        'score_4':info[5],
        'name_5':s5,
        'score_5':info[6],
        'name_6':s6,
        'score_6':info[7],
        'average':info[8],
        'rank': rank,
        'percentage':  str(percentage)+"%",
        'password':password
        }
    )


def get_average(sub, term):
    connect = pymysql.connect(host="localhost", port=3306, user="root", passwd="HRYYCFLqswslhk1", charset="utf8",
                              db='mytest')
    cur = connect.cursor()
    query =r"select "+sub+" from student_"+term
    stu_num = cur.execute(query)
    each_score = cur.fetchall()
    sum = 0
    for i in range(0,stu_num):
        sum += each_score[i][0]
    average = sum*1.0/stu_num
    return jsonify({
        'sub':sub,
        'average':average
    })


app = Flask(__name__)


@app.route('/api/users/<string:username>/<string:term>', methods=['GET'])
def get_info(username, term):
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
    query = "select password from student_"+term+" where id="+username
    cur.execute(query)
    password=cur.fetchone()[0]
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
    if term == 'a1': t = 1
    elif term == 'a2': t = 2
    elif term == 'b1': t = 3
    elif term == 'b2': t = 4
    connect.close()
    cur.close()
    return jsonify(
        {
        'Stuid':username,
        'stu_name':info[1],
        'name_1': s1,
        'score_1': int(info[2]),
        'name_2': s2,
        'score_2': int(info[3]),
        'name_3':s3,
        'score_3': int(info[4]),
        'name_4':s4,
        'score_4':int(info[5]),
        'name_5':s5,
        'score_5':int(info[6]),
        'name_6':s6,
        'score_6':int(info[7]),
        'average':int(info[8]),
        'rank': rank,
        'percentage':  str(percentage)+"%",
        'password':password,
        'term':t
        }
    )


@app.route('/api/monitor', methods=['GET'])
def get_monitor():
    connect = pymysql.connect(host="localhost", port=3306, user="root", passwd="HRYYCFLqswslhk1", charset="utf8",
                              db='mytest')
    cur = connect.cursor()
    query = "select id from monitor"
    cur.execute(query)
    id = cur.fetchone()[0]
    query = "select password from monitor"
    cur.execute(query)
    password = cur.fetchone()[0]
    return jsonify({
        'mon_id': id,
        'mon_pas':password
    })

@app.route('/api/all', methods=['GET'])
def get_all():
    connect = pymysql.connect(host="localhost", port=3306, user="root", passwd="HRYYCFLqswslhk1", charset="utf8",
                              db='mytest')
    cur = connect.cursor()
    all_info = []
    for k in range(1,5):
        for username in range(1, 59):
            if k == 1:term='a1'
            if k == 2:term='a2'
            if k == 3:term='b1'
            if k == 4:term='b2'
            query = "desc student_" + term
            cur.execute(query)
            all = cur.fetchall()
            s1 = all[2][0]
            s2 = all[3][0]
            s3 = all[4][0]
            s4 = all[5][0]
            s5 = all[6][0]
            s6 = all[7][0]
            id = "0317991%02d" % username
            query = "select password from student_" + term + " where id=" + id
            cur.execute(query)
            password = cur.fetchone()[0]
            query = "select * from student_" + term + " where id=" + id
            cur.execute(query)
            info = cur.fetchone()
            query = "select average_score from student_" + term + " where id=" + id
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
            a = {
                    'Stuid': id,
                    'stu_name': info[1],
                    'name_1': s1,
                    'score_1': int(info[2]),
                    'name_2': s2,
                    'score_2': int(info[3]),
                    'name_3': s3,
                    'score_3': int(info[4]),
                    'name_4': s4,
                    'score_4': int(info[5]),
                    'name_5': s5,
                    'score_5': int(info[6]),
                    'name_6': s6,
                    'score_6': int(info[7]),
                    'average': int(info[8]),
                    'rank': rank,
                    'percentage': str(percentage) + "%",
                    'password': password,
                    'term': k
                }
            all_info.append(a)
    connect.close()
    cur.close()
    return jsonify(all_info)


if __name__ == '__main__':
    app.run(host='0.0.0.0', port=5000, debug=True)
