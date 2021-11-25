'''@author AJWuu'''

import json
from python_freeipa import ClientMeta

json_file = 'customers.json'
with open(json_file, 'r') as jsonFile:
    records = json.loads(jsonFile.read())

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
