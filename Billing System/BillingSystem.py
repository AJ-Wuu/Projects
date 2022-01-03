'''@author AJWuu'''

import json
from python_freeipa import ClientMeta

json_file = 'customers.json'
with open(json_file, 'r') as jsonFile:
    records = json.loads(jsonFile.read())


class customer:
    Department = 'unknown department'
    Email = 'xxx@xxx.edu'
    FirstName = 'firstname'
    LastName = 'lastname'
    Name = 'firstname lastname'

    def __init__(self, Department, Email, FirstName, LastName):
        self.Department = Department
        self.Email = Email
        self.FirstName = FirstName
        self.LastName = LastName
        self.Name = FirstName + LastName


client = ClientMeta('ipa.demo1.freeipa.org')
#client = ClientMeta(dns_lookup=True)
client.login('admin', 'Secret123')

userAdd = client.user_add('test3', 'John', 'Doe',
                          'John Doe', o_preferredlanguage='EN')

userMod = client.user_mod()

userDel = client.user_del()

groupAdd = client.group_add()

groupMod = client.group_mod()

groupDel = client.group_del()

print(userAdd)
