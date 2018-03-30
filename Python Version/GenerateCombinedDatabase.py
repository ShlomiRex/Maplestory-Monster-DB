
import csv

reader_strings = csv.DictReader(open("Result/Strings.csv","r",encoding="utf-8"))
reader_mobattrib = csv.DictReader(open("Result/MobsAttributes.csv","r",encoding="utf-8"))
file_out = open("Database.csv","w+",encoding="utf-8")

#id as int
def getNameByID(id):
    global reader_strings
    for record in reader_strings:
        if int(record["id"]) == int(id):
            reader_strings = csv.DictReader(open("Result/Strings.csv", "r", encoding="utf-8"))
            return record["name"]
    return None

def generateDatabase():
    file_out.write("id,name,level,exp,hp\n")
    for row in reader_mobattrib:
        mob_id = None
        mob_name = None
        mob_level = None
        mob_exp = None
        mob_hp = None

        mob_id = int(row["id"])
        mob_name = getNameByID(mob_id)
        mob_level = row["level"]
        mob_exp = row["exp"]
        mob_hp = row["hp"]
        if mob_id is None or mob_name is None or mob_level is None or mob_exp is None or mob_hp is None:
            print("Error at mob id: ",mob_id)
            continue
        else:
            db_string = str(mob_id)+","+mob_name+","+mob_level+","+mob_exp+","+mob_hp+"\n"
            file_out.write(db_string)
    return

generateDatabase()


#searching in strings for id: 100120
#strings: has 100120