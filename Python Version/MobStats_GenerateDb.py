import xml.etree.ElementTree as ET
import os
Mobs_Files = "Resources/Mobs/"
"""
#Input: Mob file in format XML
def getMobAttributes(file):
    result = []
    tree = ET.parse(file)
    root = tree.getroot()
    for child in tree.iter():
        result.append(child)
        break #change
    return result

for file_name in os.listdir(Mobs_Files):
    res = getMobAttributes(open(Mobs_Files+file_name,"r"))
    print(res)
    break #change
"""
file_out = open("MobsAttributes.csv","w+")
for file_name in os.listdir(Mobs_Files):
    tree = ET.parse(Mobs_Files+file_name)
    root = tree.getroot()
    for child in root:
        if child.attrib.get("name") == "info":
            for mob_info in child:
                if mob_info.get("name") == "level":
                    mob_level = mob_info.get("value")
                elif mob_info.get("name") == "exp":
                    mob_exp = mob_info.get("value")
                elif mob_info.get("name") == "maxHP":
                    mob_hp = mob_info.get("value")
            #id, level, exp, hp
            db_string = file_name.replace(".img.xml","")+","+mob_level+","+mob_exp+","+mob_hp+"\n"
            file_out.write(db_string)
file_out.close()