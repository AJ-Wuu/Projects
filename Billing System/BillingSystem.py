'''@author AJWuu'''

import json
from python_freeipa import ClientMeta

with open ('customers.json','r') as jsonFile:
    customers = json.load(jsonFile)

client = ClientMeta('ipa.demo1.freeipa.org')
client.login('admin', 'Secret123')
user = client.user_add('test3', 'John', 'Doe', 'John Doe', o_preferredlanguage='EN')
user = client.user_mod()

group = client.group_add()
group = client.group_mod()
