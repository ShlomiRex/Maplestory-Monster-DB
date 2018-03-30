
import csv

db = open("Database.csv","r",encoding="utf-8")
out = open("Database_ExpDivHp.csv","w+",encoding="utf-8")

out.write("id,name,level,exp,hp,exp_div_hp\n")

for line in csv.DictReader(db):
    orddic = line.values()
    string = ""
    for value in orddic:
        string += value+","
    try:
        exp = int(line["exp"])
        hp = int(line["hp"])
        div = float(exp/hp)
        string += str(div)
    except:
        string = string[:-1]
    string += "\n"
    out.write(string)