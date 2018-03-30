File_Loc = "Resources/MobStrings.img.xml"
File_Destination = "Strings.csv"

import xml.etree.ElementTree as ET
tree = ET.parse(File_Loc) #xml file
root = tree.getroot()

database = open(File_Destination, "w+", encoding="utf-8")
for child in root:
    monster_id = child.get("name")
    monster_name = child[0].get("value")
    string = monster_id+","+monster_name+"\n"
    database.write(string)
database.close()